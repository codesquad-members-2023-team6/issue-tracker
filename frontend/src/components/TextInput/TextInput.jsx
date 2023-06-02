import { useState } from 'react';
import styles from './TextInput.module.css';
import classNames from 'classnames/bind';
import { Button, FileButton, Icon } from '@components/index';

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
  _onInput,
  _onKeyUp,
  tagName = 'input',
  type = 'text',
  errorMessage,
  hasFileUpload,
  showCaption,
  handleFileBtnOnChange,
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
            placeholder={placeholder}
            onChange={_onChange}
            onKeyDown={_onKeyDown}
            onInput={_onInput}
            onKeyUp={_onKeyUp}
          />
        ) : (
          <input
            className={InputClassName}
            type={type}
            id={id}
            value={value}
            placeholder={placeholder}
            onChange={_onChange}
            onKeyDown={_onKeyDown}
            onInput={_onInput}
            onKeyUp={_onKeyUp}
          />
        )}
        {isTextArea && showCaption && (
          <div className={captionClassName}>
            띄어쓰기 포함 {value.length}글자
          </div>
        )}
        {hasFileUpload && (
          <div className={bottomClassName}>
            <FileButton
              handleFileBtnOnChange={handleFileBtnOnChange}
            ></FileButton>
          </div>
        )}
      </div>
      {errorMessage && <p className={InputErrorClassName}>{errorMessage}</p>}
    </div>
  );
};
