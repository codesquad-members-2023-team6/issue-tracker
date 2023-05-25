import { useState } from 'react';
import { Profile, Button } from '@components/index';
import { MY_USER_DATA } from '@src/constants/user';
import classNames from 'classnames/bind';
import styles from './WritePage.module.css';
import { TextInput } from '@src/components/TextInput/TextInput';
import { debounce } from '@utils/index';
const cx = classNames.bind(styles);

export const WritePage = () => {
  const containerClassNames = `${cx('container')}`;
  const footerClassNames = `${cx('footer')}`;
  const sidebarClassNames = `${cx('sidebar')}`;
  const inputContainerClassNames = `${cx('input-container')}`;

  const [inputValue, setInputValue] = useState('');
  const [textareaValue, setTextareaValue] = useState('');
  const [showCaption, setShowCaption] = useState(false);
  const maxLength = 1000;

  const handleTextAreaOnKeyDown = () => {
    debounce(() => {
      setShowCaption(true);
    }, 0)();
  };

  const handleTextAreaOnKeyup = () => {
    const labelShowTime = 2000;
    debounce(() => {
      setShowCaption(false);
    }, labelShowTime)();
  };

  const handleTextAreaOnChange = ({ target }) => {
    const value = target.value;
    if (value.length >= maxLength) {
      return setTextareaValue(value.slice(0, maxLength));
    }
    setTextareaValue(value);
  };

  return (
    <>
      <div className={containerClassNames}>
        <Profile url={MY_USER_DATA.profile}></Profile>
        <div className={inputContainerClassNames}>
          <TextInput
            _onChange={(event) => setInputValue(event.target.value)}
            value={inputValue}
            placeholder="제목"
          ></TextInput>
          <TextInput
            tagName="textarea"
            style={{ height: '436px', alignItems: 'start', padding: '16px' }}
            hasFileUpload={true}
            label="코멘트를 입력하세요"
            _onChange={handleTextAreaOnChange}
            value={textareaValue}
            showCaption={showCaption}
            _onKeyDown={handleTextAreaOnKeyDown}
            _onKeyUp={handleTextAreaOnKeyup}
          ></TextInput>
        </div>
        <div className={sidebarClassNames}>옆에 있는거~~</div>
      </div>
      <div className={footerClassNames}>
        <Button
          type="ghost"
          width="fit-content"
          iconName="xSquare"
          btnSize="m"
          text="작성 취소"
        ></Button>
        <Button text="완료" color="blue"></Button>
      </div>
    </>
  );
};
