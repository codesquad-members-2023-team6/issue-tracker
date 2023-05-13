import Icon from '@components/Icon/Icon';
import { DropdownPanel } from './DropdownPanel/DropdownPanel';

export const Dropdown = ({
	optionOnClick,
	btnText,
	hasRadioBtn,
	options,
	header,
	selected,
	isOpen,
	toggleOpen,
}) => {
	return (
		<>
			<button onClick={toggleOpen}>
				{btnText}
				<Icon name="chevronDown"></Icon>
			</button>
			{isOpen && (
				<DropdownPanel
					header={header}
					options={options}
					selected={selected}
					hasRadioBtn={hasRadioBtn}
					optionOnClick={optionOnClick}
					toggleOpen={toggleOpen}
				></DropdownPanel>
			)}
		</>
	);
};

export default Dropdown;
