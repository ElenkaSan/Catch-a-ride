import { Link, useNavigate } from "react-router-dom";
import DEFAULT from "./logo.png";

function Navbar() {
  const navigate = useNavigate();

<<<<<<< HEAD
  const token = localStorage.getItem("token");
  const roles = JSON.parse(localStorage.getItem("roles") || "[]");

=======
  const roles = JSON.parse(localStorage.getItem("roles") || "[]");
>>>>>>> c83089e40d12179f8c16cce0ea4e7130b95082d1
  const isAdmin = roles.includes("ROLE_ADMIN");
  const isUser = roles.includes("ROLE_USER");

  const handleLogout = () => {
<<<<<<< HEAD
    localStorage.clear();
=======
    localStorage.removeItem("user");
    localStorage.removeItem("roles");
>>>>>>> c83089e40d12179f8c16cce0ea4e7130b95082d1
    navigate("/login");
  };

  return (
    <div>
      <nav className="navbar navbar-expand-xl navbar-dark bg-secondary">
        <div className="nav-link">
          <Link className="p-3 text-light nav-item" to={"/"}>
<<<<<<< HEAD
            Catch A Ride <img src={DEFAULT} alt="trip" style={{ height:'30px', width:'30px'}} />
          </Link>
          <Link className="p-5 text-light nav-item" to={"/vehicles"}>Available Cars</Link>
          {token && <Link className="p-5 text-light nav-item" to={"/booking/add"}>Add Booking</Link>}
          {!token && (
            <Link className="p-5 text-light nav-item" to={"/login"}>Login / Signup</Link>
          )}
          {isAdmin && (
=======
            Catch A Ride <img src={DEFAULT} alt="trip" style={{ height: '30px', width: '30px' }} />
          </Link>
          <Link className="p-5 text-light nav-item" to={"/vehicles"}>Available Cars</Link>
          <Link className="p-5 text-light nav-item" to={"/booking/add"}>Add Booking</Link>

          {/* If not logged in, show login link */}
          {!localStorage.getItem("user") && (
            <Link className="p-5 text-light nav-item" to={"/login"}>Login / Signup</Link>
          )}

          {/* If logged in and admin, show admin page */}
          {localStorage.getItem("user") && isAdmin && (
>>>>>>> c83089e40d12179f8c16cce0ea4e7130b95082d1
            <>
              <Link className="p-5 text-light nav-item" to={"/admin"}>Admin Page</Link>
              <button className="btn btn-warning" onClick={handleLogout}>Logout</button>
            </>
          )}
<<<<<<< HEAD
          {isUser && !isAdmin && (
=======

          {/* If logged in and regular user, show user page */}
          {localStorage.getItem("user") && isUser && (
>>>>>>> c83089e40d12179f8c16cce0ea4e7130b95082d1
            <>
              <Link className="p-5 text-light nav-item" to={"/user"}>User Page</Link>
              <button className="btn btn-warning" onClick={handleLogout}>Logout</button>
            </>
          )}
        </div>
      </nav>
    </div>
  );
}

<<<<<<< HEAD
export default Navbar;
=======
export default Navbar;
>>>>>>> c83089e40d12179f8c16cce0ea4e7130b95082d1
