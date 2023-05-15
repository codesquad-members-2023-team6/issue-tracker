import { Icon, Profile } from '@components/index';
import styles from './DropdownElement.module.css';
import classNames from 'classnames/bind';
import { TYPE } from '@src/constants/dropdown';

export const DropdownElement = ({
  id,
  type,
  contents,
  isSelected,
  profile,
  hasRadioBtn,
  _onClick,
}) => {
  const cx = classNames.bind(styles);
  const headerClassNames = `${cx('header')} typo-s typo-regular`;
  const optionClassNames = `${cx('contents')} typo-m ${
    isSelected ? 'typo-bold' : 'typo-regular'
  }`;

  const isHeader = type === TYPE.HEADER;
  const iconName = isSelected ? 'checkOnCircle' : 'checkOffCircle';

  return isHeader ? (
    <div className={headerClassNames}>{contents}</div>
  ) : (
    <button onClick={_onClick} id={id} className={optionClassNames}>
      <Profile url={profile}></Profile>
      <label htmlFor={id}>{contents}</label>
      {hasRadioBtn && <Icon name={iconName}></Icon>}
    </button>
  );
};

export default DropdownElement;
