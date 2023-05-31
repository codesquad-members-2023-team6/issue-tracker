import { Button } from '@components/index';
import imagefiles from '@assets/images/index';
import { TextInput } from '@src/components/TextInput/TextInput';
import classNames from 'classnames/bind';
import styles from './LoginPage.module.css';
import { useEffect, useState } from 'react';
import { checkValidation } from '@services/login';
import { DOMAIN } from '@constants/routes';
import { OAUTH_CLIENT_ID } from '@constants/login';
import { useNavigate } from 'react-router-dom';
const cx = classNames.bind(styles);

export const LoginPage = () => {
  const LogoComponent = imagefiles['logoType'];
  const loginPageClassNames = `${cx('login-page')}`;
  const containerClassNames = `${cx('container')}`;
  const navigate = useNavigate();

  const handleLoginBtnClick = () => {
    const scope = 'user';
    const redirectUri = `${DOMAIN}/auth`;
    const clientId = OAUTH_CLIENT_ID;

    if (process.env.NODE_ENV === 'production') {
      window.location.href = `https://github.com/login/oauth/authorize?response_type=code&redirect_uri=${redirectUri}&client_id=${clientId}&scope=${scope}`;
    } else {
      localStorage.setItem(
        'loginToken',
        'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJnaXRodWJfbG9naW5fbWVtYmVyIiwiZXhwIjoxNjg1NTU2MjUzLCJ1c2VycHJvZmlsZSI6eyJsb2dpbiI6InJlYWxzbm9vcHNvIiwiYXZhdGFyX3VybCI6Imh0dHBzOi8vYXZhdGFycy5naXRodWJ1c2VyY29udGVudC5jb20vdS85NjM4MTIyMT92PTQiLCJpZCI6OTYzODEyMjF9fQ.ZYXap4sraFYLD6jp42xPEiHtXU-fc7qDcIBDnHfJvU8'
      );
      navigate('/');
    }
  };
  const logoHeight = 40;

  const githubLoginProps = {
    width: '100%',
    text: 'GitHub 계정으로 로그인',
  };

  const [idValue, setIdValue] = useState('');
  const [passwordValue, setPasswordValue] = useState('');
  const [isCTADisabled, setIsCTADisabled] = useState(true);
  const [error, setError] = useState({ id: false, password: false });

  const handleIdInputChange = (event) => {
    setIdValue(event.target.value);
  };

  const handlePasswordInputChange = (event) => {
    setPasswordValue(event.target.value);
  };

  const enableCTAIfTextFiledFilled = () => {
    const isAllFilled = Boolean(idValue) && Boolean(passwordValue);
    if (isAllFilled) {
      setIsCTADisabled(false);
    } else {
      setIsCTADisabled(true);
    }
  };

  const handleTextInputError = (target) => {
    const targetValue = target === 'id' ? idValue : passwordValue;
    if (Boolean(targetValue) && !checkValidation(target, targetValue))
      setError({ ...error, [target]: true });
    else setError({ ...error, [target]: false });
  };

  useEffect(() => {
    handleTextInputError('id');
  }, [idValue]);

  useEffect(() => {
    handleTextInputError('password');
  }, [passwordValue]);

  useEffect(() => {
    enableCTAIfTextFiledFilled();
  }, [idValue, passwordValue]);

  return (
    <div className={loginPageClassNames}>
      <LogoComponent height={logoHeight}></LogoComponent>
      <div className={containerClassNames}>
        <Button {...githubLoginProps} _onClick={handleLoginBtnClick}></Button>
        <p>or</p>
        <TextInput
          label="아이디"
          size="l"
          value={idValue}
          _onKeydown={() => setIdValue(idValue)}
          _onChange={handleIdInputChange}
          errorMessage={
            error.id
              ? '아이디는 최소 6자리에서 최대 16자리까지 입력할 수 있습니다.'
              : false
          }
        ></TextInput>
        <TextInput
          label="패스워드"
          size="l"
          type="password"
          value={passwordValue}
          _onChange={handlePasswordInputChange}
          errorMessage={
            error.password
              ? '비밀번호는 최소 6자리에서 12자리까지 입력할 수 있습니다.'
              : false
          }
        ></TextInput>
        <Button
          width="100%"
          color="blue"
          text="아이디로 로그인"
          status={isCTADisabled ? 'disabled' : 'default'}
        ></Button>
        <Button
          width="100%"
          text="회원가입"
          type="ghost"
          btnSize="s"
          iconName="plus"
        ></Button>
      </div>
    </div>
  );
};
