import { useState, useEffect } from "react";
import { useNavigate, useParams, Link } from "react-router-dom";

const VEHICLE_DEFAULT = {
    make: "",
    model: "",
    year: "",
    color: "",
    trim: "",
    rentRate: "",
    leaseRate: "",
    bookingStatus: false,
    dealershipId: "",
    imageUrl: "", 
}

function VehicleForm() {
    const [vehicle, setVehicle] = useState(VEHICLE_DEFAULT);
    const [error, setError] = useState([]);
    const [getMessage, setGetMessage] = useState("");
    const url = "http://localhost:8080/api/vehicle";
    const navigate = useNavigate();
    const { id } = useParams();
    
    // useEffect to fetch the vehicle data when in edit mode (id exists)
    useEffect(() => {
        console.log("v_Id:", id);
        if (id) {
            fetch(`${url}/id/${id}`)
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
                   // setVehicle(data);
                   console.log("Fetched agent data:", data);
                   setVehicle({
                        ...VEHICLE_DEFAULT,
                        ...data,
                       bookingStatus: data.bookingStatus === true || data.bookingStatus === "true",
                       imageUrl: data.imageUrl ? data.imageUrl : "",
                       imageUrlFile: null,
                    });
                })
                .catch(console.log);
        } else {
            setVehicle(VEHICLE_DEFAULT);
        }
    }, [id]);

    // const handleChange = (event) => {
    //     setVehicle({
    //         ...vehicle,
    //         [event.target.name]: event.target.value,
    //     }); 
    // };
    const handleChange = (event) => {
        const { name, value } = event.target;
        if (name === "imageUrl") { 
            setVehicle({ //clear file if URL is used
                ...vehicle,
                [name]: value,
                imageUrlFile: null
            });
        } else {
            setVehicle({
                ...vehicle,
                [name]: value
            });
        }
    };

    const handleFileUpload = (event) => {
        const file = event.target.files[0];
        if (file) {
            setVehicle({
                ...vehicle,
                imageUrlFile: file
            });
        }
    };

    const handleSubmit = (event) => {
        event.preventDefault();
        const method = id ? "PUT" : "POST"; 
        const apiUrl = id ? `${url}/${id}` : url;

        console.log("fetching API:", id);
        console.log("Submitting:", JSON.stringify(vehicle, null, 2));

        const formData = new FormData();
        formData.append("vehicle", new Blob([JSON.stringify(vehicle)], { type: "application/json" }));
    
        if (vehicle.imageUrlFile) {
            formData.append("file", vehicle.imageUrlFile);
        }

        fetch(apiUrl, 
            {
            method: method,
            body: formData,
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
                } else if (response.status === 200) {
                    return response.json();    
                } else if (response.status === 400) {
                    return response.json();
                } else {
                    return Promise.reject(`Unexpected StatusCode: ${response.status}`);
                }
            }
        })
        .then((data) => {
            if (method === "POST" && data) { 
                if (Array.isArray(data)) { 
                    setError(data);
                } else {
                    setGetMessage(`Vehicle ${data.make} ${data.model} for dealership ID #${data.dealershipId} was successfully added!`);
                    setTimeout(() => {
                        setGetMessage("");
                        navigate("/vehicles");
                    }, 1500);
                }
            } else if (method === "PUT" && !data) { 
                setGetMessage(`Vehicle was successfully updated!`);
                setTimeout(() => {
                    setGetMessage("");
                    navigate("/vehicles");
                }, 1500);
            } else {
                setError(data);
            }
        })
        .catch(console.log);
    };

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
                           disabled={!!id} 
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
                           disabled={!!id} 
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
                            disabled={!!id}
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
                            <label htmlFor="rentRate" className="form-label">Rent Rate:</label>
                            <input type="text" 
                            className="form-control" 
                            id="rentRate" 
                            name="rentRate" 
                            value={vehicle.rentRate} 
                            onChange={handleChange} 
                            required/>
                        </div> 
                        <div className="mb-3">
                            <label htmlFor="leaseRate" className="form-label">Lease Rate:</label>
                            <input type="text" 
                            className="form-control" 
                            id="leaseRate" 
                            name="leaseRate" 
                            value={vehicle.leaseRate} 
                            onChange={handleChange} 
                            required/>
                        </div>  
                        <div className="mb-3">
                            <label htmlFor="bookingStatus" className="form-label">Booked Status:</label>
                            <input type="checkbox" 
                            className="form-check-input m-3" 
                            id="bookingStatus" 
                            name="bookingStatus" 
                            checked={vehicle.bookingStatus} 
                            onChange={(event) => setVehicle({ ...vehicle, bookingStatus: event.target.checked })} 
                            />
                        </div>
                        <div className="mb-3">
                            <label htmlFor="dealershipId" className="form-label">Dealership ID:</label>
                            <input type="text"
                            className="form-control"
                            id="dealershipId"
                            name="dealershipId"
                            value={vehicle.dealershipId}
                            onChange={handleChange}
                            disabled={!!id}
                            required/>
                        </div>
                        <p className="text-center text-info">Choose options:</p>
                        <p className="text-muted small">
                            {vehicle.imageUrlFile ? "Using uploaded file image" : vehicle.imageUrl ? "Using image URL" : "No image selected"}</p>
                        <div className="mb-3">
                            <label htmlFor="imageUrl" className="form-label">Car Image (URL):</label>
                            <input
                                type="text"
                                className="form-control"
                                id="imageUrl"
                                name="imageUrl"
                                value={vehicle.imageUrl}
                                onChange={handleChange}
                            />
                            {vehicle.imageUrl && (
                                <img
                                    src={vehicle.imageUrl}
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
