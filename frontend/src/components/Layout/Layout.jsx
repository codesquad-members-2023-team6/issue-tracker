import { Navbar } from '@components/index';
import { MY_USER_DATA } from '@src/constants/user';

import styles from './Layout.module.css';
import classNames from 'classnames/bind';

const cx = classNames.bind(styles);

export const Layout = ({ children, hideNavbar }) => {
  const contentsClassNames = `${cx('contents')}`;
  const contentsInlineStyles = {
    marginTop: hideNavbar ? '0px' : '32px',
    maxWidth: hideNavbar ? '100%' : 'var(--contents-width)',
    margin: hideNavbar ? '0' : '0 auto 24px',
    padding: hideNavbar ? '0' : '0 24px',
  };

  return (
    <>
      {!hideNavbar && <Navbar user={MY_USER_DATA}></Navbar>}
      <div className={contentsClassNames} style={contentsInlineStyles}>
        {children}
      </div>
    </>
  );
};
