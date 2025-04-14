package learn.catch_ride.data.mappers;

import learn.catch_ride.models.Dealership;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DealershipMapper  implements RowMapper<Dealership> {
    @Override
    public Dealership mapRow(ResultSet resultSet, int i) throws SQLException {
        Dealership company = new Dealership();
        company.setDealershipId(resultSet.getInt("dealership_id"));
        company.setName(resultSet.getString("name"));
        company.setDescription(resultSet.getString("description"));
        company.setLocationId(resultSet.getInt("location_id"));
        return company;
    }
}
