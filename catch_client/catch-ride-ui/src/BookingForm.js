import { useState, useEffect } from "react";
import { useNavigate, useParams, Link, useLocation } from "react-router-dom";

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
    const [errors, setErrors] = useState([]);
    const url = "http://localhost:8080/api/booking";
    const navigate = useNavigate();
    const { bookingId } = useParams();
    const location = useLocation();

    useEffect(() => {
        console.log("Incoming state:", location.state);
        console.log("b_Id:", bookingId);
        if (bookingId) {
            fetch(`${url}/bookingId/${bookingId}`)
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
                    setBooking(data);
                })
                .catch(console.log);
        } else {
            const state = location.state || {};
            setBooking({
                ...BOOKING_DEFAULT,
                vehicleId: state.vehicleId || 0,
                userId: state.userId || 0,
                dealershipLocationId: state.dealershipLocationId || 0});
        }
    }, [bookingId, location.state]);

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
        console.log(url + init);
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
        <div className="container p-4 mt-4">
          <section className="container justify-content-md-center">
            <div className="jumbotron col-md-6 offset-md-3">
                <h2 className="text-center text-success mb-4">{parseInt(bookingId) > 0 ? 'Edit Booking' : 'Add Booking'}</h2>
                {errors.length > 0 && (
                    <div className="alert alert-danger">
                        <p>The Following Errors were found:</p>
                        <ul>
                            {errors.map(errors => (
                                <li key={errors}>{errors}</li>
                            ))}
                        </ul>
                    </div>
                )}
                <form onSubmit={handleSubmit}>
                    <fieldset className="form-group mb-3 fs-3">
                        <label htmlFor="startDate">Start Date</label>
                        <input id="startDate" 
                        name="startDate" 
                        type="date" 
                        className="form-control" 
                        value={booking.startDate} 
                        onChange={handleChange} />
                    </fieldset>
                    <fieldset className="form-group mb-3 fs-3">
                        <label htmlFor="endDate">End Date</label>
                        <input id="endDate" 
                        name="endDate" 
                        type="date" 
                        className="form-control" 
                        value={booking.endDate} 
                        onChange={handleChange}/>
                    </fieldset>
                    <fieldset className="form-group mb-3 fs-3">
                        <label htmlFor="bookingType">Booking Type:</label>
                        <div className="form-check mb-3">
                            <input className="form-check-input" 
                              type="radio"
                              name="bookingType" 
                              id="rent" 
                              value="RENT" 
                              checked={booking.bookingType === "RENT"} 
                              onChange={handleChange}/>
                            <label className="form-check-label ml-2 fs-3" htmlFor="rent">Rent</label>
                        </div>
                        <div className="form-check mb-3 fs-3">
                            <input className="form-check-input"
                               type="radio" 
                               name="bookingType" 
                               id="lease" 
                               value="LEASE" 
                               checked={booking.bookingType === "LEASE"} 
                               onChange={handleChange}/>
                            <label className="form-check-label ml-2 mb-3 fs-3" htmlFor="lease">Lease</label>
                        </div>
                        <div className="d-flex justify-content-between mt-4">
                            <button type="submit" className="btn btn-success btn-lg">{parseInt(bookingId) > 0 ? 'Submit Booking' : 'Add Booking'}</button>
                            <Link type="button" className="btn btn-secondary btn-lg" to={'/user'}>Cancel</Link>
                        </div> 
                    </fieldset>
                </form>
            </div>
        </section>
      </div>
    )
}

export default BookingForm;