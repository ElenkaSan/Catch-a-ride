import { useState, useEffect } from "react";
import { useNavigate, useParams, Link } from "react-router-dom";

const VEHICLE_DEFAULT = {
    make: "",
    model: "",
    year: 0,
    color: "",
    trim: "",
    rentRate: "",
    leaseRate: "",
    bookStatus: false,
    imageCar: "", 
}

function VehicleForm() {
    const [vehicle, setVehicle] = useState(VEHICLE_DEFAULT);
    const [error, setError] = useState([]);
    const [getMessage, setGetMessage] = useState("");
    const url = "http://localhost:8080/api/vehicle";
    const navigate = useNavigate();
    const { id } = useParams();
    
    // useEffect
    useEffect(() => {
      console.log("v_Id:", id);
      if (id) {
        fetch(`${url}/${id}`)
          .then((response) => {
            console.log("Fetch response status:", response.status);
            if (response.status === 200) {
              return response.json();
            } else {
              return Promise.reject(`Unexpected StatusCode: ${response.status}`);
            }
          })
          .then((data) => {
            console.log("Fetched agent data:", data);
            setVehicle(data);
          })
          .catch(console.log);
      } else {
        setVehicle(VEHICLE_DEFAULT);
      }
    }, [id]); // Hey React, please call my useEffect function every time the id route in the url parameter changes

    const handleChange = (event) => {
        setVehicle({
            ...vehicle,
            [event.target.name]: event.target.value,
        }); 
    };

    const handleFileUpload = (event) => {
        const file = event.target.files[0];
        const reader = new FileReader();
        reader.onloadend = () => {
            setVehicle({
                ...vehicle,
                imageCar: reader.result, // Base64 encoded string
            });
        };
        reader.readAsDataURL(file);
    };

    const handleSubmit = (event) => {
        event.preventDefault();
        const method = id ? "PUT" : "POST"; //using fetch to POST the new agent's information to the Field Agent API and fetch to PUT the updated agent’s info
        const apiUrl = id ? `${url}/${id}` : url;

        fetch(apiUrl, {
            method: method,
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(vehicle),
        })
        .then((response) => {
            if (method === "POST") {
                if (response.status === 201 || response.status === 400) {
                    return response.json();
                } else {
                    return Promise.reject(`Unexpected StatusCode: ${response.status}`);
                } 
            } else if (method === "PUT") {
                if (response.status === 204) {
                    return null;
                } else if (response.status === 400) {
                    return response.json();
                } else {
                    return Promise.reject(`Unexpected StatusCode: ${response.status}`);
                }
            }
        }
        )
        .then((data) => {
            if (method === "POST" && data) { //add a new agent - 201
                setGetMessage(`Vehicle ${data.make} ${data.model} was successfully added!`);
            } else if (method === "PUT") { //update an agent - 204
                setGetMessage(`Vehicle ${vehicle.make} ${vehicle.model} was successfully updated!`);
            }
            window.scrollTo({ top: 0, behavior: "smooth" });
            setTimeout(() => setGetMessage(""), 3000);
            navigate("/vehicle");
        })
        .catch((error) => {
            console.log(error);
            if (error.status === 400) {
                setError(error.data);
            }
        });
    }

    
    return (
        <div>
            {getMessage && (
                <div className="alert alert-success text-center" role="alert">
                   {getMessage}
                </div>
           )}
            {error.length > 0 && (
                <div className="alert alert-danger text-center" role="alert">
                    <p>Having Errors:</p>
                    <ul>
                        {error.map((err) => (
                            <li key={err}>{err}</li>
                        ))}
                    </ul>
                </div>
            )}
            <section className="container justify-content-md-center">
                <div className="col-md-6 offset-md-3">
                    <h2 className="text-center text-info p-4">Vehicle Form</h2>
                    <form onSubmit={handleSubmit}>
                       <div className="mb-3">
                           <label htmlFor="make" className="form-label">Make:</label>
                           <input type="text" 
                           className="form-control" 
                           id="make" 
                           name="make" 
                           value={vehicle.make} 
                           onChange={handleChange} 
                           required/>    
                       </div>    
                       <div className="mb-3">
                           <label htmlFor="model" className="form-label">Model:</label>
                           <input type="text" 
                           className="form-control" 
                           id="model" 
                           name="model" 
                           value={vehicle.model} 
                           onChange={handleChange} 
                           required/>    
                       </div>    
                          <div className="mb-3">
                            <label htmlFor="year" className="form-label">Year:</label>
                            <input type="number" 
                            className="form-control" 
                            id="year" 
                            name="year" 
                            value={vehicle.year} 
                            onChange={handleChange} 
                            required/>
                        </div>
                        <div className="mb-3">
                                <label htmlFor="color" className="form-label">Color:</label>
                                <input type="text" 
                                className="form-control" 
                                id="color" 
                                name="color" 
                                value={vehicle.color} 
                                onChange={handleChange} />
                            </div>    
                        <div className="mb-3">
                            <label htmlFor="trim" className="form-label">Trim:</label>
                            <input type="text" 
                            className="form-control" 
                            id="trim" 
                            name="trim" 
                            value={vehicle.trim} 
                            onChange={handleChange} />
                        </div> 
                        <div className="mb-3">
                            <label htmlFor="rent" className="form-label">Rent Rate:</label>
                            <input type="text" 
                            className="form-control" 
                            id="rent" 
                            name="rent" 
                            value={vehicle.rent} 
                            onChange={handleChange} 
                            required/>
                        </div> 
                        <div className="mb-3">
                            <label htmlFor="lease" className="form-label">Lease Rate:</label>
                            <input type="text" 
                            className="form-control" 
                            id="lease" 
                            name="lease" 
                            value={vehicle.lease} 
                            onChange={handleChange} 
                            required/>
                        </div>  
                        <div className="mb-3">
                            <label htmlFor="bookStatus" className="form-label">Book Status:</label>
                            <input type="checkbox" 
                            className="form-check-input" 
                            id="bookStatus" 
                            name="bookStatus" 
                            checked={vehicle.bookStatus} 
                            onChange={(event) => setVehicle({ ...vehicle, bookStatus: event.target.checked })} 
                            />
                        </div>
                        <p className="text-center text-info">Choose options:</p>
                        <div className="mb-3">
                            <label htmlFor="imageCar" className="form-label">Car Image (URL):</label>
                            <input
                                type="text"
                                className="form-control"
                                id="imageCar"
                                name="imageCar"
                                value={vehicle.imageCar}
                                onChange={handleChange}
                            />
                            {vehicle.imageCar && (
                                <img
                                    src={vehicle.imageCar}
                                    alt="Vehicle Preview"
                                    className="img-fluid mt-3 rounded"
                                />
                            )}
                        </div>
                        <div className="mb-3">
                            <label htmlFor="imageUpload" className="form-label">Upload Car Image:</label>
                            <input
                                type="file"
                                className="form-control"
                                id="imageUpload"
                                name="imageUpload"
                                accept="image/*"
                                onChange={handleFileUpload}
                            />
                        </div>
                        <div className="d-flex justify-content-between mt-3 mb-4">
                        <button type="submit" className={`btn btn-${id ? "warning":"info"} btn-lg`}>{id > 0 ? "Save Updates" : "Save Added Car"} </button>
                        <Link to={'/vehicles'}><button type="button" className='btn btn-secondary btn-lg'>Cancel</button>
                        </Link>
                        </div>
                    </form>    
                </div>    
            </section>        
        </div>
    );
}

export default VehicleForm;