import './App.module.css';
import { Dropdown } from '@components/Dropdown/Dropdown';
import { useState } from 'react';

export function Test() {
	const [isDropdownOpen, setDropdownOpen] = useState(false);
	const [selected, setSelected] = useState('adsfasdfasf');
	const handleDropdown = (isOpen) => {
		return () => setDropdownOpen(isOpen);
	};

	return (
		<>
			<Dropdown
				isOpen={isDropdownOpen}
				btnText={selected}
				hasRadioBtn={true}
				toggleOpen={handleDropdown(!isDropdownOpen)}
				options={[
					{ id: 'test', profile: 'adfsdkjfalksjdf', contents: 'adsfasdfasf' },
					{
						id: 'test2',
						profile: 'adfsdkjfalksjdf2',
						contents: 'adsfasdfasf2',
					},
				]}
				header={'헤더'}
				selected={selected}
				optionOnClick={({ currentTarget }) =>
					setSelected(currentTarget.innerText)
				}
			></Dropdown>
		</>
	);
}
