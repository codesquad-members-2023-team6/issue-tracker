import { Icon } from '@components/index';
import { DropdownPanel } from './DropdownPanel/DropdownPanel';
import styles from './Dropdown.module.css';
import classNames from 'classnames/bind';

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
	const cx = classNames.bind(styles);
	const buttonClassNames = `${cx('button')} typo-m typo-bold`;
	return (
		<>
			<button onClick={toggleOpen} className={buttonClassNames}>
				{btnText}
				<Icon
					name="chevronDown"
					fill="var(--color-light-neutral-text-weak)"
				></Icon>
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
