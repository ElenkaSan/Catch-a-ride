import React from 'react';

const NotAuthorized = () => {
  return (
    <div className="container text-center mt-5">
      <h2 className="text-danger">🚫 Not Authorized</h2>
      <p>You do not have permission to access this page.</p>
    </div>
  );
};

export default NotAuthorized;