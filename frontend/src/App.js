import logo from './logo.svg';
import './App.module.css';

function App() {
	if (process.env.NODE_ENV === 'development') {
		const { worker } = require('./mocks/browser');
		worker.start();
	}

	async function fetchUsers() {
		const response = await fetch('https://api.example.com/users');
		const data = await response.json();
		console.log(data);
	}

	async function createUser(user) {
		const response = await fetch('https://api.example.com/users', {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json',
			},
			body: JSON.stringify(user),
		});
		const data = await response.json();
		return data;
	}

	fetchUsers();
	createUser({ id: 3, name: 'snoop' });
	fetchUsers();

	return (
		<div className="App">
			<header className="App-header">
				<img src={logo} className="App-logo" alt="logo" />
				<p>
					Edit <code>src/App.js</code> and save to reload.
				</p>
				<a
					className="App-link"
					href="https://reactjs.org"
					target="_blank"
					rel="noopener noreferrer"
				>
					Learn React
				</a>
			</header>
		</div>
	);
}

export default App;
