import { Icon } from '@components/Icon/Icon';

export function Dropdown(props) {
	const { text, options = [] } = props;
	return (
		<>
			<div>
				{text}
				<Icon name="chevronDown"></Icon>
			</div>
			<div className="panel">
				<div className="label"></div>
				{options.map((option, i) => (
					<div key={i}>{option}</div>
				))}
			</div>
			<div className="backdrop"></div>
		</>
	);
}
