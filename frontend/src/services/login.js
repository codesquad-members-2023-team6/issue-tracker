import { customFetch } from './api';

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
  const value = window.localStorage.getItem('loginToken');
  if (value === 'undefined') return null;
  return value;
};

export const getLoginToken = async (queryCode) => {
  const env = process.env.NODE_ENV === 'production' ? 'prod' : 'dev';

  try {
    const data = await customFetch({
      path: '/oauth/result',
      method: 'GET',
      queries: { code: queryCode, env },
    });
    return data;
  } catch (error) {
    return error;
  }
};
