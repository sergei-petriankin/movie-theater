package org.petriankin.repository.impl;

import org.petriankin.domain.User;
import org.petriankin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public User save(@Nonnull User user) {
        Map<String, Object> params = new HashMap<>();
        params.put("first_name", user.getFirstName());
        params.put("last_name", user.getLastName());
        params.put("dob", user.getDateOfBirth());
        String sql = "INSERT INTO users(first_name, last_name, dob) VALUES (:first_name, :last_name, :dob)";
        namedParameterJdbcTemplate.update(sql, params);
        return user;
    }

    @Override
    public void remove(@Nonnull User user) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", user.getId());
        String sql = "DELETE FROM users WHERE id=:id";
        namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public User getById(@Nonnull Long id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        String sql = "SELECT * FROM users WHERE id=:id";
        return namedParameterJdbcTemplate.queryForObject(sql, params, new UserMapper());
    }

    @Nonnull
    @Override
    public Map<Long, User> getAll() {
        Map<Long, User> users = new HashMap<>();
        Map<String, Object> params = new HashMap<>();
        String sql = "SELECT * FROM users;";
        List<User> query = namedParameterJdbcTemplate.query(sql, params, new UserMapper());
        for (User user : query) {
            users.put(user.getId(), user);
        }
        return users;
    }

    @Nullable
    @Override
    public User getUserByEmail(@Nonnull String email) {
        Map<String, Object> params = new HashMap<>();
        params.put("email", email);
        String sql = "SELECT * FROM users WHERE email=:email";
        return namedParameterJdbcTemplate.queryForObject(
                sql,
                params,
                new UserMapper());
    }

    private static final class UserMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            User user = new User();
            user.setId(resultSet.getLong("id"));
            user.setFirstName(resultSet.getString("first_name"));
            user.setLastName(resultSet.getString("last_name"));
            user.setEmail(resultSet.getString("email"));
            user.setDateOfBirth(resultSet.getDate("dob").toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            return user;
        }
    }
}
