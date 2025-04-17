import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import Vehicle from "./Vehicle";
import axios from "./axiosConfig";

function AdminPage() {
  const [bookings, setBookings] = useState([]);
  const [users, setUsers] = useState([]);
  const [locations, setLocations] = useState([]);

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
  }, []);

  return (
    <div className="container">
      <h1 className="text-center text-info mt-4">Admin Dashboard / Home page </h1>

      {/* All Booking Cars */}
      <section className="mt-5">
        <div className="d-flex justify-content-between align-items-center">
          <h2>All Booking Cars</h2>
          <Link to="/booking/add" className="btn btn-info">Add Booking</Link>
        </div>
        <table className="table table-bordered mt-3">
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
                  <Link to={`/booking/edit/${booking.bookingId}`} className="btn btn-sm btn-info me-2">Edit</Link>
                  <button className="btn btn-sm btn-danger">Delete</button>
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
          <Link to="/user/add" className="btn btn-info">Add User</Link>
        </div>
        <table className="table table-bordered mt-3">
          <thead className="table-info">
            <tr>
              <th>ID</th>
              <th>Username</th>
              <th>Email</th>
              <th>Role</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {users.map(user => (
              <tr key={user.userId}>
                <td>{user.userId}</td>
                <td>{user.username}</td>
                <td>{user.email}</td>
                <td>{user.role}</td>
                <td>
                  <Link to={`/user/edit/${user.userId}`} className="btn btn-sm btn-info me-2">Edit</Link>
                  <button className="btn btn-sm btn-danger">Delete</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </section>

      {/* All Available Locations */}
      <section className="mt-5">
        <h2>All Available Locations</h2>
        <ul className="list-group mt-3">
          {locations.map(location => (
            <li key={location.locationId} className="list-group-item">
              {location.city}, {location.state}
            </li>
          ))}
        </ul>
      </section>
    </div>
  );
}

export default AdminPage;
