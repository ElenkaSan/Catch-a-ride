package learn.catch_ride.data.mappers;

import learn.catch_ride.models.Location;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LocationMapper implements RowMapper<Location> {

    @Override
    public Location mapRow(ResultSet resultSet, int i) throws SQLException {
        Location location = new Location();
        location.setLocationId(resultSet.getInt("location_id"));
        location.setAddress(resultSet.getString("address"));
        location.setCity(resultSet.getString("city"));
        location.setState(resultSet.getString("state"));
        location.setZipCode(resultSet.getInt("zip_code"));
        return location;
    }
}
