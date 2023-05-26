import { useLocation, useNavigate } from 'react-router-dom';
import { getLoginToken } from '@services/login';
import { useEffect } from 'react';

export const AuthPage = () => {
  const location = useLocation();
  const navigate = useNavigate();
  const queryCode = new URLSearchParams(location.search).get('code');

  useEffect(() => {
    (async () => {
      const data = await getLoginToken(queryCode);
      const token = data.token;
      window.localStorage.setItem('loginToken', token);
      navigate('/');
    })();
  }, []);

  return <></>;
};
