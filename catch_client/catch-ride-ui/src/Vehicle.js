import { useEffect, useState } from "react";
import { Link } from "react-router-dom";

function Vehicle() {
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
            console.log("Fetched vehicles:", data); // checked what the returned data vehicles
            setGetVehicles(data);
          })
          .catch(console.log);
      }, []); //this will run once on page load


        //Methods
    const handleDeleteVehicle = (vehicleId) => {
        const vehicle = getVehicles.find((v) => v.vehicleId === vehicleId); //find matching by id
        if(window.confirm(`Delete Vehicle ${vehicle.make} ${vehicle.model} ${vehicle.year}?`))
            {const init = {
                method: "DELETE",
            };
            fetch(`${url}/${vehicleId}`, init)
              .then((response) => {
                if (response.status === 204) {
                    const seeVehicle = getVehicles.filter(
                    (v) => v.vehicleId !== vehicleId); // create a copy of the array
                    setGetVehicles(seeVehicle); // update the vehicles state
                    setGetMessage(`Vehicle ${vehicle.make} ${vehicle.model} ${vehicle.year} with ID #${vehicleId} was successfully deleted!`); //add message as alert
                   // window.scrollTo({ top: 0, behavior: "smooth" });
                  //  setTimeout(() => setGetMessage(""), 3000); // to clear the message
                } else {
                  return Promise.reject(`Unexpected Status Code: ${response.status}`);
                }
              })
                .catch(console.log);
            }
        }; 

    return (
        <div>
          <h2 className="text-center text-info p-4">Vehicles List</h2>
          {getMessage && (
            <div className="alert alert-success text-center" role="alert">
              {getMessage}
             </div>
          )}
            <section className="container justify-content-md-center">
              <Link className="btn btn-lg btn-info mt-2 mb-4" to={'/vehicle/add'}>Add New Car</Link>  
              <table className="table table-striped table-hover">
                <thead>
                    <tr>
                        <th scope="col">Make</th>
                        <th scope="col">Mode</th>
                        <th scope="col">Year</th>
                        <th scope="col">Color</th>
                        <th scope="col">Trim</th>
                        <th scope="col">Rent Rate</th>
                        <th scope="col">Lease Rate</th>
                        <th scope="col">Book Status</th>
                        <th>&nbsp;</th>
                    </tr>
                </thead>
              <tbody>
                {getVehicles.map((vehicle) => (
                    <tr key={vehicle.vehicleId}>
                        <td>{vehicle.make}</td>
                        <td>{vehicle.model}</td>
                        <td>{vehicle.year}</td>
                        <td>{vehicle.color}</td>
                        <td>{vehicle.trim}</td>
                        <td>{vehicle.rentRate}</td>
                        <td>{vehicle.leaseRate}</td>
                        <td>{vehicle.bookingStatus ? "Yes" : "No"}</td>
                        <td>
                            <Link className="btn btn-info" to={`/vehicle/edit/${vehicle.vehicleId}`}>
                            Edit
                            </Link>
                            <button className="btn btn-danger" 
                            onClick={() => handleDeleteVehicle(vehicle.vehicleId)}>Delete</button>
                        </td>
                    </tr>
                ))}
              </tbody>
              </table>
            </section>
        </div>
    );

}

export default Vehicle;