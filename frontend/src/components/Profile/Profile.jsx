import styles from './Profile.module.css';
import classNames from 'classnames/bind';
import imagefiles from '@assets/images/index';

export const Profile = ({ url, size = '32', _onClick }) => {
  const cx = classNames.bind(styles);
  const profileClassNames = `${cx('button')} typo-m typo-bold`;
  const UserDefaultImageComponent = imagefiles['userImage'];
  const width = size + 'px';
  const height = size + 'px';
  return (
    <div onClick={_onClick} className={profileClassNames}>
      {url ? (
        <div
          style={{
            width: `${width}`,
            height: `${height}`,
            backgroundImage: `url(${url})`,
            backgroundSize: 'contain',
            borderRadius: '100%',
          }}
        ></div>
      ) : (
        <UserDefaultImageComponent
          width={width}
          height={height}
        ></UserDefaultImageComponent>
      )}
    </div>
  );
};
