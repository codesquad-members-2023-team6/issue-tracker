import React from 'react';
import iconfiles from '@assets/icons/index';

export const Icon = ({ name = 'smile', width = '16', height = '16' }) => {
	const IconComponent = iconfiles[name];
	return <IconComponent width={width} height={height}></IconComponent>;
};

export default Icon;
