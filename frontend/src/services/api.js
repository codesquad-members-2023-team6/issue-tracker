import { URL } from '@constants/api';
import { removeEmptyKeyValues } from '@utils/index';
import { getToken, logout } from '@services/login';

export const customFetch = async ({
  path,
  method,
  body,
  queries,
  hasAuth = true,
  options,
}) => {
  const cache = hasAuth ? 'no-store' : 'max-age=3600, private';
  let headers = {
    'Content-Type': 'application/json',
    'Cache-Control': cache,
  };

  if (options) {
    headers = {
      ...headers,
      ...options,
    };
  }

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
    const res = await fetch(url, {
      method,
      headers,
      body: JSON.stringify(body),
    });

    const result = await res.json();

    if (!result.status) {
      console.log(result.message);
    }

    const data = result.data;

    return data;
  } catch (error) {
    console.error(error);
    throw error;
  }
};
