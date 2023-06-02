import classNames from 'classnames/bind';
import styles from './SideboxElement.module.css';
import {
  Dropdown,
  InformationTag,
  Profile,
  Progress,
  Icon,
} from '@components/index';
import { useState, useEffect } from 'react';
import { getMember } from '@services/member';
import { getLabel } from '@services/label';
import { getMilestone } from '@services/milestone';
import { convertListToOptions } from '@services/dropdown';

const cx = classNames.bind(styles);

export const SideboxElement = ({
  style,
  selectedState,
  name,
  id,
  editable,
}) => {
  const sideboxElementClassNames = `${cx('sidebar-element')}`;

  const [isDropdownOpen, setDropdownOpen] = useState(false);
  const [selected, setSelected] = selectedState;
  const [list, setList] = useState([]);

  const options = convertListToOptions(
    list,
    id === 'assignee' ? 'name' : 'title'
  );

  useEffect(() => {
    (async () => {
      if (id === 'assignee') {
        const members = await getMember();
        setList(members);
      }
      if (id === 'label') {
        const labels = await getLabel();
        setList(labels);
      }
      if (id === 'milestone') {
        const milestones = await getMilestone();
        setList(milestones);
      }
    })();
  }, []);

  const handleDropdown = (isOpen) => {
    return () => setDropdownOpen(isOpen);
  };

  const getSelectedDataByText = (list, text) => {
    return list.find((element) => {
      const compare = id === 'assignee' ? element.name : element.title;
      return compare === text;
    });
  };

  const optionOnClick = ({ currentTarget }) => {
    const text = currentTarget.innerText;
    const target = getSelectedDataByText(list, text);
    setSelected(target);
  };

  const RequireSelectedData = ({ children }) => {
    if (!selected) return null;
    return children;
  };

  return (
    <div className={sideboxElementClassNames} style={{ ...style }}>
      <Dropdown
        panelPosition="left"
        isOpen={isDropdownOpen}
        toggleOpen={handleDropdown(!isDropdownOpen)}
        options={options}
        selected={selected}
        optionOnClick={optionOnClick}
        width="100%"
        editable={editable}
        btnComponent={
          <div className={cx('dropdown-btn')}>
            <div>{name}</div>
            {editable && (
              <Icon
                name="chevronDown"
                fill="var(--color-light-neutral-text-weak)"
              ></Icon>
            )}
          </div>
        }
      ></Dropdown>

      {id === 'assignee' && (
        <RequireSelectedData>
          <div className={cx('assignee')}>
            <Profile size={20} url={selected?.profileImageUrl} />
            <p className="typo-label">{selected?.name}</p>
          </div>
        </RequireSelectedData>
      )}

      {id === 'label' && (
        <RequireSelectedData>
          <div className={cx('label')}>
            <InformationTag
              {...selected}
              text={selected?.title}
            ></InformationTag>
          </div>
        </RequireSelectedData>
      )}

      {id === 'milestone' && (
        <RequireSelectedData>
          <div className={cx('milestone')}>
            <Progress
              percent={
                (selected?.closedIssueNum / selected?.totalIssueNum) * 100
              }
              label={selected?.title}
            />
          </div>
        </RequireSelectedData>
      )}
    </div>
  );
};
