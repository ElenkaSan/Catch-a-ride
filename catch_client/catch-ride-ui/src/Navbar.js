import { Link, useNavigate } from "react-router-dom";
import DEFAULT from "./logo.png";

function Navbar() {
  const navigate = useNavigate();

  const token = localStorage.getItem("token");
  const roles = JSON.parse(localStorage.getItem("roles") || "[]");

  const isAdmin = roles.includes("ROLE_ADMIN");
  const isUser = roles.includes("ROLE_USER");

  const handleLogout = () => {
    localStorage.clear();
    navigate("/login");
  };

  return (
    <div>
      <nav className="navbar navbar-expand-xl navbar-dark bg-secondary">
        <div className="nav-link">
          <Link className="p-3 text-light nav-item" to={"/"}>
            Catch A Ride <img src={DEFAULT} alt="trip" style={{ height:'30px', width:'30px'}} />
          </Link>
          <Link className="p-5 text-light nav-item" to={"/vehicles"}>Available Cars</Link>
          {token && <Link className="p-5 text-light nav-item" to={"/booking/add"}>Add Booking</Link>}
          {!token && (
            <Link className="p-5 text-light nav-item" to={"/login"}>Login / Signup</Link>
          )}
          {isAdmin && (
            <>
              <Link className="p-5 text-light nav-item" to={"/admin"}>Admin Page</Link>
              <button className="btn btn-warning" onClick={handleLogout}>Logout</button>
            </>
          )}
          {isUser && !isAdmin && (
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

export default Navbar;