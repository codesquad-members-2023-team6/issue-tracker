import { DropdownElement } from '@components/Dropdown/DropdownElement/DropdownElement';
import { createPortal } from 'react-dom';
import styles from './DropdownPanel.module.css';
import classNames from 'classnames/bind';
import { TYPE } from '@src/constants/dropdown';

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
	const cx = classNames.bind(styles);
	const dropdonwPanelClassNames = cx('dropdown-panel');
	const containerClassNames = cx('container');

	const isSelected = (selected, contents) => selected === contents;

	return createPortal(
		<div className={dropdonwPanelClassNames} onClick={toggleOpen}>
			<div className={containerClassNames}>
				<DropdownElement type={TYPE.HEADER} contents={header}></DropdownElement>
				{options.map((option, i) => (
					<DropdownElement
						type={TYPE.OPTION}
						key={i}
						id={option.id}
						profile={option.profile}
						contents={option.contents}
						isSelected={isSelected(selected, option.contents)}
						hasRadioBtn={hasRadioBtn}
						_onClick={handleOption}
						toggleOpen={toggleOpen}
					></DropdownElement>
				))}
			</div>
		</div>,
		document.body
	);
};
