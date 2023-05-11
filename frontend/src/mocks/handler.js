import { rest } from 'msw';
export const handlers = [
	rest.post('https://api.example.com/users', (req, res, ctx) => {
		const newUser = req.body;
		return res(
			ctx.status(201),
			ctx.json({
				message: 'User created successfully',
				user: newUser,
			})
		);
	}),

	rest.get('https://api.example.com/presslist', (req, res, ctx) => {
		const pressList = [
			{
				title: '새 기능 추가',
				comment: [
					{
						writer: {
							id: '1',
							profile: 'https://example.com/profile.png',
							name: 'John Doe',
						},
						contents: '새 기능 추가에 대한 의견입니다.',
					},
					{
						writer: {
							id: '2',
							profile: 'https://example.com/profile.png',
							name: 'Jane Doe',
						},
						contents: '새 기능 추가에 대한 또 다른 의견입니다.',
					},
				],
				writer: {
					id: '1',
					profile: 'https://example.com/profile.png',
					name: 'John Doe',
				},
				assignee: null,
				label: null,
				milestone: null,
				status: 'open',
				editedTime: new Date(),
				index: 0,
			},
			{
				title: '버그 수정',
				comment: [
					{
						writer: {
							id: '3',
							profile: 'https://example.com/profile.png',
							name: 'Peter Smith',
						},
						contents: '버그 수정에 대한 의견입니다.',
					},
				],
				writer: {
					id: '2',
					profile: 'https://example.com/profile.png',
					name: 'Jane Doe',
				},
				assignee: {
					id: '1',
					profile: 'https://example.com/profile.png',
					name: 'John Doe',
				},
				label: {
					title: '버그',
					description: '이것은 버그 레이블입니다.',
					backgroundColor: 'red',
					textColor: 'white',
				},
				milestone: null,
				status: 'open',
				editedTime: new Date(),
				index: 1,
			},
			{
				title: 'UI 개선',
				comment: [
					{
						writer: {
							id: '4',
							profile: 'https://example.com/profile.png',
							name: 'Mary Jones',
						},
						contents: 'UI 개선에 대한 의견입니다.',
					},
				],
				writer: {
					id: '3',
					profile: 'https://example.com/profile.png',
					name: 'Peter Smith',
				},
				assignee: null,
				label: {
					title: 'UI',
					description: '이것은 UI 레이블입니다.',
					backgroundColor: 'blue',
					textColor: 'white',
				},
				milestone: {
					id: '1',
					title: '마일스톤 1',
					endDate: new Date(),
					contents: '이것은 마일스톤 1의 내용입니다.',
					totalIssueNum: 10,
					closedIssueNum: 5,
					isClosed: false,
				},
				status: 'open',
				editedTime: new Date(),
				index: 2,
			},
			{
				title: '새로운 사용자 지정 필드 추가',
				comment: [
					{
						writer: {
							id: '5',
							profile: 'https://example.com/profile.png',
							name: 'David Jones',
						},
						contents: '새로운 사용자 지정 필드 추가에 대한 의견입니다.',
					},
				],
				writer: {
					id: '4',
					profile: 'https://example.com/profile.png',
					name: 'Mary Jones',
				},
				assignee: null,
				label: null,
				milestone: null,
				status: 'open',
				editedTime: new Date(),
				index: 3,
			},
			{
				title: '새로운 사용자 지정 필드 추가',
				comment: [
					{
						writer: {
							id: '5',
							profile: 'https://example.com/profile.png',
							name: 'David Jones',
						},
						contents: '새로운 사용자 지정 필드 추가에 대한 의견입니다.',
					},
				],
				writer: {
					id: '4',
					profile: 'https://example.com/profile.png',
					name: 'Mary Jones',
				},
				assignee: null,
				label: null,
				milestone: null,
				status: 'open',
				editedTime: new Date(),
				index: 3,
			},
			{
				title: '검색 기능 개선',
				comment: [
					{
						writer: {
							id: '6',
							profile: 'https://example.com/profile.png',
							name: 'Sarah Smith',
						},
						contents: '검색 기능 개선에 대한 의견입니다.',
					},
				],
				writer: {
					id: '5',
					profile: 'https://example.com/profile.png',
					name: 'David Jones',
				},
				assignee: null,
				label: null,
				milestone: null,
				status: 'open',
				editedTime: new Date(),
				index: 4,
			},
			{
				title: '새로운 보고서 템플릿 추가',
				comment: [
					{
						writer: {
							id: '7',
							profile: 'https://example.com/profile.png',
							name: 'Michael Anderson',
						},
						contents: '새로운 보고서 템플릿 추가에 대한 의견입니다.',
					},
				],
				writer: {
					id: '6',
					profile: 'https://example.com/profile.png',
					name: 'Sarah Smith',
				},
				assignee: null,
				label: null,
				milestone: null,
				status: 'open',
				editedTime: new Date(),
				index: 5,
			},
			{
				title: '새로운 기능에 대한 사용자 테스트 추가',
				comment: [
					{
						writer: {
							id: '8',
							profile: 'https://example.com/profile.png',
							name: 'John Doe',
						},
						contents: '새로운 기능에 대한 사용자 테스트에 대한 의견입니다.',
					},
				],
				writer: {
					id: '7',
					profile: 'https://example.com/profile.png',
					name: 'Michael Anderson',
				},
				assignee: null,
				label: null,
				milestone: null,
				status: 'open',
				editedTime: new Date(),
				index: 6,
			},
			{
				title: '새로운 기능에 대한 성능 테스트 추가',
				comment: [
					{
						writer: {
							id: '9',
							profile: 'https://example.com/profile.png',
							name: 'Jane Doe',
						},
						contents: '새로운 기능에 대한 성능 테스트에 대한 의견입니다.',
					},
				],
				writer: {
					id: '8',
					profile: 'https://example.com/profile.png',
					name: 'John Doe',
				},
				assignee: null,
				label: null,
				milestone: null,
				status: 'open',
				editedTime: new Date(),
				index: 7,
			},
		];
		return res(ctx.status(200), ctx.json(pressList));
	}),
];
