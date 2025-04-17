import { useEffect, useState } from "react";
import { Link } from "react-router-dom";

function Booking() {
    const [getBookings, setGetBookings] = useState([]);
    const [getMessage, setGetMessage] = useState("");
    const url = "http://localhost:8080/api/booking";

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
            console.log("Fetched bookings:", data);
            setGetBookings(data);
          })
          .catch(console.log);
      }, []);

      const handleDeleteBooking = (bookingId) => {
        const booking = getBookings.find((b) => b.bookingId === bookingId);
        if(window.confirm(`Delete Booking on ${booking.startDate} to ${booking.endDate}?`))
            {const init = {
                method: "DELETE"
            };
            fetch(`${url}/${bookingId}`, init)
                .then((response) => {
                    if (response.status === 204) {
                        const seeBooking = getBookings.filter(
                            (b) => b.bookingId !== bookingId);
                            setGetBookings(seeBooking);
                            setGetMessage(`Booking from ${booking.startDate} to ${booking.endDate} with ID #${bookingId} was successfully deleted!`);
                    } else {
                        return Promise.reject(`Unexpected Status Code: ${response.status}`);
                    }
                })
                .catch(console.log);
            }
        };

      return (
        <div>
            <h2 className="test-center text-info p-4">Booking List</h2>
            {getMessage && (
                <div className="alert alert-success text-center" role="alert">
                    {getMessage}
                </div>
            )}
            <section className="container justify-content-md-center">
                <table className="table table-striped table-hover">
                    <thead>
                        <tr>
                            <th scope="col">Vehicle Id</th>
                            <th scope="col">dealershipId</th>
                            <th scope="col">Start Date</th>
                            <th scope="col">End Date</th>
                            <th scope="col">Total</th>
                            <th scope="col">Booking Type</th>
                            <th scope="col">Date Created</th>
                            <th>&nbsp;</th>
                        </tr>
                    </thead>
                    <tbody>
                        {getBookings.map((booking) => (
                            <tr key={booking.bookingId}>
                                <td>{booking.vehicleId}</td>
                                <td>{booking.dealershipLocationId}</td>
                                <td>{booking.startDate}</td>
                                <td>{booking.endDate}</td>
                                <td>{booking.total}</td>
                                <td>{booking.bookingType}</td>
                                <td>{booking.dateCreated}</td>
                                <td>
                                    <Link className="btn btn-info" to={`/booking/edit/${booking.bookingId}`} state={{vehicleId: booking.vehicleId, userId: 1, dealershipLocationId: booking.dealershipLocationId}}>Edit</Link>
                                    <button className="btn btn-danger" onClick={() => handleDeleteBooking(booking.bookingId)}>Delete</button>
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </section>
        </div>
      )

}

export default Booking;