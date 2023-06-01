import styles from './Navbar.module.css';
import classNames from 'classnames/bind';
import imagefiles from '@assets/images/index';
import { Dropdown, Profile } from '@components/index';
import { useNavigate } from 'react-router-dom';
import { useContext, useEffect, useReducer, useState } from 'react';
import { logout } from '@services/login';
import { storeContext } from '@stores/index';

export const Navbar = () => {
  const navigate = useNavigate();
  const cx = classNames.bind(styles);
  const navClassNames = cx('navbar');
  const containerClassNames = cx('container');
  const LogoComponent = imagefiles['logoType'];
  const logoHeight = 40;

  const [user, userDispatch] = useContext(storeContext).user;

  const handleLogoClick = () => {
    navigate('/');
  };

  const handleProfileOpen = () => {
    setProfileOpen(!profileOpen);
  };

  const handleProfileOptionClick = () => {
    logout(navigate);
  };

  const [profileOpen, setProfileOpen] = useState(false);

  return (
    <div className={navClassNames}>
      <div className={containerClassNames}>
        <LogoComponent
          onClick={handleLogoClick}
          height={logoHeight}
        ></LogoComponent>
        <Dropdown
          btnComponent={<Profile url={user?.profileImageUrl}></Profile>}
          options={[{ index: 0, contents: '로그아웃' }]}
          toggleOpen={handleProfileOpen}
          isOpen={profileOpen}
          optionOnClick={handleProfileOptionClick}
        ></Dropdown>
      </div>
    </div>
  );
};
