import React from 'react';
import iconfiles from '@assets/icons/index';
import styles from './Icon.module.css';
import classNames from 'classnames/bind';

export const Icon = ({
  name = 'smile',
  width = '16',
  height = '16',
  fill = 'var(--color-gray-1000)',
}) => {
  const cx = classNames.bind(styles);
  const IconComponent = iconfiles[name];
  const iconClass = cx('icon');

  return (
    <div
      className={iconClass}
      style={{ width: `${width}px`, height: `${height}px` }}
    >
      <IconComponent width={width} height={height} fill={fill}></IconComponent>
    </div>
  );
};

export default Icon;
