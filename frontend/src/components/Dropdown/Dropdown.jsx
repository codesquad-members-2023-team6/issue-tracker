import { Icon } from '@components/index';
import { DropdownPanel } from './DropdownPanel/DropdownPanel';
import styles from './Dropdown.module.css';
import classNames from 'classnames/bind';
import { useRef, useState } from 'react';

export const Dropdown = ({
  optionOnClick,
  btnComponent,
  btnText,
  hasRadioBtn,
  options,
  header,
  selected,
  isOpen,
  toggleOpen,
  panelPosition,
  width,
}) => {
  const cx = classNames.bind(styles);
  const buttonClassNames = `${cx('button')} typo-m typo-bold`;
  const btnElement = useRef(null);
  const [btnCoordinate, setBtnCoordinate] = useState();

  const handleBtnClick = () => {
    const { top, left, height, right } =
      btnElement.current.getBoundingClientRect();
    setBtnCoordinate({ top, left, height, right });
    toggleOpen();
  };

  return (
    <>
      <button
        onClick={handleBtnClick}
        className={buttonClassNames}
        ref={btnElement}
        style={{ width }}
      >
        {btnComponent ?? (
          <>
            {btnText}
            <Icon
              name="chevronDown"
              fill="var(--color-light-neutral-text-weak)"
            ></Icon>
          </>
        )}
      </button>
      {isOpen && (
        <DropdownPanel
          header={header}
          options={options}
          selected={selected}
          hasRadioBtn={hasRadioBtn}
          optionOnClick={optionOnClick}
          toggleOpen={toggleOpen}
          btnCoordinate={btnCoordinate}
          panelPosition={panelPosition}
        ></DropdownPanel>
      )}
    </>
  );
};
