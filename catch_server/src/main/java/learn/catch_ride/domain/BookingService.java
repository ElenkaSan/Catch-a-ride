package learn.catch_ride.domain;

import learn.catch_ride.data.BookingRepository;
import learn.catch_ride.models.Booking;

import java.time.LocalDate;
import java.util.List;

public class BookingService {

    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) { this.bookingRepository = bookingRepository; }

    public List<Booking> findAll() { return bookingRepository.findAll(); }

    public List<Booking> findByLocation(int locationId) { return bookingRepository.findByLocation(locationId); }

    public Booking findById(int bookingId) { return bookingRepository.findById(bookingId); }

    public Result<Booking> add(Booking booking) {
        Result<Booking> result = validate(booking);
        if (!result.isSuccess()) {
            return result;
        }

        if (booking.getBookingId() != 0) {
            result.addMessage("bookingId cannot be set for `add` operation", ResultType.INVALID);
            return result;
        }

        booking = bookingRepository.add(booking);
        result.setPayload(booking);
        return result;
    }

    public Result<Booking> update(Booking booking) {
        Result<Booking> result = validate(booking);
        if (!result.isSuccess()) {
            return result;
        }

        bookingRepository.update(booking);

        result.setPayload(booking);

        return result;
    }

    public Result<Booking> deleteById(int bookingId) {
        Result<Booking> result = new Result<>();
        LocalDate today = LocalDate.now();
        Booking booking = findById(bookingId);

        if(booking.getStartDate().isBefore(today)) {
            result.addMessage("Cannot cancel reservation after start date.", ResultType.INVALID);
            return result;
        }

        bookingRepository.deleteById(bookingId);

        result.setPayload(booking);

        return result;
    }

    //To-Do: Create Function that Calculates Total Cost of Booking.

    private Result<Booking> validate(Booking booking) {
        Result<Booking> result = new Result<>();
        List<Booking> bookings = bookingRepository.findAll();
        LocalDate today = LocalDate.now();

        if (booking == null) {
            result.addMessage("booking cannot be null.", ResultType.INVALID);
        }

        if (booking.getStartDate() == null) {
            result.addMessage("Start date is required.", ResultType.INVALID);
        }

        if (booking.getEndDate() == null) {
            result.addMessage("End date is required.", ResultType.INVALID);
        }

        //To-Do: Create Validation for checking if user exists.
        //To-Do: Create Validation for checking if vehicle exists.
        //To-Do: Create Validation for checking if dealership location exists.

        if (booking.getStartDate().isBefore(today)) {
           result.addMessage("Start Date must be in the future.", ResultType.INVALID);
        }

        if (booking.getEndDate().isAfter(booking.getStartDate())) {
            result.addMessage("End Date must be after start date.", ResultType.INVALID);
        }

        for(Booking b: bookings) {
            if((!booking.getEndDate().isBefore(b.getStartDate()) && !b.getEndDate().isBefore(booking.getStartDate())) &&
                    (booking.getBookingId() != b.getBookingId())) {
                result.addMessage("Booking dates must not overlap with others.", ResultType.INVALID);
                break;
            }
        }

        return result;

    }
}
