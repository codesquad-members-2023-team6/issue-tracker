import styles from './IssueList.module.css';
import classNames from 'classnames/bind';
import { IssueElement, IssueListHeader } from '@containers/index';

export const IssueList = ({
  issueData,
  userList,
  assigneeList,
  milestoneList,
  labelList,
  issueCount,
}) => {
  const cx = classNames.bind(styles);
  const containerClassNames = cx('container');
  const contentsClassNames = cx('contents');
  const emptyClassNames = cx('empty');

  return (
    <div className={containerClassNames}>
      {issueData && (
        <IssueListHeader
          userList={userList}
          assigneeList={assigneeList}
          milestoneList={milestoneList}
          issueCount={issueCount}
          labelList={labelList}
        ></IssueListHeader>
      )}
      <ul className={contentsClassNames}>
        {issueData.length !== 0 ? (
          issueData.map((issue) => {
            const title = issue.title;
            const label = issue.label;
            const issueNumber = issue.index;
            const timeStamp = issue.editedTime;
            const writer = issue.writer.name;
            const milesStone = issue.milestone;
            const profile = issue.writer.profile;

            return (
              <li key={issueNumber}>
                <IssueElement
                  iconName="alertCircle"
                  title={title}
                  label={label}
                  issueNumber={issueNumber}
                  timeStamp={timeStamp}
                  writer={writer}
                  milesStone={milesStone}
                  profile={profile}
                ></IssueElement>
              </li>
            );
          })
        ) : (
          <div className={emptyClassNames}>
            검색과 일치하는 결과가 없습니다.
          </div>
        )}
      </ul>
    </div>
  );
};
