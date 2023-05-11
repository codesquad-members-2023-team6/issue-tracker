import { rest } from 'msw';
export const handlers = [
	rest.get('https://api.example.com/users', (req, res, ctx) => {
		return res(
			ctx.status(200),
			ctx.json([
				{
					id: 1,
					name: 'John Doe',
				},
				{
					id: 2,
					name: 'Jane Doe',
				},
			])
		);
	}),

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
];
