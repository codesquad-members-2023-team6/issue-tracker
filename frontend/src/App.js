import { Layout } from '@components/index';
import { Routes, Route, Navigate, BrowserRouter } from 'react-router-dom';
import { routes } from '@constants/routes';
import { getToken } from '@services/login';

const RequireAuth = ({ children }) => {
  const isLogin = !!getToken();
  if (!isLogin) {
    return <Navigate to="/login" replace />;
  }
  return children;
};

function App() {
  // if (process.env.NODE_ENV === 'development') {
  //   const { worker } = require('./mocks/browser');
  //   worker.start();
  // }

  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          {routes.map((route) => {
            const { path, element, auth, hideNavbar } = route;
            return (
              <Route
                key={path}
                path={path}
                element={
                  <Layout hideNavbar={hideNavbar}>
                    {auth ? <RequireAuth>{element}</RequireAuth> : element}
                  </Layout>
                }
              />
            );
          })}
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
