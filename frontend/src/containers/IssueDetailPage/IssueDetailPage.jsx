import styles from './IssueDetailPage.module.css';
import classNames from 'classnames/bind';
import { DetailHeader, DetailBody } from '@containers/index';
import { useParams } from 'react-router-dom';
import { useState, useEffect } from 'react';
import { getIssueDetail } from '@src/services/issue';
import { Sidebox } from '@src/components';

export const IssueDetailPage = () => {
  const params = useParams();
  const cx = classNames.bind(styles);
  const issueDetailClassNames = `${cx('issue-detail-page')}`;
  const detailBodyClassNames = `${cx('detail-body')}`;

  const [issueDetail, setIssueDetail] = useState(null);
  const [issueTitle, setIssueTitle] = useState('');
  const [selectedAssignee, setSelectedAssignee] = useState();
  const [selectedLabel, setSelectedLabel] = useState();
  const [selectedMilestone, setSelectedMilstone] = useState();

  useEffect(() => {
    (async () => {
      const issueId = params.issueId;
      const response = await getIssueDetail({ issueId });

      setIssueDetail(response);
      setIssueTitle(response?.title ?? '');
    })();
  }, []);

  useEffect(() => {
    setSelectedAssignee(issueDetail?.assignee);
    setSelectedLabel(issueDetail?.labelList?.[0]);
    setSelectedMilstone(issueDetail?.milestone);
  }, [issueDetail]);

  const getDetailDatasByComponent = (issueDetail, componentName) => {
    const {
      index,
      status,
      createdAt,
      writer,
      commentList,
      contents,
      milestone,
    } = issueDetail;
    if (componentName === 'header') {
      return {
        index,
        status,
        createdAt,
        writerName: writer.name,
        commentLegnth: commentList?.length,
      };
    }
    if (componentName === 'body') {
      const writersComment = {
        commentIdx: -1,
        contents,
        writer,
        createdAt,
        editedAt: null,
      };

      return {
        writer,
        comment: [writersComment, ...commentList],
        contents,
        milestone,
      };
    }
  };
  return (
    <div className={issueDetailClassNames}>
      {issueDetail && (
        <>
          <div>
            <DetailHeader
              {...getDetailDatasByComponent(issueDetail, 'header')}
              titleState={[issueTitle, setIssueTitle]}
            ></DetailHeader>
          </div>
          <div className={detailBodyClassNames}>
            <DetailBody
              {...getDetailDatasByComponent(issueDetail, 'body')}
            ></DetailBody>
            <Sidebox
              editable={false}
              selectedAssigneeState={[selectedAssignee, setSelectedAssignee]}
              selectedLabelState={[selectedLabel, setSelectedLabel]}
              selectedMilstoneState={[selectedMilestone, setSelectedMilstone]}
            ></Sidebox>
          </div>
        </>
      )}
    </div>
  );
};
