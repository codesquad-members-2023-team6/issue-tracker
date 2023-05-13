import Icon from '@components/Icon/Icon';
import styles from './DropdownElement.module.css';
import classNames from 'classnames/bind';
import { TYPE } from '@src/constants/dropdown';

export const DropdownElement = ({
	id,
	type,
	contents,
	isSelected,
	profile,
	hasRadioBtn,
	_onClick,
}) => {
	const cx = classNames.bind(styles);
	const headerClassNames = cx('header');
	const optionClassNames = cx('contents') + (isSelected ? ' selected' : '');

	const isHeader = type === TYPE.HEADER;
	const iconName = isSelected ? 'checkOnCircle' : 'checkOffCircle';

	return isHeader ? (
		<div className={headerClassNames}>{contents}</div>
	) : (
		<button onClick={_onClick} id={id} className={optionClassNames}>
			{profile}
			<label htmlFor={id}>{contents}</label>
			{hasRadioBtn && <Icon name={iconName}></Icon>}
		</button>
	);
};

export default DropdownElement;
