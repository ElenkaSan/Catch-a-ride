package learn.catch_ride.data;

import learn.catch_ride.data.mappers.LocationMapper;
import learn.catch_ride.models.Location;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class LocationJdbcTemplateRepository implements LocationRepository{
    private final JdbcTemplate jdbcTemplate;

    public LocationJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public List<Location> findAll() {
        final String sql = "select location_id, address, city, state, zip_code "
                + "from location limit 1000;";
        return jdbcTemplate.query(sql, new LocationMapper());
    }

    @Override
    @Transactional
    public Location findById(int locationId) {

        final String sql = "select location_id, address, city, state, zip_code "
                + "from location "
                + "where location_id = ?;";

        Location location = jdbcTemplate.query(sql, new LocationMapper(), locationId).stream()
                .findFirst().orElse(null);


        return location;
    }

    @Override
    public Location add(Location location) {

        final String sql = "insert into location (location_id, address, city, state, zip_code) "
                + " values (?,?,?,?,?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, location.getLocationId());
            ps.setString(2, location.getAddress());
            ps.setString(3, location.getCity());
            ps.setString(4, location.getState());
            ps.setInt(5, location.getZipCode());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        location.setLocationId(keyHolder.getKey().intValue());
        return location;
    }

    @Override
    public boolean update(Location location) {

        final String sql = "update location set "
                + "address = ?, "
                + "city = ?, "
                + "state = ?, "
                + "zip_code = ? "
                + "where location_id = ?;";

        return jdbcTemplate.update(sql,
                location.getAddress(),
                location.getCity(),
                location.getState(),
                location.getZipCode(),
                location.getLocationId()) > 0;
    }

    @Override
    @Transactional
    public boolean deleteById(int locationId) {
        return jdbcTemplate.update("delete from location where location_id = ?;", locationId) > 0;
    }


    @Override
    public int getUsageCount(int locationId) {
        int count = 0;
        count = count + jdbcTemplate.queryForObject(
                "select count(*) from dealership where location_id = ?;", Integer.class, locationId);
        count = count + jdbcTemplate.queryForObject(
                "select count(*) from user where location_id = ?;", Integer.class, locationId);
        count = count + jdbcTemplate.queryForObject(
                "select count(*) from booking where location_id = ?;", Integer.class, locationId);
        return count;
    }
}
