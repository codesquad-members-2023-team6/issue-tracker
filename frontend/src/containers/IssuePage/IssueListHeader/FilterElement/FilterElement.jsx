import { Dropdown } from '@components/index';
import { useState, useContext } from 'react';
import { PANEL_POSITION } from '@src/constants/dropdown';
import { filterContext } from '@src/services/issue';

export const FilterElement = ({ filterName, filterKey, options }) => {
  const dropdownWidth = '100px';
  const [selected, setSelected] = useState('');
  const [isDropdownOpen, setDropdownOpen] = useState(false);
  const handleDropdownOpen = (isOpen) => {
    return () => setDropdownOpen(isOpen);
  };
  const header = `${filterName} 필터`;
  const [filters, setFilters] = useContext(filterContext);

  const optionOnClick = ({ currentTarget }) => {
    const index = currentTarget.id;
    setFilters({ ...filters, [filterKey]: index });
    setSelected(index);
  };

  return (
    <>
      <Dropdown
        width={dropdownWidth}
        isOpen={isDropdownOpen}
        btnText={filterName}
        hasRadioBtn={true}
        panelPosition={PANEL_POSITION.LEFT}
        toggleOpen={handleDropdownOpen(!isDropdownOpen)}
        options={options}
        header={header}
        selected={selected}
        optionOnClick={optionOnClick}
      ></Dropdown>
    </>
  );
};
