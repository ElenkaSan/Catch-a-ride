package learn.catch_ride.data.mappers;

import learn.catch_ride.models.Vehicle;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VehicleMapper implements RowMapper<Vehicle> {
    @Override
    public Vehicle mapRow(ResultSet resultSet, int i) throws SQLException {
        Vehicle car = new Vehicle();
        car.setVehichleId(resultSet.getInt("vehicle_id"));
        car.setMake(resultSet.getString("make"));
        car.setModel(resultSet.getString("model"));
        car.setYear(resultSet.getInt("year"));
        car.setColor(resultSet.getString("color"));
        car.setTrim(resultSet.getString("trim"));
        car.setDealershipId(resultSet.getInt("dealership_id"));
        car.setRentRate(resultSet.getBigDecimal("rent_rate"));
        car.setLeaseRate(resultSet.getBigDecimal("lease_rate"));
        return car;
    }
}

