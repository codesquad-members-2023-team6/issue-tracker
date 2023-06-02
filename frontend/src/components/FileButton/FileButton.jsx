import styles from './FileButton.module.css';
import classNames from 'classnames/bind';
import { Button, Icon } from '@components/index';

const cx = classNames.bind(styles);

export const FileButton = ({ handleFileBtnOnChange }) => {
  const fileContainerClassName = `${cx('file-container')}`;
  const labelClassName = `${cx('label')} typo-m`;
  const inputClassName = `${cx('input')}`;

  return (
    <div className={fileContainerClassName}>
      <label htmlFor="file_upload" className={labelClassName}>
        <Icon name="paperclip"></Icon>
        파일 첨부하기
      </label>
      <input
        type="file"
        id="file_upload"
        onChange={handleFileBtnOnChange}
        className={inputClassName}
      />
    </div>
  );
};
