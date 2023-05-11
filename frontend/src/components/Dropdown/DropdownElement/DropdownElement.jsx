import Icon from '@components/Icon/Icon';

export const DropdownElement = ({
	id,
	type,
	contents,
	isSelected,
	profile,
	hasRadioBtn,
	_onClick,
}) => {
	const isHeader = type === 'header';
	const headerClassNames = '';
	const optionClassNames = isSelected ? 'selected' : '';

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
