import httpClient from '../configurations/httpClient';
import { API } from '../configurations/configuration';
import { getToken } from './localStorageService';

export const getMyPosts = async (page) => {
  return await httpClient.get(API.MY_POST, {
    headers: {
      Authorization: `Bearer ${getToken()}`,
    },
    params: {
      currentPage: page,
      pageSize: 10,
    },
  });
};
