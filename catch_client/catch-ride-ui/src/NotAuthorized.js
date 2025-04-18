
const NotAuthorized = () => {
  return (
    <div className="jumbotron text-center" style={{backgroundImage: 'url(./401.jpg)', backgroundSize: 'cover', height: '100vh'}}>
    <h1 className="text-light display-4 p-2">You do not have permission to access this page.</h1>
    <a className="btn btn-secondary btn-lg p-2" href="/">Go to Home</a>
  </div>
  );
};

export default NotAuthorized;