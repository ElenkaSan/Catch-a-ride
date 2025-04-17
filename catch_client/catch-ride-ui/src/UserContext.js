// User Context will help to distribute data globally to all children
// We will use this to save our User Session through out the app by exporting it.

// import { createContext } from "react";

// const UserContext = createContext();

// export default UserContext;

import { createContext } from 'react';

const UserContext = createContext({
  isLoggedIn: null,
  setIsLoggedIn: () => {}
});

export default UserContext;