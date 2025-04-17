import React, { useState } from "react";
import UserContext from "./UserContext";

const UserProvider = ({ children }) => {
    const storedUser = localStorage.getItem("user");
    const isValidJson = storedUser && storedUser !== "undefined";
    const [isLoggedIn, setIsLoggedIn] = useState(() => {
        return isValidJson ? JSON.parse(storedUser) : null;
    });

  return (
    <UserContext.Provider value={{ isLoggedIn, setIsLoggedIn }}>
      {children}
    </UserContext.Provider>
  );
};

export default UserProvider;