import { Icon, Tab } from '@components/index';
import styles from './IssueListHeader.module.css';
import classNames from 'classnames/bind';
import { FilterElement } from './IssueElement/FilterElement/FilterElement';
import { FILTER_KEYS, initialFilter } from '@constants/issue';
import { useContext, useEffect, useState } from 'react';
import { filterContext } from '@src/services/issue';
import { isFilterApplied } from '@services/issue';

export const IssueListHeader = ({
  userList,
  milestoneList,
  assigneeList,
  issueCount,
  labelList,
}) => {
  const cx = classNames.bind(styles);

  const [filters, setFilters] = useContext(filterContext);

  const convertListToOptions = (list, contentsKey) => {
    return list.map((element) => {
      const option = {};
      if (element.profile) {
        option.profile = element.profile;
      }

      option.contents = element[contentsKey];

      if (element.index === parseInt(-1)) {
        option.index = element.index;
      }

      option.index = element.index;
      return option;
    });
  };

  const userOptions = convertListToOptions(userList, 'name');
  const assigneeOptions = convertListToOptions(assigneeList, 'name');
  const labelOptions = convertListToOptions(labelList, 'title');
  const milestoneOptions = convertListToOptions(milestoneList, 'title');

  const filterInfos = [
    {
      name: '담당자',
      key: FILTER_KEYS.ASSIGNEE,
      data: assigneeOptions,
      selectedOption: filters[FILTER_KEYS.ASSIGNEE],
    },
    {
      name: '레이블',
      key: FILTER_KEYS.LABEL,
      data: labelOptions,
      selectedOption: filters[FILTER_KEYS.LABEL],
    },
    {
      name: '마일스톤',
      key: FILTER_KEYS.MILESTONE,
      data: milestoneOptions,
      selectedOption: filters[FILTER_KEYS.MILESTONE],
    },
    {
      name: '작성자',
      key: FILTER_KEYS.WRITER,
      data: userOptions,
      selectedOption: filters[FILTER_KEYS.WRITER],
    },
  ];

  const statusTabDatas = [
    {
      text: '열린 이슈',
      filterValue: 'open',
      icon: 'alertCircle',
      count: issueCount.open,
    },
    {
      text: '닫힌 이슈',
      filterValue: 'close',
      icon: 'archive',
      count: issueCount.closed,
    },
  ];

  const initialActiveTab = statusTabDatas[0].text;
  const [activeTab, setActiveTab] = useState(initialActiveTab);

  useEffect(() => {
    if (!isFilterApplied(filters, initialFilter)) {
      setActiveTab(initialActiveTab);
    }
  }, [filters]);

  const handleTabOnClick = ({ currentTarget }) => {
    const targetData = statusTabDatas.find(
      (data) => currentTarget.id === data.text
    );
    const filterValue = targetData.filterValue;
    const text = targetData.text;
    setFilters({ ...filters, status: filterValue });
    setActiveTab(text);
  };

  return (
    <>
      <div className={cx(`header`)}>
        <div className={cx(`check-box`)}>
          <input type="checkbox"></input>
        </div>
        <div className={cx(`header-contents`)}>
          <Tab
            buttonDatas={statusTabDatas}
            type="ghost"
            width="fit-content"
            active={activeTab}
            _onClick={handleTabOnClick}
          ></Tab>
          <div className={cx(`issue-contents_column`)}>
            {filterInfos.map((info) => (
              <FilterElement
                key={info.name}
                filterName={info.name}
                filterKey={info.key}
                options={info.data}
                selectedOption={info.selectedOption}
              ></FilterElement>
            ))}
          </div>
        </div>
      </div>
    </>
  );
};
