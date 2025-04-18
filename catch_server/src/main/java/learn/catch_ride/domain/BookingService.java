package learn.catch_ride.domain;

import learn.catch_ride.data.BookingRepository;
import learn.catch_ride.data.DealershipRepository;
import learn.catch_ride.data.UserRepository;
import learn.catch_ride.data.VehicleRepository;
import learn.catch_ride.models.Booking;
import learn.catch_ride.models.BookingType;
import learn.catch_ride.models.Vehicle;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final VehicleRepository vehicleRepository;
    private final UserRepository userRepository;
    private final DealershipRepository dealershipRepository;

    public BookingService(BookingRepository bookingRepository, VehicleRepository vehicleRepository, UserRepository userRepository, DealershipRepository dealershipRepository) {
        this.bookingRepository = bookingRepository;
        this.vehicleRepository = vehicleRepository;
        this.userRepository = userRepository;
        this.dealershipRepository = dealershipRepository;
    }

    public List<Booking> findAll() { return bookingRepository.findAll(); }

    public List<Booking> findByLocation(int locationId) { return bookingRepository.findByLocation(locationId); }

    public Booking findById(int bookingId) { return bookingRepository.findById(bookingId); }

    public List<Booking> findByUserId(int userId) { //for userPage
        return bookingRepository.findByUserId(userId);
    }

    public Result<Booking> add(Booking booking) {
        Result<Booking> result = validate(booking);
        if (!result.isSuccess()) {
            return result;
        }

        if (booking.getBookingId() != 0) {
            result.addMessage("Booking ID cannot be set for `add` operation", ResultType.INVALID);
            return result;
        }

        Vehicle vehicle = vehicleRepository.findById(booking.getVehicleId());
        vehicle.setBookingStatus(true);
        vehicleRepository.update(vehicle);
        BigDecimal total = calculateTotal(booking, vehicle);
        booking.setTotal(total);
        System.out.println("System date now: " + LocalDate.now());
        booking.setDateCreated(LocalDate.now());
        booking = bookingRepository.add(booking);
        result.setPayload(booking);
        return result;
    }

    public Result<Booking> update(Booking booking) {
        Result<Booking> result = validate(booking);
        if (!result.isSuccess()) {
            return result;
        }

        Vehicle vehicle = vehicleRepository.findById(booking.getVehicleId());
        BigDecimal total = calculateTotal(booking, vehicle);
        booking.setTotal(total);
        bookingRepository.update(booking);

        result.setPayload(booking);

        return result;
    }

    public Result<Booking> deleteById(int bookingId) {
        Result<Booking> result = new Result<>();
        LocalDate today = LocalDate.now();
        Booking booking = findById(bookingId);

        if (booking == null) {
            result.addMessage("Booking cannot be null.", ResultType.INVALID);
            return result;
        }

        if(booking.getStartDate().isBefore(today)) {
            result.addMessage("Cannot cancel reservation after start date.", ResultType.INVALID);
            return result;
        }

        Vehicle vehicle = vehicleRepository.findById(booking.getVehicleId());
        vehicle.setBookingStatus(false);
        vehicleRepository.update(vehicle);
        bookingRepository.deleteById(bookingId);

        result.setPayload(booking);

        return result;
    }

    public BigDecimal calculateTotal(Booking booking, Vehicle vehicle) {
        BigDecimal finalTotal = new BigDecimal("0.00");

        BigDecimal dailyRate = booking.getBookingType() == BookingType.RENT ? vehicle.getRentRate() : vehicle.getLeaseRate();

        for(LocalDate date = booking.getStartDate(); !date.isEqual(booking.getEndDate()); date = date.plusDays(1)) {
            finalTotal = finalTotal.add(dailyRate);
        }

        return finalTotal;
    }

    private Result<Booking> validate(Booking booking) {
        Result<Booking> result = new Result<>();
        List<Booking> bookings = bookingRepository.findAll();
        LocalDate today = LocalDate.now();

        if (booking == null) {
            result.addMessage("Booking cannot be null.", ResultType.INVALID);
            return result;
        }

        if (booking.getStartDate() == null) {
            result.addMessage("Start date is required.", ResultType.INVALID);
        }

        if (booking.getEndDate() == null) {
            result.addMessage("End date is required.", ResultType.INVALID);
        }

        if(booking.getStartDate() != null && booking.getEndDate() != null) {
            if (booking.getStartDate().isBefore(today)) {
                result.addMessage("Start date must be in the future.", ResultType.INVALID);
            }

            if (!booking.getEndDate().isAfter(booking.getStartDate())) {
                result.addMessage("End Date must be after start date.", ResultType.INVALID);
            }

            for (Booking b : bookings) {
                if (b.getVehicleId() == booking.getVehicleId() && booking.getBookingId() != b.getBookingId()) {
                    if (booking.getStartDate().isBefore(b.getEndDate()) && booking.getEndDate().isAfter(b.getStartDate())) {
                        result.addMessage("Booking dates must not overlap with others.", ResultType.INVALID);
                        break;
                    }
                }
            }
        }

        if (vehicleRepository.findById(booking.getVehicleId()) == null) {
            result.addMessage("Vehicle not found in database.", ResultType.INVALID);
        }

        if (userRepository.findById(booking.getUserId()) == null) {
            result.addMessage("User not found in database.", ResultType.INVALID);
        }

        if (dealershipRepository.findByLocationId(booking.getDealershipLocationId()) == null) {
            result.addMessage("Dealership not found in database.", ResultType.INVALID);
        }

        return result;

    }
}
