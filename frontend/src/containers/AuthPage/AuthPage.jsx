import { useLocation, useNavigate } from 'react-router-dom';
import { getLoginToken } from '@services/login';
import { useEffect } from 'react';

export const AuthPage = () => {
  const location = useLocation();
  const navigate = useNavigate();
  const queryCode = new URLSearchParams(location.search).get('code');

  const runGetLoginTokenAPI = async () => {
    try {
      const data = await getLoginToken(queryCode);
      if (!data) throw Error('Fail to get loginToken');
      const token = data.token;
      window.localStorage.setItem('loginToken', token);
    } catch (error) {
      console.error(error);
    }
  };

  runGetLoginTokenAPI();

  return <></>;
};
