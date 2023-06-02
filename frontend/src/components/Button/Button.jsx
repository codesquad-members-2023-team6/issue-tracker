import styles from './Button.module.css';
import classNames from 'classnames/bind';
import { Icon } from '@components/index';

export const Button = ({
  iconName,
  text,
  type,
  status = 'default',
  color = 'black',
  width,
  btnSize = 'l',
  _onClick,
  style,
  id,
  textColor,
  isFileBtn,
}) => {
  const cx = classNames.bind(styles);

  const btnSizeKind = cx(`size-${btnSize}`);
  const textSizeClass = `typo-${btnSize}`;
  const btnStatus = cx(`status-${status}`);
  const btnType = type === 'ghost' ? cx('type-ghost') : cx('type-contained');
  const btnColor =
    type === 'outline' ? cx(`outline-color-${color}`) : cx(`color-${color}`);

  const getTextColor = () => {
    if (type === 'ghost') {
      return 'var(--color-light-neutral-text)';
    }
    if (type === 'outline') {
      if (color === 'blue') {
        return 'var(--color-light-accent-text-weak)';
      }
      return 'var(--color-light-neutral-text)';
    }
    return 'var(--color-light-accent-text)';
  };
  const typoColor = textColor || getTextColor(type);

  const buttonClassNames = `${cx(
    'btn'
  )} ${btnColor} ${btnSizeKind} ${btnStatus} ${btnType}`;

  return (
    <button
      className={buttonClassNames}
      onClick={_onClick}
      style={{ ...style, width }}
      id={id}
      disabled={status === 'disabled'}
      type={isFileBtn ? 'submit' : undefined}
    >
      {iconName && <Icon name={iconName} fill={typoColor}></Icon>}
      <span style={{ color: typoColor }} className={textSizeClass}>
        {text}
      </span>
    </button>
  );
};
