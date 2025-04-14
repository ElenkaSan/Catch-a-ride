package learn.catch_ride.data.mappers;

import learn.catch_ride.models.Booking;
import learn.catch_ride.models.BookingType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookingMapper implements RowMapper<Booking> {

    @Override
    public Booking mapRow(ResultSet resultSet, int i) throws SQLException {
        Booking booking = new Booking();
        booking.setBookingId(resultSet.getInt("booking_id"));
        booking.setVehicleId(resultSet.getInt("vehicle_id"));
        booking.setUserId(resultSet.getInt("user_id"));
        booking.setDealershipLocationId(resultSet.getInt("dealership_location_id"));
        booking.setStartDate(resultSet.getDate("start_date").toLocalDate());
        booking.setEndDate(resultSet.getDate("end_date").toLocalDate());
        booking.setBookingType(BookingType.valueOf(resultSet.getString("booking_type")));
        booking.setDateCreated(resultSet.getDate("date_created_at").toLocalDate());
        booking.setTotal(resultSet.getBigDecimal("total_cost"));
        return booking;
    }

}
