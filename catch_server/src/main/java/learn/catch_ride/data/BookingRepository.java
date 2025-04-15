package learn.catch_ride.data;

import learn.catch_ride.models.Booking;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BookingRepository {
    List<Booking> findAll();

    List<Booking> findByLocation(int locationId);

    Booking findById(int bookingId);

    Booking add(Booking booking);

    boolean update(Booking booking);

    @Transactional
    boolean deleteById(int bookingId);
}
