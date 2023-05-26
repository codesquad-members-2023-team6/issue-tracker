export const OAUTH_CLIENT_ID =
  process.env.NODE_ENV === 'production'
    ? process.env.REACT_APP_OAUTH_CLIENT_ID_PROD
    : process.env.REACT_APP_OAUTH_CLIENT_ID_DEV;
