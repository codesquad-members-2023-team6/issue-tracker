import styles from './IssueElement.module.css';
import classNames from 'classnames/bind';
import { Link } from 'react-router-dom';
import { Icon, InformationTag, Profile, ElapsedTime } from '@components/index';

export const IssueElement = ({
  iconName,
  title,
  label,
  issueNumber,
  timeStamp,
  writer,
  milesStone,
  profileImageUrl,
}) => {
  const cx = classNames.bind(styles);

  return (
    <div className={cx(`issue-element`)}>
      <div className={cx(`check-box`)}>
        <input type="checkbox"></input>
      </div>
      <div className={cx(`issue-contents`)}>
        <div className={cx(`issue-contents_column`)}>
          <div className={cx(`icon`)}>
            {iconName && (
              <Icon
                name={iconName}
                fill="var(--color-light-accent-background)"
              ></Icon>
            )}
          </div>
          <div className="typo-title-medium">
            <Link to={`/detail/${issueNumber}`}>{title}</Link>
          </div>
          {label && (
            <InformationTag
              text={label.title}
              backgroundColor={label.backgroundColor}
              style={label.style}
            ></InformationTag>
          )}
        </div>
        <div className={cx(`issue-contents_column`)}>
          <div className={cx(`issue-number`)}>#{issueNumber} 이 이슈가</div>
          <div className={cx(`time-Stamp`)}>
            <ElapsedTime createdAt={timeStamp}></ElapsedTime>
          </div>
          <div className={cx(`writer`)}>{writer}님에 의해 작성되었습니다.</div>
          <div className={cx(`mile-stone`)}>
            {milesStone && (
              <>
                <Icon
                  name="milestone"
                  fill="var(--color-light-neutral-text-weak)"
                ></Icon>
                {milesStone?.title}
              </>
            )}
          </div>
        </div>
      </div>
      <div className={cx(`profile`)}>
        <Profile url={profileImageUrl}></Profile>
      </div>
    </div>
  );
};
