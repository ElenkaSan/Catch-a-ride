package learn.catch_ride.data;

import learn.catch_ride.data.mappers.BookingMapper;
import learn.catch_ride.models.Booking;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

@Repository
public class BookingJdbcTemplateRepository implements BookingRepository{

    private final JdbcTemplate jdbcTemplate;

    public BookingJdbcTemplateRepository(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    @Override
    public List<Booking> findAll() {
        final String sql = "select booking_id, vehicle_id, user_id, dealership_location_id, start_date, end_date, booking_type, date_created_at, total_cost "
                + "from booking;";
        return jdbcTemplate.query(sql, new BookingMapper());
    }

    @Override
    public List<Booking> findByLocation(int locationId) {
        final String sql = "select booking_id, vehicle_id, user_id, dealership_location_id, start_date, end_date, booking_type, date_created_at, total_cost "
                + "from booking "
                + "where dealership_location_id = ?;";
        return jdbcTemplate.query(sql, new BookingMapper(), locationId);
    }

    @Override
    public Booking findById(int bookingId) {
        final String sql = "select booking_id, vehicle_id, user_id, dealership_location_id, start_date, end_date, booking_type, date_created_at, total_cost "
                + "from booking "
                + "where booking_id = ?;";

        return jdbcTemplate.query(sql, new BookingMapper(), bookingId).stream()
                .findFirst().orElse(null);
    }

    @Override
    public List<Booking> findByUserId(int userId) {
       // final String sql = "select * from booking where user_id = ?";
        final String sql = "select booking_id, vehicle_id, user_id, dealership_location_id, start_date, end_date, booking_type, date_created_at, total_cost "
                + "from booking where user_id = ?";
        return jdbcTemplate.query(sql, new BookingMapper(), userId);
    }

    @Override
    public Booking add(Booking booking) {
        final String sql = "insert into booking (vehicle_id, user_id, dealership_location_id, start_date, end_date, booking_type, date_created_at, total_cost) "
                + "values (?,?,?,?,?,?,?,?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, booking.getVehicleId());
            ps.setInt(2, booking.getUserId());
            ps.setInt(3, booking.getDealershipLocationId());
            ps.setDate(4, Date.valueOf(booking.getStartDate()));
            ps.setDate(5, Date.valueOf(booking.getEndDate()));
            ps.setString(6, booking.getBookingType().toString());
            ps.setDate(7, Date.valueOf(LocalDate.now()));
            ps.setBigDecimal(8, booking.getTotal());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        booking.setBookingId(keyHolder.getKey().intValue());
        return booking;
    }

    @Override
    public boolean update(Booking booking) {
        final String sql = "update booking set "
                + "vehicle_id = ?, "
                + "user_id = ?, "
                + "dealership_location_id = ?, "
                + "start_date = ?, "
                + "end_date = ?, "
                + "date_created_at = ?, "
                + "total_cost = ? "
                + "where booking_id = ?;";

        return jdbcTemplate.update(sql,
                booking.getVehicleId(),
                booking.getUserId(),
                booking.getDealershipLocationId(),
                booking.getStartDate(),
                booking.getEndDate(),
                booking.getDateCreated(),
                booking.getTotal(),
                booking.getBookingId()) > 0;
    }

    @Transactional
    @Override
    public boolean deleteById(int bookingId) {
        return jdbcTemplate.update("delete from booking where booking_id = ?", bookingId) > 0;
    }
}
