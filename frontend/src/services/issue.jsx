import { URL } from '@constants/api';
import { customFetch } from '@services/api';
import { createContext } from 'react';
import { removeEmptyKeyValues, deepCopy } from '@utils/index';

export const filterContext = createContext();

export const getIssueDetail = async ({ issueId }) => {
  const response = await customFetch({
    path: `/issue/${issueId}`,
    method: 'GET',
    queries: {
      issueId,
    },
  });

  if (response === null) {
    throw new Error('Response is null');
  }

  return response;
};

export const patchIssueStatus = async (issueId, status) => {
  try {
    const response = await customFetch({
      path: `/issue/${issueId}/status`,
      method: 'PATCH',
      body: {
        status,
      },
    });

    return response;
  } catch (error) {
    console.error(error);
    throw error;
  }
};

export const getIssueList = async ({
  status = 'open',
  page = 0,
  assignee,
  label,
  milestone,
  writer,
  commentBy,
}) => {
  try {
    const response = await customFetch({
      path: '/issues',
      method: 'GET',
      queries: {
        status,
        page,
        maxPageNum: 10,
        assignee,
        label,
        milestone,
        writer,
        commentBy,
      },
    });

    return response;
  } catch (error) {
    console.error(error);
    throw error;
  }
};

export const convertFilterToString = (filters) => {
  const copiedFilters = { ...filters };
  removeEmptyKeyValues(copiedFilters);
  const stringfiedFilters = Object.entries(copiedFilters)
    .map((filter) => {
      const [key, value] = filter;
      return `${key}:${value}`;
    })
    .join(', ');
  return stringfiedFilters;
};

export const updateCountsToTabInfo = (
  labelAndMilestoneInfo,
  labelLength,
  milestoneLength
) => {
  const counts = {
    label: labelLength,
    milestone: milestoneLength,
  };

  const copiedInfo = deepCopy(labelAndMilestoneInfo);
  copiedInfo.forEach((_, i) => {
    copiedInfo[i].count = counts[copiedInfo[i].id];
  });
  return copiedInfo;
};

export const isFilterApplied = (filters, initialFilter) =>
  JSON.stringify(filters) !== JSON.stringify(initialFilter);
