export const initialState = {
  memberIdx: null,
  id: '',
  profileImageUrl: '',
  name: '',
};

const setUser = (state, payload) => {
  return { ...payload };
};

export const reducer = (state, { type, payload }) => {
  switch (type) {
    case 'SET_USER':
      return setUser(state, payload);
    default:
      throw new Error('user state: 타입이 지정되지 않았어요.');
  }
};

export default [initialState, reducer];
