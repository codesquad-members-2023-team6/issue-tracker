import './App.module.css';
import { IssuePage } from '@containers/IssuePage/IssuePage';

function App() {
	if (process.env.NODE_ENV === 'development') {
		const { worker } = require('./mocks/browser');
		worker.start();
	}

	return (
		<div className="App">
			<IssuePage></IssuePage>
		</div>
	);
}

export default App;
