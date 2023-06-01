import { CommentElement } from '@containers/index';
import styles from './DetailBody.module.css';
import classNames from 'classnames/bind';
import { useEffect } from 'react';

export const DetailBody = ({ writer, comment }) => {
  const cx = classNames.bind(styles);

  const commentList = comment;
  const issueWriterId = writer?.id;

  return (
    <div className={cx('detail-body')}>
      <div className={cx('detail-comment')}>
        {commentList?.map((comment, index) => {
          const commentWriterId = comment?.writer.id;
          const writerProfile = comment?.writer.profileImageUrl;
          const writerName = comment?.writer.name;
          const wroteTime = comment?.createdAt;
          const contents = comment?.contents;

          return (
            <CommentElement
              key={index}
              issueWriterId={issueWriterId}
              commentWriterId={commentWriterId}
              writerProfile={writerProfile}
              writerName={writerName}
              wroteTime={wroteTime}
              contents={contents}
            ></CommentElement>
          );
        })}

        <div className="add-comment"></div>
      </div>
      <div className="detail-option"></div>
    </div>
  );
};
