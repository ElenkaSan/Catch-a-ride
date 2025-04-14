package learn.catch_ride.data;

import learn.catch_ride.data.mappers.VehicleMapper;
import learn.catch_ride.models.Vehicle;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VehicleJdbcTemplateRepository implements VehicleRepository {
    private final JdbcTemplate jdbcTemplate;

    public VehicleJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Vehicle findById(int vehicleId) {
        return null;
    }

    @Override
    public List<Vehicle> findAll() {
        final String sql = "select vehicle_id, make, model, year, color, trim, dealership_id, rent_rate, lease_rate  "
                + "from vehicle limit 1000;";
        return jdbcTemplate.query(sql, new VehicleMapper());
    }

    @Override
    public Vehicle add(Vehicle vehicle) {
        return null;
    }

    @Override
    public boolean update(Vehicle vehicle) {
        return false;
    }

    @Override
    public boolean deleteById(int vehicleId) {
        return false;
    }


}
