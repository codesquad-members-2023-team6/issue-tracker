import styles from './DetailBody.module.css';
import classNames from 'classnames/bind';
import { CommentElement } from '@containers/index';

export const DetailBody = ({ issueObject }) => {
  const commentList = issueObject.comment;
  const issueWriterId = issueObject.writer?.id;

  const addCommentElement = () => {
    return commentList?.map((comment, index) => {
      const commentWriterId = comment?.writer.id;
      const writerProfile = comment?.writer.profile;
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
    });
  };

  return (
    <div className="detail-body">
      <div className="detail-comment">
        {addCommentElement()}
        <div className="add-comment"></div>
      </div>
      <div className="detail-option"></div>
    </div>
  );
};
