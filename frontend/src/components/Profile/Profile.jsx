import styles from './Profile.module.css';
import classNames from 'classnames/bind';

export const Profile = ({ url, size = '32', _onClick }) => {
	const cx = classNames.bind(styles);
	const profileClassNames = `${cx('button')} typo-m typo-bold`;
	return (
		<button onClick={_onClick} className={profileClassNames}>
			<img src={url} width={size + 'px'} height={size + 'px'}></img>
		</button>
	);
};

export default Profile;
