import styles from './InformationTag.module.css';
import { Icon } from '@components/index';

export const InformationTag = ({
  iconName,
  text,
  backgroundColor,
  style = 'outline',
}) => {
  const { TagStyle, solidColor, outlineColor, outlineBorder } = styles;

  const solidIconColor = 'var(--color-light-accent-text)';
  const outlineIconColor = 'var(--color-light-neutral-text)';
  const outlineBgColor = 'var(--color-light-neutral-background)';

  const tagBgColor = style === 'solid' ? backgroundColor : outlineBgColor;
  const textColor = style === 'solid' ? solidColor : outlineColor;
  const iconColor = style === 'solid' ? solidIconColor : outlineIconColor;

  const InfoTagClassName = `${TagStyle} ${solidColor} typo-caption ${textColor} ${outlineBorder}`;

  return (
    <label className={InfoTagClassName} style={{ backgroundColor: tagBgColor }}>
      {iconName && <Icon name={iconName} fill={iconColor}></Icon>}
      {text}
    </label>
  );
};
