import Icon from '@components/Icon/Icon';
import style from './DropdownElement.module.css';

export const DropdownElement = ({
	id,
	type,
	contents,
	isSelected,
	profile,
	hasRadioBtn,
	_onClick,
}) => {
	const { DropdownElementStyle } = style;
	const isHeader = type === 'header';
	const headerClassNames = '';
	const optionClassNames =
		DropdownElementStyle + (isSelected ? ' selected' : '');

	return isHeader ? (
		<div className={headerClassNames}>{contents}</div>
	) : (
		<button onClick={_onClick} id={id} className={optionClassNames}>
			{profile}
			<label htmlFor={id}>{contents}</label>
			{hasRadioBtn && (
				<Icon name={isSelected ? 'checkOnCircle' : 'checkOffCircle'}></Icon>
			)}
		</button>
	);
};

export default DropdownElement;
