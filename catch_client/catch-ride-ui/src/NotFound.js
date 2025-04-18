function NotFound() {

    return (
      <div className="jumbotron text-center" style={{backgroundImage: 'url(./error.jpg)', backgroundSize: 'cover', height: '100vh'}}>
        {/* <h1 className="display-1 text-danger mb-4">404</h1> */}
        {/* <p className="fs-4 mb-4">Oops! Page not found.</p> */}
        <a className="btn btn-secondary btn-lg" href="/">Go to Home</a>
      </div>
    );
  }
  
  export default NotFound;
