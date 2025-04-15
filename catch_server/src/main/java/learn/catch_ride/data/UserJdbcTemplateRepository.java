package learn.catch_ride.data;

import learn.catch_ride.data.mappers.UserMapper;
import learn.catch_ride.models.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class UserJdbcTemplateRepository  implements UserRepository{
    private final JdbcTemplate jdbcTemplate;

    public UserJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<User> findAll() {
        final String sql = "select user_id, first_name, last_name, email, password, date_created_at, location_id, is_admin "
                + "from user limit 1000;";
        return jdbcTemplate.query(sql, new UserMapper());
    }

    @Override
    @Transactional
    public User findById(int userId) {

        final String sql = "select user_id, first_name, last_name, email, password, date_created_at, location_id, is_admin "
                + "from user "
                + "where user_id = ?;";

        User user = jdbcTemplate.query(sql, new UserMapper(), userId).stream()
                .findFirst().orElse(null);


        return user;
    }

    @Override
    public User add(User user) {

        final String sql = "insert into user (user_id, first_name, last_name, email, password, date_created_at, location_id, is_admin) "
                + " values (?,?,?,?,?,?,?,?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, user.getUserId());
            ps.setString(2, user.getFirstName());
            ps.setString(3, user.getLastName());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getPassword());
            ps.setDate(6, java.sql.Date.valueOf(user.getDateCreatedAt().toString()));
            ps.setInt(7, user.getLocationId());
            ps.setBoolean(8, user.isAdmin());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        user.setUserId(keyHolder.getKey().intValue());
        return user;
    }

    @Override
    public boolean update(User user) {

        final String sql = "update user set "
                + "first_name = ?, "
                + "last_name = ?, "
                + "email = ?, "
                + "password = ?, "
                + "date_created_at = ?, "
                + "location_id = ?, "
                + "is_admin = ? "
                + "where user_id = ?;";

        return jdbcTemplate.update(sql,
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword(),
                user.getDateCreatedAt(),
                user.getLocationId(),
                user.isAdmin(),
                user.getUserId()) > 0;
    }

    @Override
    @Transactional
    public boolean deleteById(int userId) {
        return jdbcTemplate.update("delete from user where user_id = ?;", userId) > 0;
    }


    @Override
    public int getUsageCount(int userId) {
        int count = 0;
        count = count + jdbcTemplate.queryForObject(
                "select count(*) from booking where location_id = ?;", Integer.class, userId);
        return count;
    }
}
