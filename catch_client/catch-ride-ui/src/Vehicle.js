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

      return (
        <div>
          <h2 className="text-center text-info p-4">Vehicles List</h2>
          {getMessage && (
            <div className="alert alert-success text-center" role="alert">
              {getMessage}
             </div>
          )}
            <section className="container justify-content-md-center">
              <Link className="btn btn-lg btn-secondary mt-2 mb-4" to={'/vehicle/add'}>Add New Vehicle</Link>  
              <table className="table table-striped table-hover">
                <thead>
                    <tr>
                        <th scope="col">Make</th>
                        <th scope="col">Mode</th>
                        <th scope="col">Year</th>
                        <th scope="col">Color</th>
                        <th scope="col">Trim</th>
                        <th scope="col">Book Status</th>
                    </tr>
                </thead>
              <tbody>
                {getVehicles.map((vehicle) => (
                    <tr key={vehicle.id}>
                        <td>{vehicle.make}</td>
                        <td>{vehicle.model}</td>
                        <td>{vehicle.year}</td>
                        <td>{vehicle.color}</td>
                        <td>{vehicle.trim}</td>
                        <td>{vehicle.bookStatus}</td>
                    </tr>
                ))}
              </tbody>
              </table>
            </section>
        </div>
    );

}

export default Vehicle;