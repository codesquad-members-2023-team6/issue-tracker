import styles from './Empty.module.css';
import classNames from 'classnames/bind';

const cx = classNames.bind(styles);

export const Empty = ({ children }) => {
  const emptyClassNames = cx('empty');
  return <div className={emptyClassNames}>{children}</div>;
};
