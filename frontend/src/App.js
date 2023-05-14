import './App.module.css';
import Dropdown from '@components/Dropdown/Dropdown';
import { useRef, useState } from 'react';
import { Button } from './components/Button/Button';
import { IssuePage } from '@containers/IssuePage/IssuePage';
import { InformationTag } from '@components/InformationTag/InformationTag';
import { Icon } from './components';

function App() {
	if (process.env.NODE_ENV === 'development') {
		const { worker } = require('./mocks/browser');
		worker.start();
	}

	const [isOpen, setIsOpen] = useState(false);
	const [selectedId, setSelectedId] = useState(false);
	return (
		<div className="App">
			<header className="App-header">
				<Dropdown
					isOpen={isOpen}
					btnOnClick={() => setIsOpen(!isOpen)}
					btnText="메롱"
					header="헤더더"
					hasRadioBtn={true}
					options={[
						{ id: 'adfsasdf', profile: 'dfadfsdfa', contents: 'test' },
						{ id: 'adfasdfxx', profile: 'ddd', contents: 'test2' },
					]}
					optionOnClick={({ currentTarget }) => {
						const id = currentTarget.getAttribute('id');
						setSelectedId(id);
					}}
					selectedId={selectedId}
				></Dropdown>
			</header>
			<IssuePage></IssuePage>
			<Button
				icon={<Icon name="plus"></Icon>}
				text="BUTTON"
				btnSize="l"
				color="blue"
			></Button>
			<Button text="BUTTON" btnSize="m" color="black"></Button>
			<Button text="BUTTON" btnSize="s" color="black"></Button>
			<InformationTag
				icon={<Icon name="alertCircle" fill="white"></Icon>}
				text="Label"
				bgColor="#007AFF"
				mode="light"
			></InformationTag>
			<InformationTag
				icon={<Icon name="archive" fill="white"></Icon>}
				text="Label"
				bgColor="red"
				mode="light"
			></InformationTag>
			<InformationTag text="Label" mode="neutral"></InformationTag>
		</div>
	);
}

export default App;
