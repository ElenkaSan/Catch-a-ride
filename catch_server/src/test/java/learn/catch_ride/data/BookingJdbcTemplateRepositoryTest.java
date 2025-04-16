package learn.catch_ride.data;

import learn.catch_ride.models.Booking;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static learn.catch_ride.models.BookingType.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class BookingJdbcTemplateRepositoryTest {

    final static int NEXT_ID = 4;

    @Autowired
    BookingJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() { knownGoodState.set(); }

    @Test
    void findAll() {
        List<Booking> bookings = repository.findAll();
        assertNotNull(bookings);

        assertTrue(bookings.size() >= 3);
    }

    @Test
    void shouldFindByLocation() {
        List<Booking> bookings = repository.findByLocation(1);
        assertNotNull(bookings);

        assertEquals(3, bookings.size());
    }

    @Test
    void shouldFindById() {
        Booking booking = repository.findById(1);
        assertEquals(1, booking.getBookingId());
        assertEquals(1, booking.getUserId());
        assertEquals(RENT, booking.getBookingType());
        assertEquals(new BigDecimal("360"), booking.getTotal());
    }

    @Test
    void shouldAdd() {
        Booking booking = makeBooking();
        Booking actual = repository.add(booking);
        assertNotNull(actual);
        assertEquals(NEXT_ID, actual.getBookingId());
    }

    @Test
    void shouldUpdate() {
        Booking booking = makeBooking();
        booking.setBookingId(3);
        assertTrue(repository.update(booking));
        booking.setBookingId(13);
        assertFalse(repository.update(booking));
    }

    @Test
    void shouldDeleteById() {
        assertTrue(repository.deleteById(2));
        assertFalse(repository.deleteById(2));
    }

    private Booking makeBooking() {
        Booking booking = new Booking();
        booking.setVehicleId(1);
        booking.setUserId(1);
        booking.setDealershipLocationId(2);
        booking.setStartDate(LocalDate.parse("2025-08-01"));
        booking.setEndDate(LocalDate.parse("2025-08-07"));
        booking.setDateCreated(LocalDate.now());
        booking.setTotal(BigDecimal.valueOf(420.00));
        booking.setBookingType(RENT);
        return booking;
    }
}