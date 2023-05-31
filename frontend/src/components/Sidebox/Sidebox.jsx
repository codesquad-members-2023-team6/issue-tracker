import classNames from 'classnames/bind';
import styles from './Sidebox.module.css';
import { SideboxElement } from './SideboxElement/SideboxElement';

const cx = classNames.bind(styles);

export const Sidebox = ({
  selectedAssigneeState,
  selectedLabelState,
  selectedMilstoneState,
  editable = true,
}) => {
  const sideboxClassNames = `${cx('sidebox')}`;
  const dropdownDatas = [
    {
      name: '담당자',
      id: 'assignee',
      selectedState: selectedAssigneeState,
    },
    {
      name: '레이블',
      id: 'label',
      selectedState: selectedLabelState,
    },
    {
      name: '마일스톤',
      id: 'milestone',
      selectedState: selectedMilstoneState,
    },
  ];

  return (
    <div className={sideboxClassNames}>
      {dropdownDatas.map((data, i) => (
        <SideboxElement
          style={{
            borderTop:
              i !== 0 ? '1px solid var(--color-light-neutral-border)' : 'none',
          }}
          key={data.name}
          {...data}
          selectedState={data.selectedState}
          editable={editable}
        ></SideboxElement>
      ))}
    </div>
  );
};
