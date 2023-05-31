import { customFetch } from './api';

export const getMilestone = async () => {
  const response = await customFetch({
    path: `/milestones`,
    method: 'GET',
  });
  return response;
};
