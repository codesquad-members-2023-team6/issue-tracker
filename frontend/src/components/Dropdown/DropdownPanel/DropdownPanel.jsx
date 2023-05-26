import { DropdownElement } from '@components/Dropdown/DropdownPanel/DropdownElement/DropdownElement';
import { createPortal } from 'react-dom';
import styles from './DropdownPanel.module.css';
import classNames from 'classnames/bind';
import { TYPE } from '@src/constants/dropdown';
import { PANEL_POSITION, CONTAINER_WIDTH } from '@src/constants/dropdown';

export const DropdownPanel = ({
  header,
  options,
  selected,
  toggleOpen,
  optionOnClick,
  hasRadioBtn,
  btnCoordinate,
  panelPosition = PANEL_POSITION.RIGHT,
}) => {
  const cx = classNames.bind(styles);
  const dropdonwPanelClassNames = cx('dropdown-panel');
  const containerClassNames = `${cx('container')}`;
  const containerInlineStyle = {
    width: `${CONTAINER_WIDTH}px`,
    top: `${btnCoordinate.top + btnCoordinate.height + 4}px`,
    left: `${
      panelPosition === PANEL_POSITION.RIGHT
        ? btnCoordinate.right - CONTAINER_WIDTH
        : btnCoordinate.left
    }px`,
  };

  const isSelected = (selected, contents) => {
    return parseInt(selected) === parseInt(contents);
  };

  const handleOption = (e) => {
    optionOnClick(e);
    toggleOpen();
  };

  return createPortal(
    <div className={dropdonwPanelClassNames} onClick={toggleOpen}>
      <div className={containerClassNames} style={containerInlineStyle}>
        <DropdownElement type={TYPE.HEADER} contents={header}></DropdownElement>
        {options.map((option, i) => (
          <DropdownElement
            type={TYPE.OPTION}
            key={option.index}
            id={option.index}
            profile={option.profile}
            contents={option.contents}
            isSelected={isSelected(selected, option.index)}
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
