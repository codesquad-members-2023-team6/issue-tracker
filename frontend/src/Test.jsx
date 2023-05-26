import { Dropdown, Tab, Button, InformationTag } from '@components/index';
import { IssuePage, IssueElement } from '@containers/index';
import { useState } from 'react';

export function Test() {
  const [isDropdownOpen, setDropdownOpen] = useState(false);
  const [selected, setSelected] = useState('selected option1');
  const handleDropdown = (isOpen) => {
    return () => setDropdownOpen(isOpen);
  };
  const [active, setActive] = useState(null);

  return (
    <div>
      <Dropdown
        isOpen={isDropdownOpen}
        btnText={selected}
        hasRadioBtn={true}
        toggleOpen={handleDropdown(!isDropdownOpen)}
        options={[
          {
            id: 'test',
            profile:
              'https://s3.ap-northeast-2.amazonaws.com/image.themiilk.com/production/reporters/183650d33f1/e5c5004c6e_1663847761.png',
            contents: 'selected option1',
          },
          {
            id: 'test2',
            profile: 'https://assets.themiilk.com/test/test-profile1.png',
            contents: 'selected option2',
          },
        ]}
        header={'헤더'}
        selected={selected}
        optionOnClick={({ currentTarget }) =>
          setSelected(currentTarget.innerText)
        }
      ></Dropdown>

      <Tab
        buttonDatas={[
          { text: 'LEFT', icon: 'plus' },
          { text: 'RIGHT', icon: 'plus' },
        ]}
        active={active}
        _onClick={({ currentTarget }) => setActive(currentTarget.innerText)}
      ></Tab>
      <Button iconName="plus" text="BUTTON" btnSize="l" color="blue"></Button>
      <Button text="BUTTON" btnSize="m" color="black"></Button>
      <Button text="BUTTON" btnSize="s" color="black"></Button>
      <InformationTag
        iconName="alertCircle"
        text="Label"
        bgColor="#007AFF"
        mode="light"
      ></InformationTag>
      <InformationTag
        iconName="archive"
        text="Label"
        bgColor="red"
        mode="light"
      ></InformationTag>
      <InformationTag text="Label" mode="neutral"></InformationTag>
      <IssuePage></IssuePage>
      <IssueElement
        iconName="alertCircle"
        title="이슈제목"
        label="레이블 이름"
        issueNumber="#3"
        timeStamp="2023-05-15"
        writer="sarang_daddy"
        mileStone="마일스톤"
        profile="https://assets.themiilk.com/test/test-profile1.png"
      ></IssueElement>
    </div>
  );
}
