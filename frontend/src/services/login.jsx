export const logout = (navigate) => {
  window.localStorage.removeItem('loginToken');
  navigate('/login');
};

const checkIsLengthValid = (input, min, max) => {
  const length = input.length;
  return length >= min && length <= max;
};

export const checkValidation = (target, value) => {
  const MIN = 6;
  const MAX = target === 'id' ? 16 : 12;
  return checkIsLengthValid(value, MIN, MAX);
};

export const getToken = () => {
  return window.localStorage.getItem('loginToken');
};

export const getLoginToken = async (queryCode) => {
  try {
    const data = await customFetch({
      path: '/login/github',
      method: 'GET',
      queries: { code: queryCode },
    });
    return data;
  } catch (error) {
    return error;
  }
};
