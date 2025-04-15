package learn.catch_ride.data;


import learn.catch_ride.data.mappers.DealershipMapper;
import learn.catch_ride.data.mappers.VehicleMapper;
import learn.catch_ride.models.Dealership;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class DealershipJdbcTemplateRepository implements DealershipRepository {
    private final JdbcTemplate jdbcTemplate;
    private final String DEALERSHIP_COLUMN_NAMES = "dealership_id, `name`, `description`, location_id";

    public DealershipJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Dealership findById(int dealershipId) {
      //  final String sql = "select dealership_id, `name`, `description`, location_id "
      //          + "from dealership "
      //         + "where dealership_id = ?;";

        final String sql = String.format("select %s from dealership where dealership_id = ?;", DEALERSHIP_COLUMN_NAMES);
        Dealership company = jdbcTemplate.query(sql, new DealershipMapper(), dealershipId).stream()
                .findFirst().orElse(null);

        if (company != null) {
            addVehicle(company);
        }

        return company;
    }

    @Override
    public List<Dealership> findByName(String name) {
        final String sql = String.format("select %s from dealership where `name` = ?;", DEALERSHIP_COLUMN_NAMES);
        return jdbcTemplate.query(sql, new DealershipMapper(), name);
    }

    @Override
    public List<Dealership> findAll() {
      //  final String sql = "select dealership_id, name, description, location_id  "
      //          + "from dealership limit 1000;";
        final String sql = String.format("select %s from dealership limit 1000;", DEALERSHIP_COLUMN_NAMES);
        return jdbcTemplate.query(sql, new DealershipMapper());
    }

    @Override
    public Dealership add(Dealership dealership) {
        final String sql = "insert into dealership (`name`, `description`, location_id) "
                + " values (?,?,?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, dealership.getName());
            ps.setString(2, dealership.getDescription());
            ps.setInt(3, dealership.getLocationId());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        dealership.setDealershipId(keyHolder.getKey().intValue());
        return dealership;
    }

    @Override
    public boolean update(Dealership dealership) {
        final String sql = "update dealership set "
                + "`name` = ?, "
                + "`description` = ?, "
                + "location_id = ? "
                + "where dealership_id = ?;";

        return jdbcTemplate.update(sql,
                dealership.getName(),
                dealership.getDescription(),
                dealership.getLocationId(),
                dealership.getDealershipId()) > 0;
    }

    @Override
    public boolean deleteById(int dealershipId) {
    //    jdbcTemplate.update("delete from vehicle where vehicle_id = ?;", dealershipId); wrong..
        jdbcTemplate.update("delete from vehicle where dealership_id = ?;", dealershipId);
        return jdbcTemplate.update("delete from dealership where dealership_id = ?;", dealershipId) > 0;
    }

    @Override
    public List<Dealership> findByLocationId(int locationId) {
        final String sql = String.format("select %s from dealership where location_id = ?;", DEALERSHIP_COLUMN_NAMES);
        return jdbcTemplate.query(sql, new DealershipMapper(), locationId);
    }

    private void addVehicle(Dealership company) {
        final String sql = "select vehicle_id, make, model, year, color, trim, dealership_id, rent_rate, lease_rate, booking_status  "
                + "from vehicle "
                + "where dealership_id = ?;";
        var cars = jdbcTemplate.query(sql, new VehicleMapper(), company.getDealershipId());
        company.setCars(cars);
    }
}
