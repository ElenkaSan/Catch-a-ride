package learn.catch_ride.domain;

import learn.catch_ride.data.BookingRepository;
import learn.catch_ride.data.DealershipRepository;
import learn.catch_ride.data.UserRepository;
import learn.catch_ride.data.VehicleRepository;
import learn.catch_ride.models.Booking;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;

import static learn.catch_ride.models.BookingType.RENT;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class BookingServiceTest {

    @Autowired
    BookingService service;

    @MockBean
    BookingRepository bookingRepository;
    @MockBean
    VehicleRepository vehicleRepository;
    @MockBean
    UserRepository userRepository;
    @MockBean
    DealershipRepository dealershipRepository;

    @Test
    void shouldNotFindBooking() {
        assertNull(service.findById(999));
    }

    @Test
    void shouldNotAddWhenNull() {
        Result<Booking> result = service.add(null);
        assertFalse(result.isSuccess());
    }

    @Test
    void shouldNotAddWithNullStartDate() {
        Booking booking = makeBooking();
        booking.setStartDate(null);

        Result<Booking> result = service.add(booking);
        assertFalse(result.isSuccess());
    }

    @Test
    void shouldNotAddWithNullEndDate() {
        Booking booking = makeBooking();
        booking.setEndDate(null);

        Result<Booking> result = service.add(booking);
        assertFalse(result.isSuccess());
    }

    @Test
    void shouldNotAddWhenVehicleNotFound() {
        Booking booking = makeBooking();
        booking.setVehicleId(999);

        Result<Booking> result = service.add(booking);
        assertFalse(result.isSuccess());
    }

    @Test
    void shouldNotAddWhenUserNotFound() {
        Booking booking = makeBooking();
        booking.setUserId(999);

        Result<Booking> result = service.add(booking);
        assertFalse(result.isSuccess());
    }

    @Test
    void shouldNotAddWhenDealershipNotFound() {
        Booking booking = makeBooking();
        booking.setDealershipLocationId(9999);

        Result<Booking> result = service.add(booking);
        assertFalse(result.isSuccess());
    }

    @Test
    void shouldNotAddWhenStartDateIsInPast() {
        Booking booking = makeBooking();
        booking.setStartDate(LocalDate.now().minusDays(1));

        Result<Booking> result = service.add(booking);
        assertFalse(result.isSuccess());
    }

    @Test
    void ShouldNotAddWhenEndDateIsBeforeStartDate() {
        Booking booking = makeBooking();
        booking.setEndDate(booking.getStartDate().minusDays(1));  // End date before start date

        Result<Booking> result = service.add(booking);
        assertFalse(result.isSuccess());
    }

    @Test
    void shouldNotAddWhenDatesOverlap() {
        Booking booking = makeBooking();
        booking.setStartDate(LocalDate.parse("2025-06-18"));
        booking.setEndDate(LocalDate.parse("2025-06-22"));

        Result<Booking> result = service.add(booking);
        assertFalse(result.isSuccess());
    }

    @Test
    void shouldNotAddWhenBookingIdAssigned() {
        Booking booking = makeBooking();
        booking.setBookingId(1);  // Set an ID, which should not happen for an add operation

        Result<Booking> result = service.add(booking);
        assertFalse(result.isSuccess());
    }

    @Test
    void shouldNotDeleteAfterStartDate() {
        Booking booking = makeBooking();
        booking.setStartDate(LocalDate.now().minusDays(1)); // Set a past start date


        Result<Booking> result = service.deleteById(booking.getBookingId());
        assertFalse(result.isSuccess());
    }

    private Booking makeBooking() {
        Booking booking = new Booking();
        booking.setVehicleId(1);
        booking.setUserId(1);
        booking.setDealershipLocationId(1);
        booking.setStartDate(LocalDate.parse("2025-08-01"));
        booking.setEndDate(LocalDate.parse("2025-08-07"));
        booking.setDateCreated(LocalDate.now());
        booking.setBookingType(RENT);
        return booking;
    }

}