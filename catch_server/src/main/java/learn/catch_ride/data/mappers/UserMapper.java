package learn.catch_ride.data.mappers;

import learn.catch_ride.models.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User();
        user.setUserId(resultSet.getInt("user_id"));
        user.setUserName(resultSet.getString("user_name"));
        user.setFirstName(resultSet.getString("first_name"));
        user.setLastName(resultSet.getString("last_name"));
        user.setEmail(resultSet.getString("email"));
        user.setPassword(resultSet.getString("password"));
        user.setDateCreatedAt(resultSet.getDate("date_created_at").toLocalDate());
        user.setAdmin(resultSet.getBoolean("is_admin"));
        user.setLocationId(resultSet.getInt("location_id"));
        return user;
    }
}
