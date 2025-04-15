package learn.catch_ride.data;

import learn.catch_ride.data.mappers.VehicleMapper;
import learn.catch_ride.models.Vehicle;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class VehicleJdbcTemplateRepository implements VehicleRepository {
    private final JdbcTemplate jdbcTemplate;
    private final String VEHICLE_COLUMN_NAMES = "vehicle_id, make, model, year, color, trim, dealership_id, rent_rate, lease_rate";

    public VehicleJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Vehicle findById(int vehicleId) {
        final String sql = String.format("select %s from vehicle " + "where vehicle_id = ?;", VEHICLE_COLUMN_NAMES);
        Vehicle result = jdbcTemplate.query(sql, new VehicleMapper(), vehicleId).stream()
                .findAny().orElse(null);
        return result;
    }

    @Override
    public List<Vehicle> findAll() {
       // final String sql = "select vehicle_id, make, model, year, color, trim, dealership_id, rent_rate, lease_rate  "
        //        + "from vehicle limit 1000;";
        final String sql = String.format("select %s from vehicle limit 1000;", VEHICLE_COLUMN_NAMES);
        return jdbcTemplate.query(sql, new VehicleMapper());
    }

    @Override
    public Vehicle add(Vehicle vehicle) {
        final String sql = "insert into vehicle (make, model, year, color, trim, dealership_id, rent_rate, lease_rate) "
                + " values (?,?,?,?,?,?,?,?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, vehicle.getMake());
            ps.setString(2, vehicle.getModel());
            ps.setInt(3, vehicle.getYear());
            ps.setString(4, vehicle.getColor());
            ps.setString(5, vehicle.getTrim());
            ps.setInt(6, vehicle.getDealershipId());
            ps.setBigDecimal(7, vehicle.getRentRate());
            ps.setBigDecimal(8, vehicle.getLeaseRate());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        vehicle.setVehichleId(keyHolder.getKey().intValue());
        return vehicle;
    }

    @Override
    public boolean update(Vehicle vehicle) {
        final String sql = "update vehicle set "
                + "make = ?, "
                + "model = ?, "
                + "year = ?, "
                + "color = ?, "
                + "trim = ?, "
                + "dealership_id = ?, "
                + "rent_rate = ?, "
                + "lease_rate = ? "
                + "where vehicle_id = ?;";

        return jdbcTemplate.update(sql,
                vehicle.getMake(),
                vehicle.getModel(),
                vehicle.getYear(),
                vehicle.getColor(),
                vehicle.getTrim(),
                vehicle.getDealershipId(),
                vehicle.getRentRate(),
                vehicle.getLeaseRate(),
                vehicle.getVehichleId()) > 0;
    }

    @Override
    public boolean deleteById(int vehicleId) {
        final String sql = "select count(*) from booking where vehicle_id = ?;"; //checking if car booked by user
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, vehicleId);
        if (count != null && count > 0) { //if using will not delete
            return false;
        }
        return jdbcTemplate.update(
                "delete from vehicle where vehicle_id = ?;",
                vehicleId) > 0;
    }

    @Override
    public List<Vehicle> findByDealershipId(int dealershipId) {
        final String sql = String.format("select %s from vehicle where dealership_id = ?;", VEHICLE_COLUMN_NAMES);
        return jdbcTemplate.query(sql, new VehicleMapper(), dealershipId);
    }
}
