import styles from './Tab.module.css';
import classNames from 'classnames/bind';
import { Button } from '@components/index';

export const Tab = ({
  buttonDatas,
  active,
  _onClick,
  type,
  width = '160px',
}) => {
  const cx = classNames.bind(styles);
  const tabClassNames = `${cx('tab')} ${type && cx(type)}`;
  const isGhostType = type === 'ghost';

  const getBtnBgColor = (id, active) => {
    const isActive = id === active;

    if (isActive && !isGhostType) {
      return 'var(--color-light-neutral-background-bold)';
    }
    return 'inherit';
  };

  const getTextColor = (id, active) => {
    const isActive = id === active;

    if (isActive && isGhostType) {
      return 'var(--color-light-neutral-text-strong)';
    }
    return null;
  };

  const btnWidth = width;

  return (
    <div className={tabClassNames}>
      {buttonDatas.map((data) => {
        const text = `${data.text}(${data.count ?? ''})`;
        return (
          <Button
            style={{
              backgroundColor: getBtnBgColor(data.text, active),
            }}
            textColor={getTextColor(data.text, active)}
            key={data.text}
            text={text}
            width={btnWidth}
            iconName={data.icon ?? false}
            _onClick={_onClick}
            btnSize="m"
            type="ghost"
            id={data.text}
          ></Button>
        );
      })}
    </div>
  );
};
