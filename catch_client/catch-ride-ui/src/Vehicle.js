
import { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom"; 
import axios from './axiosConfig';
import defaultImg from "./logo.png";

function Vehicle({ showAvailableOnly = false }) {
  const [getVehicles, setGetVehicles] = useState([]);
  const [getMessage, setGetMessage] = useState("");
  const [filterByZip, setFilterByZip] = useState(false);
  const [userZip, setUserZip] = useState(null);
  const navigate = useNavigate(); 
  const [isAdmin, setIsAdmin] = useState(false);
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  const fetchVehicles = async () => {
    try {
      if (filterByZip && userZip) {
        const response = await fetch(`http://localhost:8080/api/vehicle/zipcode/${userZip}`);
        const data = await response.json();
        
        if (!Array.isArray(data)) {
          console.error("Expected an array but got:", data);
          setGetVehicles([]);
          setGetMessage("Unexpected response format.");
          return;
        }
        if (data.length === 0) {
          setGetMessage("Sorry but there are no cars available in that zip code.");
        } else {
          setGetMessage("");
        }
        const vehiclesToShow = showAvailableOnly
          ? data.filter((v) => !v.bookingStatus)
          : data;
        setGetVehicles(vehiclesToShow);
      } else {
        const response = await fetch("http://localhost:8080/api/vehicle");
        const data = await response.json();
        
        if (!Array.isArray(data)) {
          console.error("Expected an array but got:", data);
          setGetVehicles([]);
          setGetMessage("Unexpected response format.");
          return;
        }
        const vehiclesToShow = showAvailableOnly
          ? data.filter((v) => !v.bookingStatus)
          : data;
        setGetVehicles(vehiclesToShow);
        setGetMessage(""); // Reset message if not filtering
      }
    } catch (err) {
      console.error("Fetch error:", err);
      setGetMessage("Error loading vehicles.");
    }
  };

  const toggleZipFilter = async () => {
    const newFilterState = !filterByZip;

    if (newFilterState) {
      const locationId = localStorage.getItem("locationId");
      if (locationId) {
        try {
          const res = await fetch(`http://localhost:8080/api/location/${locationId}`);
          const data = await res.json();
          setUserZip(data.zipCode);
        } catch (err) {
          console.error("Error fetching location:", err);
          setGetMessage("Failed to retrieve your zip code.");
          return;
        }
      } else {
        setGetMessage("You must be logged in to use zip filtering.");
        return;
      }
    }

    setFilterByZip(newFilterState);
  };

  useEffect(() => {
    const token = localStorage.getItem("token");
    const role = localStorage.getItem("roles");
    setIsLoggedIn(!!token);
    setIsAdmin(role === 'admin');
  }, []);

  useEffect(() => {
    fetchVehicles();
  }, [filterByZip, showAvailableOnly, userZip]);

  const handleBookClick = (vehicleId) => {
    if (!isLoggedIn) {
      navigate("/login");
    } else {
      navigate(`/booking/add`);
    }
  };

  const handleDeleteVehicle = (vehicleId) => {
    const vehicle = getVehicles.find((v) => v.vehicleId === vehicleId);
    if (window.confirm(`Delete Vehicle ${vehicle.make} ${vehicle.model} ${vehicle.year}?`)) {
      axios.delete(`http://localhost:8080/api/vehicle/${vehicleId}`)
        .then((response) => {
          if (response.status === 204) {
            const updated = getVehicles.filter((v) => v.vehicleId !== vehicleId);
            setGetVehicles(updated);
            setGetMessage(`Vehicle ${vehicle.make} ${vehicle.model} ${vehicle.year} was deleted.`);
          } else {
            return Promise.reject(`Unexpected Status Code: ${response.status}`);
          }
        })
        .catch(console.log);
    }
  };

  return (
    <div className="container">
      <h2 className="text-center text-info p-4">Vehicles List</h2>

      {getMessage && (
        <div className="alert alert-warning text-center" role="alert">
          {getMessage}
        </div>
      )}

      <div className="d-flex justify-content-between align-items-center mb-3">
        {isAdmin && (
          <Link className="btn btn-info" to="/vehicle/add">
            Add New Car
          </Link>
        )}
        <button className="btn btn-outline-primary" onClick={toggleZipFilter}>
          {filterByZip ? "Show All Cars" : "Show Cars in My Zip"}
        </button>
      </div>

      <div className="row">
        {getVehicles.map((vehicle) => (
          <div className="col-md-6 col-lg-3 mb-4" key={vehicle.vehicleId}>
            <div className="card h-100 shadow border border-info">
              {vehicle.imageUrl ? (
                <img
                  src={
                    vehicle.imageUrl.startsWith("http")
                      ? vehicle.imageUrl
                      : `http://localhost:8080/uploads/${vehicle.imageUrl}`
                  }
                  alt={`${vehicle.make} ${vehicle.model}`}
                  className="card-img-top"
                  style={{ height: "180px", objectFit: "cover" }}
                />
              ) : (
                <img
                  src={defaultImg}
                  alt="Default Car img"
                  className="card-img-top"
                  style={{ height: "180px", objectFit: "cover" }}
                />
              )}
              <div className="card-body d-flex flex-column">
                <h5 className="card-title text-primary">
                  {vehicle.year} {vehicle.make} {vehicle.model}
                </h5>
                <p className="card-text mb-1">Color: {vehicle.color}</p>
                <p className="card-text mb-1">Trim: {vehicle.trim}</p>
                <p className="card-text mb-1">Rent: ${vehicle.rentRate}</p>
                <p className="card-text mb-1">Lease: ${vehicle.leaseRate}</p>
                <p className={`card-text ${vehicle.bookingStatus ? 'text-danger' : 'text-success'}`}>
                  {vehicle.bookingStatus ? "Booked" : "Available"}
                </p>
                <div className="mt-auto">
                  {!vehicle.bookingStatus && (
                    <Link
                      to={`/booking/add`}
                      state={{
                        vehicleId: vehicle.vehicleId,
                        userId: localStorage.getItem('appUserId'),
                        dealershipLocationId: vehicle.dealershipId
                      }}
                      className="btn btn-success btn-sm mt-2">
                      Book
                    </Link>
                  )}
                  {isAdmin && (
                    <>
                      <Link to={`/vehicle/edit/${vehicle.vehicleId}`} className="btn btn-info btn-sm me-2">
                        Edit
                      </Link>
                      <button onClick={() => handleDeleteVehicle(vehicle.vehicleId)} className="btn btn-danger btn-sm">
                        Delete
                      </button>
                    </>
                  )}
                </div>
              </div>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}

export default Vehicle;