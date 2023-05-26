import {
  LoginPage,
  AuthPage,
  IssuePage,
  ErrorPage,
  IssueDetail,
  WritePage,
} from '@containers/index';

export const DOMAIN =
  process.env.NODE_ENV === 'production'
    ? process.env.REACT_APP_PROD_URL
    : process.env.REACT_APP_DEV_URL;

export const routes = [
  { path: '/login', element: <LoginPage />, hideNavbar: true },
  { path: '/auth', element: <AuthPage />, hideNavbar: true },
  { path: '/', element: <IssuePage />, auth: true },
  { path: '*', element: <ErrorPage /> },
  {
    path: '/detail/:issueId',
    element: <IssueDetail />,
    auth: true,
  },
  { path: '/write', element: <WritePage /> },
];
