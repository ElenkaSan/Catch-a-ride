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
  const [getBookings, setBookings] = useState([]);
  const [getMessage, setMessage] = useState("");
  const [loading, setLoading] = useState(true);
  const url = "http://localhost:8080/api/booking";
  const [username, setUsername] = useState("");
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [email, setEmail] = useState("");

  const token = localStorage.getItem("token");
  const appUserId = localStorage.getItem("appUserId");
  const locationId = localStorage.getItem("locationId");
  console.log(locationId)
  useEffect(() => {
    setUsername(localStorage.getItem("username") || "");
    setFirstName(localStorage.getItem("firstName") || "");
    setLastName(localStorage.getItem("lastName") || "");
    setEmail(localStorage.getItem("email") || "");

    const fetchBookingInfo = async () => {
      try {
        const bookingRes = await fetch(`http://localhost:8080/api/booking/user/${appUserId}`);
        if (!bookingRes.ok) throw new Error("Failed to load bookings");
        const bookings = await bookingRes.json();

        const bookingsWithLocation = await Promise.all(
          bookings.map(async (booking) => {
            let userAddress = null;
            let dealershipAddress = null;
  
            try {
              const res = await fetch(`http://localhost:8080/api/location/${locationId}`);
              if (res.ok) userAddress = await res.json();
            } catch (e) {
              console.error("Failed to fetch user location", e);
            }

  
            try {
              const dealershipAddRes = await fetch(`http://localhost:8080/api/location/${booking.dealershipLocationId}`);
              if (dealershipAddRes.ok) dealershipAddress = await dealershipAddRes.json();
            } catch (e) {
              console.error("Failed to fetch dealership location", e);
            }
  
            return {
              ...booking,
              userAddress,
              dealershipAddress,
            };
          })
        );

        setBookings(bookingsWithLocation);
        setLoading(false);
      } catch (err) {
        console.error(err);
        setLoading(false);
      }
    }

    // Fetch bookings
    // if (appUserId) {
    //   fetch(`http://localhost:8080/api/booking/user/${appUserId}`)
    //     .then((res) =>
    //       res.ok ? res.json() : Promise.reject("Failed to load bookings")
    //     )
    //     .then((data) => {
    //       setBookings(data);
    //       setLoading(false);
    //     })
    //     .catch((err) => {
    //       console.error(err);
    //       setLoading(false);
    //     });
    // }

    
  if (appUserId) {
    fetchBookingInfo();
  }
  }, [appUserId]);

  const handleDeleteBooking = (bookingId) => {
    const booking = getBookings.find((b) => b.bookingId === bookingId);
    if(window.confirm(`Delete Booking on ${booking.startDate} to ${booking.endDate}?`))
        {const init = {
            method: "DELETE"
        };
        fetch(`${url}/${bookingId}`, init)
            .then((response) => {
                if (response.status === 204) {
                    const seeBooking = getBookings.filter(
                        (b) => b.bookingId !== bookingId);
                        setBookings(seeBooking);
                        setMessage(`Booking from ${booking.startDate} to ${booking.endDate} with ID #${bookingId} was successfully deleted!`);
                } else {
                    return Promise.reject(`Unexpected Status Code: ${response.status}`);
                }
            })
            .catch(console.log);
        }
    };

  if (!token) {
    return <Navigate to="/login" replace />;
  }

  const renderBookedCars = () => (
    <div className="mt-4">
      <h3 className="text-center text-info">Hello, {firstName || username}!</h3>
      <h4 className="text-center text-light mb-4">You have booked these Cars:</h4>
      <div className="row justify-content-center">
        {getBookings.map((booking) => (
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
                  {new Date(booking.startDate).toLocaleDateString()} <br />
                  <strong>Delivering from </strong> {booking.dealershipAddress?.address}, {booking.dealershipAddress?.city}, {booking.dealershipAddress?.state} <br />
                  <strong>to </strong> {booking.userAddress?.address}, {booking.userAddress?.city}, {booking.userAddress?.state} <br />
                </CardText>
                <div className="d-flex justify-content-between">
                  <Link
                    to={`/booking/edit/${booking.bookingId}`} 
                    state={{vehicleId: booking.vehicleId, userId: localStorage.getItem('appUserId'), dealershipLocationId: booking.dealershipLocationId}} 
                    className="btn btn-outline-warning btn-sm"
                  >
                    Edit
                  </Link>
                  <Button
                    color="danger"
                    size="sm"
                    onClick={() => handleDeleteBooking(booking.bookingId)}
                  >
                    Delete
                  </Button>
                </div>
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
              ) : getBookings.length === 0 ? (
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
