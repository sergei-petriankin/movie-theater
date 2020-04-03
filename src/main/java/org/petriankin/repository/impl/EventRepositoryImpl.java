package org.petriankin.repository.impl;

import org.petriankin.domain.Event;
import org.petriankin.domain.enums.EventRating;
import org.petriankin.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class EventRepositoryImpl implements EventRepository {
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Nullable
    @Override
    public Event getByName(@Nonnull String name) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        String sql = "SELECT * FROM events WHERE name=:name";
        return namedParameterJdbcTemplate.queryForObject(sql, params, new EventMapper());
    }

    @Override
    public Event save(@Nonnull Event event) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", event.getName());
        params.put("base_price", event.getBasePrice());
        params.put("rating", event.getRating().ordinal());
        String sql = "INSERT INTO events(name, base_price, rating) VALUES (:name, :base_price, :rating)";
        namedParameterJdbcTemplate.update(sql, params);
        return event;
    }

    @Override
    public void remove(@Nonnull Event event) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", event.getId());
        String sql = "DELETE FROM events WHERE id=:id";
        namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public Event getById(@Nonnull Long id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        String sql = "SELECT * FROM events WHERE id=:id";
        return namedParameterJdbcTemplate.queryForObject(sql, params, new EventMapper());
    }

    @Nonnull
    @Override
    public Map<Long, Event> getAll() {
        Map<Long, Event> result = new HashMap<>();
        Map<String, Object> params = new HashMap<>();
        String sql = "SELECT * FROM events";
        List<Event> query = namedParameterJdbcTemplate.query(sql, params, new EventMapper());
        for (Event event : query) {
            result.put(event.getId(), event);
        }
        return result;
    }


    private static final class EventMapper implements RowMapper<Event> {
        @Override
        public Event mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            Event event = new Event();
            event.setName(resultSet.getString("name"));
            event.setBasePrice(resultSet.getDouble("base_price"));
            event.setRating(EventRating.values()[resultSet.getInt("rating")]);
            return event;
        }
    }
}
