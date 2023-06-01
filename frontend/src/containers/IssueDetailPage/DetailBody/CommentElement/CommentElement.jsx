import styles from './CommentElement.module.css';
import classNames from 'classnames/bind';
import { ElapsedTime, Profile, InformationTag, Icon } from '@components/index';

export const CommentElement = ({
  issueWriterId,
  commentWriterId,
  writerProfile,
  writerName,
  wroteTime,
  contents,
}) => {
  // style
  const cx = classNames.bind(styles);
  const elementClassNames = `${cx('element')}`;
  const titleClassNames = `${cx('title')}`;
  const leftClassNames = `${cx('left')}`;
  const rightClassNames = `${cx('right')}`;
  const writeClassNames = `${cx('write')}`;
  const editClassNames = `${cx('edit')}`;
  const bodyClassNames = `${cx('body')}`;
  const writerNameText = `${cx('writerNameText')}`;
  const elapsedTimeText = `${cx('elapsedTimeText')}`;

  return (
    <div className={elementClassNames}>
      <div className={titleClassNames}>
        <div className={leftClassNames}>
          <Profile url={writerProfile} size={'32'}></Profile>
          <div className={writerNameText}>{writerName}</div>
          <div className={elapsedTimeText}>
            <ElapsedTime createdAt={wroteTime}></ElapsedTime>
          </div>
        </div>
        <div className={rightClassNames}>
          {issueWriterId === commentWriterId && (
            <div className={writeClassNames}>
              <InformationTag text={'작성자'} style={'ghost'}></InformationTag>
              <div className={editClassNames}>
                <Icon name={'edit'}></Icon>
                <span
                  className="typo-button-weak"
                  style={{ color: 'var(--color-light-neutral-text)' }}
                >
                  편집
                </span>
              </div>
            </div>
          )}
          <Icon></Icon>
        </div>
      </div>
      <div className={bodyClassNames}>
        <span>{contents}ddd</span>
      </div>
    </div>
  );
};
