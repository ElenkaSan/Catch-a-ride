import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import axios from './axiosConfig';
import defaultImg from "./logo.png"; // Placeholder image
function Vehicle({ showAvailableOnly = false }) {
  const [getVehicles, setGetVehicles] = useState([]);
  const [getMessage, setGetMessage] = useState("");
  const url = "http://localhost:8080/api/vehicle";
  useEffect(() => {
    fetch(url)
      .then((response) => {
        if (response.status === 200) {
          return response.json();
        } else {
          return Promise.reject(`Unexpected Status Code: ${response.status}`);
        }
      })
      .then((data) => {
        console.log("Fetched vehicles:", data);
        const vehiclesToShow = showAvailableOnly
        ? data.filter((vehicle) => !vehicle.bookingStatus)
        : data;
      setGetVehicles(vehiclesToShow);
    })
    .catch(console.log);
}, [showAvailableOnly]);
  //       setGetVehicles(data);
  //     })
  //     .catch(console.log);
  // }, []);
  const handleDeleteVehicle = (vehicleId) => {
    const vehicle = getVehicles.find((v) => v.vehicleId === vehicleId);
    if (window.confirm(`Delete Vehicle ${vehicle.make} ${vehicle.model} ${vehicle.year}?`)) {
      axios.delete(`${url}/${vehicleId}`)
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
        <div className="alert alert-success text-center" role="alert">
          {getMessage}
        </div>
      )}
      <div className="text-end mb-4">
        <Link className="btn btn-lg btn-info" to="/vehicle/add">
          Add New Car
        </Link>
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
                  <Link to={`/vehicle/edit/${vehicle.vehicleId}`} className="btn btn-info btn-sm me-2">
                    Edit
                  </Link>
                  <button onClick={() => handleDeleteVehicle(vehicle.vehicleId)} className="btn btn-danger btn-sm">
                    Delete
                  </button>
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
