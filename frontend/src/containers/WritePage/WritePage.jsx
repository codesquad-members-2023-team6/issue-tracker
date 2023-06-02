import { useState, useEffect } from 'react';
import { Profile, Button } from '@components/index';
import classNames from 'classnames/bind';
import styles from './WritePage.module.css';
import { WriteBox, Sidebox } from '@components/index';
import { postIssue } from '@services/issue';
import { useNavigate } from 'react-router-dom';
import { useContext, useReducer } from 'react';
import { storeContext } from '@stores/index';

const cx = classNames.bind(styles);

export const WritePage = () => {
  const containerClassNames = `${cx('container')}`;
  const footerClassNames = `${cx('footer')}`;
  const sidebarClassNames = `${cx('sidebar')}`;
  const inputContainerClassNames = `${cx('input-container')}`;

  const navigate = useNavigate();

  const [titleValue, setTitleValue] = useState('');
  const [contentsValue, setContentsValue] = useState('');
  const [assigneeValue, setAssigneeValue] = useState(null);
  const [milestoneValue, setMilestoneValue] = useState(null);
  const [labelValue, setLabelValue] = useState(null);
  const [isCTADisabled, setIsCTADisabled] = useState(true);
  const [user, userDispatch] = useContext(storeContext).user;

  const convertIndexKey = (data, key) => {
    const copiedData = { ...data, [`${key}Idx`]: data?.index };
    delete copiedData['index'];
    return copiedData;
  };

  const handleCTABtnOnClick = async () => {
    const issue = {
      title: titleValue,
      contents: Boolean(contentsValue) ? contentsValue : null,
      writer: user?.memberIdx,
      assignee: assigneeValue?.memberIdx,
      labels: labelValue ? [convertIndexKey(labelValue, 'label')] : [],
      milestone: milestoneValue ? milestoneValue : null,
    };

    await postIssue(issue);
    alert('글쓰기 완료!');
    navigate('/');
  };

  const enableCTAIfTextFiledFilled = () => {
    const isAllFilled = Boolean(titleValue);
    if (isAllFilled) {
      setIsCTADisabled(false);
    } else {
      setIsCTADisabled(true);
    }
  };

  useEffect(() => {
    enableCTAIfTextFiledFilled();
  }, [titleValue]);

  return (
    <>
      <div className={containerClassNames}>
        <Profile url={user.profileImageUrl}></Profile>
        <WriteBox
          hasTitle={true}
          titleState={[titleValue, setTitleValue]}
          contentsState={[contentsValue, setContentsValue]}
          setIsCTADisabled={setIsCTADisabled}
        />
        <div className={sidebarClassNames}>
          <Sidebox
            selectedAssigneeState={[assigneeValue, setAssigneeValue]}
            selectedLabelState={[labelValue, setLabelValue]}
            selectedMilstoneState={[milestoneValue, setMilestoneValue]}
          />
        </div>
      </div>
      <div className={footerClassNames}>
        <Button
          type="ghost"
          width="fit-content"
          iconName="xSquare"
          btnSize="m"
          text="작성 취소"
          _onClick={() => navigate(-1)}
        ></Button>
        <Button
          text="완료"
          color="blue"
          _onClick={handleCTABtnOnClick}
          status={isCTADisabled ? 'disabled' : 'default'}
        ></Button>
      </div>
    </>
  );
};
