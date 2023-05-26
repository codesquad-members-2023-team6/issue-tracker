import { useState } from 'react';
import styles from './TextInput.module.css';
import classNames from 'classnames/bind';
import { Button, Icon } from '@components/index';

export const TextInput = ({
  size,
  states = 'initial',
  label,
  id,
  width,
  placeholder = '',
  style,
  icon,
  value,
  _onChange,
  _onKeyDown,
  tagName = 'input',
  type = 'text',
  errorMessage,
  hasFileUpload,
  showCaption,
}) => {
  const cx = classNames.bind(styles);

  const container = cx('container');
  const textInputMode = size === 'l' ? cx('size-l') : cx('size-s');
  const textInputState = states === 'initial' ? cx('initial') : cx('error');
  const isValueFilledWithLabel = label && Boolean(value);

  const isTextArea = tagName === 'textarea';

  const textInputClassName = `${cx(
    'textinput'
  )} ${textInputMode} ${textInputState} ${isTextArea ? cx('textarea') : ''}`;
  const LabelClassName = `${cx('typo-dropdown-header')} ${cx('label')} ${
    isValueFilledWithLabel ? cx('filled') : ''
  }`;
  const InputClassName = `${cx('typo-body')} ${cx('input')} ${
    label ? cx('with-label') : ''
  }`;
  const InputErrorClassName = `typo-label ${errorMessage ? cx('error') : ''}`;
  const placeholderColor = `var(--color-light-neutral-text-weak)`;
  const bottomClassName = `${cx('bottom')}`;
  const captionClassName = `${cx('caption')} typo-s`;
  const fileContainerClassName = `${cx('file-container')}`;

  return (
    <div className={container}>
      <div style={{ width, ...style }} className={textInputClassName}>
        {label && (
          <label className={LabelClassName} htmlFor={id}>
            {label}
          </label>
        )}
        {icon && <Icon name={icon} fill={placeholderColor}></Icon>}
        {isTextArea ? (
          <textarea
            className={InputClassName}
            type={type}
            id={id}
            value={value}
            onChange={_onChange}
            onKeyDown={_onKeyDown}
            placeholder={placeholder}
          />
        ) : (
          <input
            className={InputClassName}
            type={type}
            id={id}
            value={value}
            onChange={_onChange}
            onKeyDown={_onKeyDown}
            placeholder={placeholder}
          />
        )}
        {isTextArea && showCaption && (
          <div className={captionClassName}>
            띄어쓰기 포함 {value.length}글자
          </div>
        )}
        {hasFileUpload && (
          <div className={bottomClassName}>
            <div className={fileContainerClassName}>
              <Button
                type="ghost"
                iconName="paperclip"
                text="파일 첨부하기"
                btnSize="s"
                width="fit-content"
                style={{ padding: '0' }}
              ></Button>
            </div>
          </div>
        )}
      </div>
      {errorMessage && <p className={InputErrorClassName}>{errorMessage}</p>}
    </div>
  );
};
