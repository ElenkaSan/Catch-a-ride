import React, { useEffect, useState } from "react";
import { Link, Navigate } from "react-router-dom";
import {
  Card,
  CardBody,
  Button,
  CardImg,
  CardTitle,
  CardText,
} from "reactstrap";
import { BsPencilSquare } from "react-icons/bs";
import { BiHomeHeart } from "react-icons/bi";
import useToggle from "./useToggle";
import UserInfoForm from "./UserInfoForm";

const UserPage = ({ updateUser }) => {
  const [isUpdate, setIsUpdate] = useToggle(false);
  const [bookings, setBookings] = useState([]);
  const [loading, setLoading] = useState(true);

  const [username, setUsername] = useState("");
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [email, setEmail] = useState("");

  const token = localStorage.getItem("token");
  const appUserId = localStorage.getItem("appUserId");

  useEffect(() => {
    setUsername(localStorage.getItem("username") || "");
    setFirstName(localStorage.getItem("firstName") || "");
    setLastName(localStorage.getItem("lastName") || "");
    setEmail(localStorage.getItem("email") || "");

    // Fetch bookings
    if (appUserId) {
      fetch(`http://localhost:8080/api/booking/user/${appUserId}`)
        .then((res) =>
          res.ok ? res.json() : Promise.reject("Failed to load bookings")
        )
        .then((data) => {
          setBookings(data);
          setLoading(false);
        })
        .catch((err) => {
          console.error(err);
          setLoading(false);
        });
    }
  }, [appUserId]);

  if (!token) {
    return <Navigate to="/login" replace />;
  }

  const renderBookedCars = () => (
    <div className="mt-4">
      <h3 className="text-center text-info">Hello, {firstName || username}!</h3>
      <h4 className="text-center text-light mb-4">You have booked these Cars:</h4>
      <div className="row justify-content-center">
        {bookings.map((booking) => (
          <div className="col-md-4 mb-4" key={booking.bookingId}>
            <Card className="shadow-sm rounded">
              <CardImg
                top
                src={booking.vehicle?.imageCar || "/logo.png"}
                alt="Car"
                className="img-fluid"
              />
              <CardBody>
                <CardTitle tag="h5" className="text-info">
                  {booking.vehicle?.make} {booking.vehicle?.model}
                </CardTitle>
                <CardText>
                  <strong>Year:</strong> {booking.vehicle?.year} <br />
                  <strong>Booked Date:</strong>{" "}
                  {new Date(booking.startDate).toLocaleDateString()}
                </CardText>
              </CardBody>
            </Card>
          </div>
        ))}
      </div>
    </div>
  );

  return (
    <section className="container">
      <Card className="text-center bg-dark text-white border-info">
        <CardBody>
          <div className="d-flex justify-content-between align-items-center">
            <h2 className="text-info">Welcome, {username}</h2>
            <div>
              <Link to="/update">
                <Button className="btn btn-outline-warning me-2">
                  <BsPencilSquare />
                </Button>
              </Link>
              <Link to="/">
                <Button className="btn btn-warning">
                  <BiHomeHeart />
                </Button>
              </Link>
            </div>
          </div>
          <hr />
          {isUpdate ? (
            <UserInfoForm updateUser={updateUser} setIsUpdate={setIsUpdate} />
          ) : (
            <>
              <div className="text-start mb-3">
                <h4 className="text-warning">
                  Full Name:{" "}
                  {firstName && lastName
                    ? `${firstName} ${lastName}`
                    : username}
                </h4>
                <h5 className="text-light">Email: {email || "N/A"}</h5>
              </div>
              {loading ? (
                <p className="text-light">Loading your bookings...</p>
              ) : bookings.length === 0 ? (
                <div className="alert alert-info mt-4">
                  <h5 className="mb-0">You have no cars booked yet.</h5>
                </div>
              ) : (
                renderBookedCars()
              )}
            </>
          )}
        </CardBody>
      </Card>
      <hr />
    </section>
  );
};

export default UserPage;
