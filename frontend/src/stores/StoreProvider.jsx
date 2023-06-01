import { initialState, reducer } from '@stores/user';
import { useContext, useReducer, useState } from 'react';
import { storeContext } from './index';

export const StoreProvider = ({ children }) => {
  const [userState, userDispatch] = useReducer(reducer, initialState);

  const rootReducer = {
    user: [userState, userDispatch],
  };

  return (
    <storeContext.Provider value={rootReducer}>
      {children}
    </storeContext.Provider>
  );
};
