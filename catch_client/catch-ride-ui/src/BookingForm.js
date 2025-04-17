import { useState, useEffect } from "react";
import { useNavigate, useParams, Link } from "react-router-dom";

const BOOKING_DEFAULT = {
    vehicleId: 0,
    userId: 0,
    dealershipLocationId: 0,
    startDate: "",
    endDate: "",
    total: 0.00,
    bookingType: "",
    dateCreated: ""
}

function BookingForm() {
    const [booking, setBooking] = useState(BOOKING_DEFAULT);
    const [error, setError] = useState([]);
    const [getMessage, setGetMessage] = useState("");
    const url = "http://localhost:8080/api/booking";
    const navigate = useNavigate();
    const { id } = useParams();

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
                    setVehicle(data);
                })
                .catch(console.log);
        } else {
            setVehicle(VEHICLE_DEFAULT);
        }
    }, [id]);

    const handleChange = (event) => {
        setBooking({
            ...booking,
            [event.target.name]: event.target.value,
        }); 
    };

    const handleSubmit = (event) => {
        event.preventDefault();

        if(bookingId){
            editBooking();
        } else {
            addBooking();
        }
    };

    const addBooking = () => {
        const init = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(booking)
        }
        fetch(url, init)
        .then(response => {
            if(response.status === 201 || response.status === 400){
                return response.json();
            } else {
                return Promise.reject(`Unexpected Status Code: ${Response.status}`);
            }
        })
        .then((data) => {
            if (data.bookingId) {
                navigate("/")
            } else {
                setErrors(data);
            }
        })
        .catch(console.log)
    };

    const editBooking = () => {
        booking.bookingId = bookingId;
        const init = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(booking)
        }
        fetch(`${url}/${bookingId}`, init)
        .then(response => {
            if(response.status === 204){
                return null;
            } else if (response.status === 400){
                return response.json();
            } else {
                return Promise.reject(`Unexpected status code: ${response.status}`);
            }
        })
        .then(data => {
            if(!data){
                navigate('/')
            } else {
                setErrors(data);
            }
        })
        .catch(console.log);
    }

    return(
        <>
            <section>
                <h2 className="mb-4">{parseInt(bookingId) > 0 ? 'Edit Booking' : 'Add Booking'}</h2>
                {errors.length > 0 && (
                    <div className="alert alert-danger">
                        <p>The FOllowing Errors were found:</p>
                        <ul>
                            {errors.map(errors => (
                                <li key={errors}>{errors}</li>
                            ))}
                        </ul>
                    </div>
                )}
                <form onSubmit={handleSubmit}>
                    <fieldset className="form-group">
                        <label htmlFor="startDate">Start Date</label>
                        <input id="startDate" name="startDate" type="date" className="form-control" value={booking.startDate} onChange={handleChange}></input>
                    </fieldset>
                    <fieldset className="form-group">
                        <label htmlFor="endDate">End Date</label>
                        <input id="endDate" name="endDate" type="date" className="form-control" value={booking.endDate} onChange={handleChange}></input>
                    </fieldset>
                    <fieldset className="form-group">
                        <label htmlFor="bookingType">Booking Type</label>
                        <div className="form-check">
                            <input className="form-check-input" type="radio" name="bookingType" id="rent" value="RENT" checked={booking.bookingType === "RENT"} onChange={handleChange}></input>
                            <label className="form-check-label" htmlFor="rent">Rent</label>
                        </div>
                        <div className="form-check">
                            <input className="form-check-input" type="radio" name="bookingType" id="lease" value="LEASE" checked={booking.bookingType === "LEASE"} onChange={handleChange}></input>
                            <label className="form-check-label" htmlFor="lease">Lease</label>
                        </div>
                    </fieldset>
                </form>
            </section>
        </>
    )
}