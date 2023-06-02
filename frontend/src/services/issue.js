import { URL } from '@constants/api';
import { customFetch } from '@services/api';
import { createContext } from 'react';
import { removeEmptyKeyValues, deepCopy } from '@utils/index';

export const filterContext = createContext();
export const checkContext = createContext();
export const isCheckedContext = createContext();

export const getIssueDetail = async ({ issueId }) => {
  const response = await customFetch({
    path: `/issue/${issueId}`,
    method: 'GET',
  });

  if (response === null) {
    throw new Error('Response is null');
  }

  return response;
};

export const postIssue = async (issue) => {
  try {
    const response = await customFetch({
      path: `/issue`,
      method: 'POST',
      body: {
        ...issue,
      },
    });

    return response;
  } catch (error) {
    console.error(error);
    throw error;
  }
};

export const patchIssueStatus = async (issueId, status) => {
  try {
    const response = await customFetch({
      path: `/issue/${issueId}/status`,
      method: 'PATCH',
      body: {
        status,
      },
    });

    return response;
  } catch (error) {
    console.error(error);
    throw error;
  }
};

export const patchIssueTitle = async (issueId, inputValue) => {
  try {
    const response = await customFetch({
      path: `/issue/${issueId}/title`,
      method: 'PATCH',
      body: {
        title: inputValue,
      },
    });

    return response;
  } catch (error) {
    console.error(error);
    throw error;
  }
};

export const uploadFile = async (formData) => {
  try {
    const response = await fetch(`${URL}/resource`, {
      method: 'POST',
      body: formData,
    });

    if (!response.ok) {
      throw new Error('Network response was not ok');
    }
    const result = response.json();

    return result;
  } catch (error) {
    console.error('Error:', error);
  }
};

export const patchMultipleIssuesStatus = async (issueIds, status) => {
  try {
    const response = await customFetch({
      path: '/issue',
      method: 'PATCH',
      body: {
        issueIdx: issueIds,
        status: status,
      },
    });

    return response;
  } catch (error) {
    console.error(error);
    throw error;
  }
};

export const getIssueList = async ({
  status = 'open',
  page = 0,
  assignee,
  label,
  milestone,
  writer,
  commentBy,
}) => {
  try {
    const response = await customFetch({
      path: '/issue',
      method: 'GET',
      queries: {
        status,
        page,
        maxPageNum: 10,
        assignee,
        label,
        milestone,
        writer,
        commentBy,
      },
    });

    return response;
  } catch (error) {
    console.error(error);
    throw error;
  }
};

export const convertFilterToString = (filters) => {
  const copiedFilters = { ...filters };
  removeEmptyKeyValues(copiedFilters);
  const stringfiedFilters = Object.entries(copiedFilters)
    .map((filter) => {
      const [key, value] = filter;
      return `${key}:${value}`;
    })
    .join(', ');
  return stringfiedFilters;
};

export const updateCountsToTabInfo = (
  labelAndMilestoneInfo,
  labelLength,
  milestoneLength
) => {
  const counts = {
    label: labelLength,
    milestone: milestoneLength,
  };

  const copiedInfo = deepCopy(labelAndMilestoneInfo);
  copiedInfo.forEach((_, i) => {
    copiedInfo[i].count = counts[copiedInfo[i].id];
  });
  return copiedInfo;
};

export const isFilterApplied = (filters, initialFilter) =>
  JSON.stringify(filters) !== JSON.stringify(initialFilter);

export const getFileData = async (selectedFile) => {
  if (!selectedFile) {
    alert('파일이 선택되지 않았습니다.');
    return;
  }

  const MAX_FILE_SIZE = 104857600;
  const isFileSizeExceed = selectedFile.size >= MAX_FILE_SIZE;

  if (isFileSizeExceed) return alert('파일 크기 초과!');

  const formData = new FormData();
  formData.append('files', selectedFile, selectedFile.name);

  const res = await uploadFile(formData);
  return res.data;
};
