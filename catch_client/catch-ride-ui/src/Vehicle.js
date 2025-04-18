
import { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom"; 
import axios from './axiosConfig';
import defaultImg from "./logo.png";

function Vehicle({ showAvailableOnly = false }) {
  const [getVehicles, setGetVehicles] = useState([]);
  const [getMessage, setGetMessage] = useState("");
  const [zipFilter, setZipFilter] = useState({ enabled: false, zip: null });
  const navigate = useNavigate(); 
  const [isAdmin, setIsAdmin] = useState(false);
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  const fetchVehicles = async (zip) => {
    try {
      if (zipFilter.enabled && zip) {
        const response = await fetch(`http://localhost:8080/api/vehicle/zipcode/${zip}`);
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
        setGetMessage("");
      }
    } catch (err) {
      console.error("Fetch error:", err);
      setGetMessage("Error loading vehicles.");
    }
  };

  const toggleZipFilter = () => {
    const newFilterState = !zipFilter.enabled;
  
    if (newFilterState) {
      const freshZip = localStorage.getItem("zipCode");
  
      if (freshZip) {
        setZipFilter({ enabled: true, zip: freshZip });
      } else {
        setGetMessage("Your zip code is missing. Please update your profile.");
      }
    } else {
      setZipFilter({ enabled: false, zip: null });
    }
  };

  useEffect(() => {
    const token = localStorage.getItem("token");
    const role = localStorage.getItem("roles");
    setIsLoggedIn(!!token);
    setIsAdmin(role === 'admin');
  }, []);

  useEffect(() => {
    if (zipFilter.enabled && zipFilter.zip) {
      fetchVehicles(zipFilter.zip);
    } else {
      fetchVehicles();
    }
  }, [zipFilter, showAvailableOnly]);



  const handleBookClick = (vehicleId, dealershipId) => {
    if (!isLoggedIn) {
      navigate("/login");
    } else {
      navigate("/booking/add", {
        state: {
          vehicleId,
          userId: localStorage.getItem('appUserId'),
          dealershipLocationId: dealershipId
        }
      });
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
          <Link className="btn btn-info btn-lg btn-outline-dark" to="/vehicle/add">
            Add New Car
          </Link>
        )}
        <button className="btn btn-outline-dark btn-lg" onClick={toggleZipFilter}>
        {zipFilter.enabled ? "Show All Cars" : "Show Cars in My Zip"}
        </button>
      </div>

      <div className="row">
        {getVehicles.map((vehicle) => (
          <div className="col-md-6 col-lg-3 mb-5" key={vehicle.vehicleId}>
            <div className="card h-100 shadow border border-info border-rounded">
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
                <p className="card-title text-info fw-bolder">
                  {vehicle.year} {vehicle.make} {vehicle.model}
                </p>
                <p className="card-text mb-1 fw-lighter">Color: {vehicle.color}</p>
                <p className="card-text mb-1 fw-lighter">Trim: {vehicle.trim}</p>
                <p className="card-text mb-1 ">Rent: ${vehicle.rentRate}</p>
                <p className="card-text mb-1 fw-lighter">Lease: ${vehicle.leaseRate}</p>
                <p className={`card-text fw-lighter ${vehicle.bookingStatus ? 'text-danger' : 'text-success'}`}>
                  {vehicle.bookingStatus ? "Booked" : "Available"}
                </p>
                <div className="mt-auto">
                  {!vehicle.bookingStatus && (
                    <button
                    className="btn btn-success btn-lg mt-2"
                    onClick={() => handleBookClick(vehicle.vehicleId, vehicle.dealershipId)}>
                    Book Car
                  </button>
                  )}
                  {isAdmin && (
                    <>
                      <Link to={`/vehicle/edit/${vehicle.vehicleId}`} className="btn btn-info btn-lg me-2">
                        Edit
                      </Link>
                      <button onClick={() => handleDeleteVehicle(vehicle.vehicleId)} className="btn btn-danger btn-lg">
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