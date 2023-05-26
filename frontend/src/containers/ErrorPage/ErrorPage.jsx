import classNames from 'classnames/bind';
import styles from './ErrorPage.module.css';
const cx = classNames.bind(styles);

export const ErrorPage = () => {
  const errorPageClassNames = cx('error-page');
  return (
    <div className={errorPageClassNames}>
      <img
        width="400px"
        src="https://i.pinimg.com/1200x/56/80/94/568094ba98564fa6652cb60e22b3674e.jpg"
      ></img>
      <h3 className="typo-xxl">404 ㅠㅠ</h3>
      <p>잘못된 요청입니다. 뒤로 돌아가세요..</p>
    </div>
  );
};
