import Icon from '@components/Icon/Icon';
import { DropdownElement } from './DropdownElement/DropdownElement';

export const Dropdown = ({
	isOpen,
	btnOnClick,
	optionOnClick,
	btnText,
	hasRadioBtn,
	options,
	header,
	selectedId,
}) => {
	return (
		<>
			<button onClick={btnOnClick}>
				{btnText}
				<Icon name="chevronDown"></Icon>
			</button>
			{isOpen && (
				<div>
					<DropdownElement type="header" contents={header}></DropdownElement>
					{options.map((option, i) => {
						const isSelected = selectedId === option.id;
						return (
							<DropdownElement
								id={option.id}
								type="option"
								key={i}
								profile={option.profile ?? null}
								contents={option.contents}
								isSelected={isSelected}
								hasRadioBtn={hasRadioBtn}
								_onClick={optionOnClick}
							></DropdownElement>
						);
					})}
				</div>
			)}
		</>
	);
};

export default Dropdown;
