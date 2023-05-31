import { useLocation, useNavigate } from 'react-router-dom';
import { getLoginToken, removeToken } from '@services/login';
import { useEffect } from 'react';

export const AuthPage = () => {
  const location = useLocation();
  const navigate = useNavigate();
  const queryCode = new URLSearchParams(location.search).get('code');

  const runGetLoginTokenAPI = async () => {
    try {
      const data = await getLoginToken(queryCode);
      const token = data.token;
      if (!token) {
        throw Error('로그인 실패');
      }

      window.localStorage.setItem('loginToken', token);
      navigate('/');
    } catch (error) {
      alert(error);
      removeToken();
      navigate('/login');
      console.error(error);
    }
  };

  runGetLoginTokenAPI();

  return <></>;
};
