import { DropdownElement } from '@components/Dropdown/DropdownElement/DropdownElement';
import { createPortal } from 'react-dom';
import style from './DropdownPanel.module.css';

export const DropdownPanel = ({
	header,
	options,
	selected,
	toggleOpen,
	optionOnClick,
	hasRadioBtn,
}) => {
	const handleOption = (e) => {
		optionOnClick(e);
		toggleOpen();
	};

	const { DropdownPanelStyle, DropdownPanelContainerStyle } = style;

	return createPortal(
		<div className={DropdownPanelStyle} onClick={toggleOpen}>
			<div
				className={DropdownPanelContainerStyle}
				onClick={(e) => e.stopPropagation()}
			>
				<DropdownElement type="header" contents={header}></DropdownElement>
				{options.map((option, i) => {
					const isSelected = selected === option.contents;
					return (
						<DropdownElement
							type="option"
							key={i}
							id={option.id}
							profile={option.profile ?? null}
							contents={option.contents}
							isSelected={isSelected}
							hasRadioBtn={hasRadioBtn}
							_onClick={handleOption}
							toggleOpen={toggleOpen}
						></DropdownElement>
					);
				})}
			</div>
		</div>,
		document.body
	);
};
