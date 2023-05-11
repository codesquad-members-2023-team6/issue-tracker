import style from './Button.module.css';

export function Button(props) {
	const { icon, text, type, status, color, btnSize, _onClick } = props;
	const {
		BtnStyle,
		BtnColorBlue,
		BtnOutlineColorBlue,
		BtnColorBlack,
		BtnOutlineColorBlack,
		BtnSizeL,
		BtnSizeM,
		BtnSizeS,
		BtnStyleContained,
		BtnStyleGhost,
		BtnStatusDefault,
		BtnStatusHover,
		BtnStatusPress,
		BtnStatusDisabled,
		BtnStatusActive,
	} = style;

	const btnSizeKind =
		btnSize === 'l' ? BtnSizeL : btnSize === 'm' ? BtnSizeM : BtnSizeS;

	const btnStatus =
		status === 'hover'
			? BtnStatusHover
			: status === 'press'
			? BtnStatusPress
			: status === 'disabled'
			? BtnStatusDisabled
			: status === 'active'
			? BtnStatusActive
			: BtnStatusDefault;

	const btnType = type === 'ghost' ? BtnStyleGhost : BtnStyleContained;

	const btnColor =
		color === 'blue'
			? type === 'outline'
				? BtnOutlineColorBlue
				: BtnColorBlue
			: type === 'outline'
			? BtnOutlineColorBlack
			: BtnColorBlack;

	const buttonClassNames = `${BtnStyle} ${btnColor} ${btnSizeKind} ${btnStatus} ${btnType}`;

	return (
		<button className={buttonClassNames} onClick={_onClick}>
			{icon}
			{text}
		</button>
	);
}
