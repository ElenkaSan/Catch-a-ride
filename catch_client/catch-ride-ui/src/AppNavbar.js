import './App.css';
import React from "react";
import { Link, useNavigate } from "react-router-dom";
import { Navbar, Nav, NavItem } from "reactstrap";
import DropdownButton from "react-bootstrap/DropdownButton";
import Dropdown from "react-bootstrap/Dropdown";
import { BiSortDown } from 'react-icons/bi';
import { NavLink } from "react-router-dom";
import defaultImg from "./logo.png"; 
import { PersonRolodex } from "react-bootstrap-icons";
import { PersonCircle } from "react-bootstrap-icons";
import { BoxArrowInRight } from "react-bootstrap-icons";
import { PersonPlus } from "react-bootstrap-icons";
import { BiLogOut } from 'react-icons/bi';

function AppNavbar() {
  const navigate = useNavigate();
  const token = localStorage.getItem("token");
  const roles = JSON.parse(localStorage.getItem("roles") || "[]");

  const isLoggedIn = !!token;
  const isAdmin = roles.includes("ROLE_ADMIN");
  const isUser = roles.includes("ROLE_USER");

  console.log("appUserId in navbar:", localStorage.getItem("appUserId"));

  const handleLogout = () => {
    localStorage.clear();
    navigate("/");
  };

  return (
    <Navbar expand="md" className="px-4 py-2">
      <NavLink to="/" className="navbar-brand text-light d-flex align-items-center lead p-2">
        <h3 className='fs-2'>Catch A Ride
          <img src={defaultImg} alt="logo" style={{ height: "40px", width: "40px", marginLeft: "10px" }} />
        </h3> 
      </NavLink>

      <Nav className="ml-auto d-flex align-items-center" navbar>
        {/* Dropdown Menu for Small Screens */}
        <DropdownButton
          variant="warning"
          className="me-3 d-md-none custom-dropdown-bg"
          title={<span className="text-light fs-5"><BiSortDown size={24} /></span>}
          id="dropdown-menu-align-right"
          drop="down"
          align="end"
          flip={true}
          autoClose="outside"
        >
          <Dropdown.Item as={Link} to="/vehicles">
            Available Cars
          </Dropdown.Item>

          {/* <Dropdown.Item as={Link} to="/booking/add">
            Add Booking
          </Dropdown.Item> */}

          {!isLoggedIn && (
            <>
              <Dropdown.Item as={Link} to="/login">
                <BoxArrowInRight size={20} style={{ marginRight: "10px" }} />
                Login
              </Dropdown.Item>
              <Dropdown.Item as={Link} to="/register">
                <PersonPlus size={20} style={{ marginRight: "10px" }} />
                Signup
              </Dropdown.Item>
            </>
          )}


          {isLoggedIn && isUser && (
            <>
              <Dropdown.Item as={Link} to="/user">
                <PersonCircle size={20} style={{ marginRight: "10px" }} />
                User Page
              </Dropdown.Item>
            </>
          )}

          {isLoggedIn && isAdmin && (
            <Dropdown.Item as={Link} to="/admin">
              <PersonRolodex size={20} style={{ marginRight: "10px" }} />
              Admin Page
            </Dropdown.Item>
          )}

          {isLoggedIn && (
            <Dropdown.Item onClick={handleLogout}>
              <BiLogOut size={20} style={{ marginRight: "10px" }} />
              Logout
            </Dropdown.Item>
          )}
        </DropdownButton>

        {/* Inline NavItems for Large Screens */}
        <div className="d-none d-md-flex"> {/* Hide on small screens */}
          <NavItem>
            <Link to="/vehicles" className="nav-link text-light px-3 fs-4 p-2">Available Cars</Link>
          </NavItem>

          {/* <NavItem>
            <Link to="/booking/add" className="nav-link text-light px-3">Add Booking</Link>
          </NavItem> */}

          {!isLoggedIn && (
            <>
              <NavItem>
                <Link to="/login" className="nav-link text-light px-3 fs-4 p-2">
                  <BoxArrowInRight size={20} style={{ marginRight: "10px" }} />
                  Login
                </Link>
              </NavItem>
              <NavItem>
                <Link to="/register" className="nav-link text-light px-3 fs-4 p-2">
                  <PersonPlus size={20} style={{ marginRight: "10px" }} />
                  Signup
                </Link>
              </NavItem>
            </>
          )}

          {isLoggedIn && isUser && (
            <NavItem>
              <Link to="/user" className="nav-link text-light px-3 fs-4 p-2">
              <PersonCircle size={20} style={{ marginRight: "10px" }} />
              User Page</Link>
            </NavItem>
          )}

          {isLoggedIn && isAdmin && (
            <NavItem>
              <Link to="/admin" className="nav-link text-light px-3 fs-4 p-2">
              <PersonRolodex size={20} style={{ marginRight: "10px" }} />
              Admin Page</Link>
            </NavItem>
          )}

          {isLoggedIn && (
            <NavItem className='px-3 fs-4 p-2'>
              <button onClick={handleLogout} className="btn btn-lg btn-outline-warning">
              <BiLogOut size={25} style={{ marginRight: "10px" }} />
              Logout
              </button>
            </NavItem>
          )}
        </div>
      </Nav>
    </Navbar>
  );
}

export default AppNavbar;
