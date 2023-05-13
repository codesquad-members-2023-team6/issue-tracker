import { useState, useEffect } from 'react';

export const IssuePage = (props) => {
	const [data, setData] = useState([]);

	useEffect(() => {
		const fetchData = async () => {
			const response = await fetch('https://api.example.com/presslist');
			const data = await response.json();
			setData(data);
		};

		fetchData();
	}, []);

	return (
		<div>
			<ul>
				{data.map((issue, i) => (
					<li key={i}>{issue.title}</li>
				))}
			</ul>
		</div>
	);
};
