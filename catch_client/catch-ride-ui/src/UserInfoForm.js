import { useState, useEffect } from "react";
import { useNavigate, Link } from "react-router-dom";

const USER_DEFAULT = {
  firstName: "",
  lastName: "",
  email: "",
  address: "",
  locationId: "",
  appUserId: "",
};

const LOCATION_DEFAULT = {
  city: "",
  state: "",
  zipCode: "",
};

function UserInfoForm() {
  const [user, setUser] = useState(USER_DEFAULT);
  const [location, setLocation] = useState(LOCATION_DEFAULT);
  const [getMessage, setGetMessage] = useState("");
  const [error, setError] = useState([]);

  const navigate = useNavigate();
  const userId = localStorage.getItem("appUserId");
  const locationIdFromStorage = localStorage.getItem("locationId"); // same value as userId


  useEffect(() => {
    if (userId) {
      fetch(`http://localhost:8080/api/user/${userId}`)
        .then(res => res.ok ? res.json() : Promise.reject("Failed to fetch user"))
        .then(data => {
          setUser(data);
          return fetch(`http://localhost:8080/api/location/${locationIdFromStorage}`);
        })
        .then(res => res.ok ? res.json() : Promise.reject("Failed to fetch location"))
        .then(locationData => setLocation(locationData))
        .catch(err => {
          console.error(err);
          setError(["Could not load user info."]);
        });
    }
  }, [userId]);

  const handleUserChange = (e) => {
    setUser({ ...user, [e.target.name]: e.target.value });
  };

  const handleLocationChange = (e) => {
    setLocation({ ...location, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const [userRes, locationRes] = await Promise.all([
        fetch(`http://localhost:8080/api/user/${userId}`, {
          method: "PUT",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify(user),
        }),
        fetch(`http://localhost:8080/api/location/${user.locationId}`, {
          method: "PUT",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify(location),
        }),
      ]);

      if (userRes.status === 204 && locationRes.status === 204) {
        localStorage.setItem("zipCode", location.zipCode);
        setGetMessage("Your information was successfully updated!");
        setTimeout(() => {
          setGetMessage("");
          navigate("/");
          window.location.reload();
        }, 2000);
      } else {
        const userErr = await userRes.json();
        const locationErr = await locationRes.json();
        setError([...userErr, ...locationErr]);
      }
    } catch (err) {
      console.error(err);
      setError(["An unexpected error occurred."]);
    }
  };

  return (
    <div className="container mt-4">
      {getMessage && <div className="alert alert-success text-center">{getMessage}</div>}
      {error.length > 0 && (
        <div className="alert alert-danger">
          <strong>Errors:</strong>
          <ul>{error.map((err, idx) => <li key={idx}>{err}</li>)}</ul>
        </div>
      )}

      <h2 className="text-center text-info mb-4">Edit Personal Information</h2>
      <form onSubmit={handleSubmit}>
        {/* User Fields */}
        <div className="mb-3">
          <label htmlFor="firstName" className="form-label">First Name</label>
          <input type="text" className="form-control" id="firstName" name="firstName" value={user.firstName} onChange={handleUserChange} required />
        </div>
        <div className="mb-3">
          <label htmlFor="lastName" className="form-label">Last Name</label>
          <input type="text" className="form-control" id="lastName" name="lastName" value={user.lastName} onChange={handleUserChange} required />
        </div>
        <div className="mb-3">
          <label htmlFor="email" className="form-label">Email</label>
          <input type="email" className="form-control" id="email" name="email" value={user.email} onChange={handleUserChange} required />
        </div>


        {/* Location Fields */}
        <div className="mb-3">
          <label htmlFor="address" className="form-label">Address</label>
          <input type="text" className="form-control" id="address" name="address" value={location.address} onChange={handleLocationChange} required />
        </div>
        <div className="mb-3">
          <label htmlFor="city" className="form-label">City</label>
          <input type="text" className="form-control" id="city" name="city" value={location.city} onChange={handleLocationChange} required />
        </div>
        <div className="mb-3">
          <label htmlFor="state" className="form-label">State</label>
          <input type="text" className="form-control" id="state" name="state" value={location.state} onChange={handleLocationChange} required />
        </div>
        <div className="mb-3">
          <label htmlFor="zipCode" className="form-label">Zip Code</label>
          <input type="text" className="form-control" id="zipCode" name="zipCode" value={location.zipCode} onChange={handleLocationChange} required />
        </div>

        <div className="d-flex justify-content-between mt-4">
          <button type="submit" className="btn btn-warning">Save Updates</button>
          <Link to="/" className="btn btn-secondary">Cancel</Link>
        </div>
      </form>
    </div>
  );
}

export default UserInfoForm;