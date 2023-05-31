import { URL } from '@constants/api';
import { removeEmptyKeyValues } from '@utils/index';
import { getToken, logout } from '@services/login';

export const customFetch = async ({
  path,
  method,
  body,
  queries,
  hasAuth = true,
}) => {
  const cache = hasAuth ? 'no-store' : 'max-age=3600, private';
  const headers = {
    'Content-Type': 'application/json',
    'Cache-Control': cache,
  };

  if (hasAuth) {
    const token = getToken();
    if (!token) {
      throw Error('No Token');
      // TODO 로그아웃
    }
    headers.Authorization = `Bearer ${token}`;
  }

  let url = URL + path;
  if (queries) {
    const copiedQuries = removeEmptyKeyValues(queries);
    const queryString = copiedQuries
      ? '?' + new URLSearchParams(Object.entries(copiedQuries)).toString()
      : '';
    url += queryString;
  }

  try {
    const data = await fetch(url, {
      method,
      headers,
      body: JSON.stringify(body),
    });
    if (!data.ok) {
      throw Error(data.statusText);
    }
    const result = await data.json();
    if (!result) {
      throw Error('No data');
    }
    return result;
  } catch (error) {
    console.error(error);
    throw error;
  }
};
