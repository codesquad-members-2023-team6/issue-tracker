import { rest } from 'msw';
import { URL } from '@constants/api';
import {
  issueList,
  members,
  assignees,
  labels,
  milestones,
  loginToken,
} from './data';
import { removeEmptyKeyValues } from '@utils/index';
import { FILTER_KEYS } from '@constants/issue';

export const handlers = [
  rest.post('https://api.example.com/users', (req, res, ctx) => {
    const newUser = req.body;
    return res(
      ctx.status(201),
      ctx.json({
        message: 'User created successfully',
        user: newUser,
      })
    );
  }),

  rest.get(`${URL}/issues`, (req, res, ctx) => {
    const query = req.url.searchParams;
    const status = query.get('status');
    const page = query.get('page');
    const maxPageNum = query.get('maxPageNum');
    const assignee = query.get('assignee');
    const label = query.get('label');
    const milestone = query.get('milestone');
    const writer = query.get('writer');
    const commentBy = query.get('commentBy');

    const queries = {
      status,
      page,
      maxPageNum,
      assignee,
      label,
      milestone,
      writer,
      commentBy,
    };

    removeEmptyKeyValues(queries);

    const filterQueries = Object.entries(queries).filter((query) => {
      const [key, _] = query;
      const isPaginationQuery = key === 'page' || key === 'maxPageNum';
      return !isPaginationQuery;
    });

    const filteredIssueList = filterQueries.reduce(
      (filteredResult, query) => {
        const [queryKey, queryValue] = query;
        if (queryKey === FILTER_KEYS.COMMENT_BY) {
          return filteredResult
            .map((issue) => {
              const filteredComments = issue['comment'].filter(
                (comment) => comment.writer.index === queryValue
              );
              if (filteredComments.length > 0) {
                return { ...issue, comment: filteredComments };
              }
              return null;
            })
            .filter((issue) => issue !== null);
        }

        if (
          queryKey === FILTER_KEYS.WRITER ||
          queryKey === FILTER_KEYS.ASSIGNEE ||
          queryKey === FILTER_KEYS.LABEL ||
          queryKey === FILTER_KEYS.MILESTONE
        ) {
          if (queryValue === '-1') {
            return filteredResult.filter((issue) => issue[queryKey] === null);
          } else {
            return filteredResult.filter(
              (issue) =>
                parseInt(issue[queryKey]?.index) === parseInt(queryValue)
            );
          }
        }

        return filteredResult.filter((issue) => issue[queryKey] === queryValue);
      },
      issueList.sort((a, b) => b.index - a.index)
    );

    const filteredByStatus = (list, status) =>
      list.filter((issue) => issue.status === status);

    const openIssueCount = filteredByStatus(issueList, 'open').length;
    const closedIssueCount = filteredByStatus(issueList, 'close').length;

    const startCount = Number(page) * Number(maxPageNum);
    const endCount = (Number(page) + 1) * Number(maxPageNum);

    const pagenationedIssueList = filteredIssueList.slice(startCount, endCount);

    const responseData = {
      issueList: pagenationedIssueList,
      openIssueCount,
      closedIssueCount,
      userList: members,
      assigneeList: assignees,
      labelList: labels,
      milestoneList: milestones,
    };

    return res(ctx.status(200), ctx.json(responseData));
  }),

  rest.get(`${URL}/members`, (req, res, ctx) => {
    return res(ctx.status(200), ctx.json(members));
  }),

  rest.get(`${URL}/login/github`, (req, res, ctx) => {
    return res(ctx.status(200), ctx.json(loginToken));
  }),
];
