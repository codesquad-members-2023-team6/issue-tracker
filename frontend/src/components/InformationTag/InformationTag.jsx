import style from './InformationTag.module.css';
import rootStyle from '../../index.module.css';

export const InformationTag = ({ icon, text, bgColor, mode }) => {
	const { TagStyle, LightColor, NeutralColor, NeutralBgColor } = style;
	const tagBgColor = mode === 'light' ? bgColor : NeutralBgColor;
	const textColor = mode === 'light' ? LightColor : NeutralColor;

	const InfoTagClassName = `${TagStyle} ${rootStyle.TypoCaption} ${tagBgColor} ${textColor}`;

	return (
		<label className={InfoTagClassName} style={{ backgroundColor: tagBgColor }}>
			{icon}
			{text}
		</label>
	);
};
