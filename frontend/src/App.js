import { Layout } from '@components/index';
import {
  Routes,
  Route,
  Navigate,
  BrowserRouter,
  useNavigate,
} from 'react-router-dom';
import { routes } from '@constants/routes';
import { getToken } from '@services/login';
import { removeToken } from '@services/login';
import { StoreProvider } from './stores/StoreProvider';

const RequireAuth = ({ children }) => {
  const navigate = useNavigate();

  const token = getToken();
  if (!token) {
    removeToken();

    return <Navigate to="/login" replace />;
  }
  return children;
};

function App() {
  return (
    <div className="App">
      <StoreProvider>
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
      </StoreProvider>
    </div>
  );
}

export default App;
