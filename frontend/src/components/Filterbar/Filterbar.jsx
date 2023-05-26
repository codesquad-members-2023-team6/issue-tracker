import { PANEL_POSITION } from '@constants/dropdown';
import { Dropdown } from '../Dropdown/Dropdown';
import styles from './Filterbar.module.css';
import classNames from 'classnames/bind';
import { useState, useContext, useEffect } from 'react';
import { TextInput } from '../TextInput/TextInput';
import {
  filterContext,
  convertFilterToString,
  isFilterApplied,
} from '@services/issue';
import { initialFilter } from '@constants/issue';

export const Filterbar = ({ options }) => {
  const cx = classNames.bind(styles);
  const filterbarClassNames = cx('filterbar');
  const dropdownWidth = '128px';
  const dropdownText = '필터';
  const dropdownHeader = '이슈 헤더';
  const textinputStyle = Object.freeze({
    height: 'calc(40px - 2px)',
    borderRadius: '0 11px 11px 0',
    width: '472px',
  });

  const initaialSelectedIndex = parseInt(0);
  const [selected, setSelected] = useState(initaialSelectedIndex);
  const [filters, setFilters] = useContext(filterContext);
  const initialInputValue = 'status:open';
  const [inputValue, setInputValue] = useState(initialInputValue);
  const [isDropdownOpen, setDropdownOpen] = useState(false);

  useEffect(() => {
    const stringfiedFilter = convertFilterToString(filters);
    setInputValue(stringfiedFilter);

    !isFilterApplied(filters, initialFilter) &&
      setSelected(initaialSelectedIndex);
  }, [filters]);

  const handleInputChange = (event) => {
    setInputValue(event.target.value);
  };

  const handleDropdownOptionOnClick = ({ currentTarget }) => {
    const selectedIndex = parseInt(currentTarget.id);
    const selectedFilter = options.find(
      (option) => parseInt(option.index) === selectedIndex
    ).filter;
    const newFilters = { ...initialFilter, ...selectedFilter };
    setFilters(newFilters);
    setSelected(selectedIndex);
  };

  const handleInputOnKeyDown = ({ key, currentTarget }) => {
    if (key === 'Enter') {
      const isInputEmpty = currentTarget.value === '';
      if (isInputEmpty) {
        setFilters({ ...initialFilter });
      }
    }
  };

  const handleDropdown = (isOpen) => {
    return () => setDropdownOpen(isOpen);
  };

  return (
    <div className={filterbarClassNames}>
      <Dropdown
        width={dropdownWidth}
        isOpen={isDropdownOpen}
        btnText={dropdownText}
        hasRadioBtn={true}
        panelPosition={PANEL_POSITION.LEFT}
        toggleOpen={handleDropdown(!isDropdownOpen)}
        options={options}
        header={dropdownHeader}
        selected={selected}
        optionOnClick={handleDropdownOptionOnClick}
      ></Dropdown>
      <TextInput
        icon="search"
        placeholder={initialInputValue}
        style={textinputStyle}
        value={inputValue}
        _onKeyDown={handleInputOnKeyDown}
        _onChange={handleInputChange}
      ></TextInput>
    </div>
  );
};
