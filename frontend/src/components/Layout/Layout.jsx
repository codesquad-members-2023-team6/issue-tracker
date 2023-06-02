import { Navbar } from '@components/index';
import styles from './Layout.module.css';
import classNames from 'classnames/bind';
import { storeContext } from '@stores/index';
import { useContext, useReducer, useEffect } from 'react';
import jwtDecode from 'jwt-decode';
import { getToken } from '@services/login';
import { useNavigate } from 'react-router-dom';

const cx = classNames.bind(styles);

export const Layout = ({ children, hideNavbar }) => {
  const contentsClassNames = `${cx('contents')}`;
  const contentsInlineStyles = {
    marginTop: hideNavbar ? '0px' : '32px',
    maxWidth: hideNavbar ? '100%' : 'var(--contents-width)',
    margin: hideNavbar ? '0' : '0 auto 24px',
    padding: hideNavbar ? '0' : '0 24px',
  };

  const navigate = useNavigate();

  const [user, userDispatch] = useContext(storeContext).user;

  const setLoginUserData = () => {
    const token = getToken();
    if (!token) {
      return navigate('/login');
    }

    const { userprofile } = jwtDecode(token);
    if (!userprofile) return;

    const loginUserProfile = {
      memberIdx: userprofile.memberIdx,
      id: userprofile.id,
      profileImageUrl: userprofile.profileImageUrl,
      name: userprofile.login,
    };

    userDispatch({ type: 'SET_USER', payload: loginUserProfile });
  };

  useEffect(() => {
    setLoginUserData();
  }, []);

  return (
    <>
      {!hideNavbar && <Navbar></Navbar>}
      <div className={contentsClassNames} style={contentsInlineStyles}>
        {children}
      </div>
    </>
  );
};
