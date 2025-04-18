import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import Vehicle from "./Vehicle";
import axios from "./axiosConfig";

function AdminPage() {
  const [bookings, setBookings] = useState([]);
  const [users, setUsers] = useState([]);
  const [locations, setLocations] = useState([]);
  const [dealerships, setDealerships] = useState([]);

  useEffect(() => {
    axios.get("/api/booking")
      .then(res => setBookings(res.data))
      .catch(console.log);

    axios.get("/api/user")
      .then(res => setUsers(res.data))
      .catch(console.log);

    axios.get("/api/location")
      .then(res => setLocations(res.data))
      .catch(console.log);

    axios.get("/api/dealership")
      .then(res => setDealerships(res.data))
      .catch(console.log);
  }, []);

  const handleDeleteUser = (userId) => {
    if (window.confirm("Are you sure you want to delete this user?")) {
      axios.delete(`/api/user/${userId}`)
        .then(() => {
          setUsers(prev => prev.filter(u => u.userId !== userId));
        })
        .catch(err => {
          console.error("Failed to delete user:", err);
        });
    }
  };


  const handleDeleteDealership = (dealershipId) => {
    if (window.confirm("Are you sure you want to delete this dealership?")) {
      axios.delete(`/api/dealership/${dealershipId}`)
        .then(() => {
          setDealerships(prev => prev.filter(d => d.dealershipId !== dealershipId));
        })
        .catch(err => {
          console.error("Failed to delete dealership:", err);
        });
    }
  };

  return (
    <div className="container p-4 mt-4 justify-content-md-center">
      <h1 className="text-center text-success mt-4">Admin Dashboard / Home page </h1>

      {/* All Booking Cars */}
      <section className="mt-5">
        <div className="d-flex justify-content-between align-items-center">
          <h2>All Booking Cars</h2>
          <Link to="/booking/add" className="btn btn-outline-secondary btn-lg">Add Booking</Link>
        </div>
        <table className="table table-bordered mt-3 fs-4">
          <thead className="table-info">
            <tr>
              <th>ID</th>
              <th>User</th>
              <th>Vehicle</th>
              <th>Start</th>
              <th>End</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {bookings.map(booking => (
              <tr key={booking.bookingId}>
                <td>{booking.bookingId}</td>
                <td>{booking.userId}</td>
                <td>{booking.vehicleId}</td>
                <td>{booking.startDate}</td>
                <td>{booking.endDate}</td>
                <td>
                  <Link to={`/booking/edit/${booking.bookingId}`} className="btn btn-lg btn-success me-2">Edit</Link>
                  <button className="btn btn-lg btn-danger">Delete</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </section>

       {/* All Cars (reuse Vehicle component) */}
       <section className="mt-5">
        <h2 className=" mb-4">All Cars</h2>
        <Vehicle showAvailableOnly={false} />
      </section>

      {/* All Users */}
      <section className="mt-5">
        <div className="d-flex justify-content-between align-items-center">
          <h2>All Users</h2>
          <Link to="/user/add" className="btn btn-outline-secondary btn-lg">Add User</Link>
        </div>
        <table className="table table-bordered mt-3 fs-4">
          <thead className="table-info">
            <tr>
              <th>ID</th>
              <th>First Name</th>
              <th>Last Name</th>
              <th>Email</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {users.map(user => (
              <tr key={user.userId}>
                <td>{user.userId}</td>
                <td>{user.firstName}</td>
                <td>{user.lastName}</td>
                <td>{user.email}</td>
                <td>
                  <Link to={`/user/edit/${user.userId}`} className="btn btn-lg btn-success me-2">Edit</Link>
                  <button 
                    className="btn btn-lg btn-danger"
                    onClick={() => handleDeleteUser(user.userId)}
                  >
                    Delete
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </section>

      {/* All Available Locations */}
      <section className="mt-5">
        <h2>All Available Locations</h2>
        <ul className="list-group mt-3 fs-4">
          {locations.map(location => (
            <li key={location.locationId} className="list-group-item">
              {location.city}, {location.state}
            </li>
          ))}
        </ul>
       <br/>
      </section>
      {/* All Dealerships */}
      <section className="mt-5 p-2">
        <div className="d-flex justify-content-between align-items-center">
          <h2>All Dealerships</h2>
          <Link to="/dealership/add" className="btn btn-success btn-lg">Add Dealership</Link>
        </div>
        <table className="table table-bordered mt-3 fs-3">
          <thead className="table-dark">
            <tr>
              <th>ID</th>
              <th>Name</th>
              <th>Description</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {dealerships.map((dealership) => (
              <tr key={dealership.dealershipId}>
                <td>{dealership.dealershipId}</td>
                <td>{dealership.name}</td>
                <td>{dealership.description}</td>
                <td>
                  <Link
                    to={`/dealership/edit/${dealership.dealershipId}`}
                    className="btn btn-lg btn-info me-2"
                  >
                    Edit
                  </Link>
                  <button
                    className="btn btn-lg btn-danger"
                    onClick={() => handleDeleteDealership(dealership.dealershipId)}
                  >
                    Delete
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </section>

    </div>
  );
}

export default AdminPage;
