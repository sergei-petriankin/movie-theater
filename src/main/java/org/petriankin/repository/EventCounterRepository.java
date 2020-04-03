package org.petriankin.repository;

import org.petriankin.utils.counter.EventCounter;
import org.petriankin.utils.counter.enums.EventCounterCases;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Repository
public class EventCounterRepository {
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    private long getCallingsByName() {
        Map<String, Object> params = new HashMap<>();
        params.put("event_counter_case", EventCounterCases.BY_NAME.ordinal());
        String sql = "SELECT * FROM event_counter WHERE event_counter_case=:event_counter_case";
        EventCounter eventCounter = namedParameterJdbcTemplate.queryForObject(sql, params, new EventCounterMapper());
        return eventCounter.getCount();
    }

    private long getCallingsByPrice() {
        Map<String, Object> params = new HashMap<>();
        params.put("event_counter_case", EventCounterCases.BY_PRICE.ordinal());
        String sql = "SELECT * FROM event_counter WHERE event_counter_case=:event_counter_case";
        EventCounter eventCounter = namedParameterJdbcTemplate.queryForObject(sql, params, new EventCounterMapper());
        return eventCounter.getCount();
    }

    private long getCallingsByBooking() {
        Map<String, Object> params = new HashMap<>();
        params.put("event_counter_case", EventCounterCases.BY_BOOKING.ordinal());
        String sql = "SELECT * FROM event_counter WHERE event_counter_case=:event_counter_case";
        EventCounter eventCounter = namedParameterJdbcTemplate.queryForObject(sql, params, new EventCounterMapper());
        return eventCounter.getCount();
    }

    public void putCallingsByName(long count) {
        long newCount = getCallingsByName() + count;
        Map<String, Object> params = new HashMap<>();
        params.put("count", newCount);
        String sql = "UPDATE event_counter SET count=:count WHERE event_counter_case = 0";
        namedParameterJdbcTemplate.update(sql, params);
    }

    public void putCallingsByPrice(long count) {
        long newCount = getCallingsByPrice() + count;
        Map<String, Object> params = new HashMap<>();
        params.put("count", newCount);
        String sql = "UPDATE event_counter SET count=:count WHERE event_counter_case = 1";
        namedParameterJdbcTemplate.update(sql, params);
    }

    public void putCallingsByBooking(long count) {
        long newCount = getCallingsByBooking() + count;
        Map<String, Object> params = new HashMap<>();
        params.put("count", newCount);
        String sql = "UPDATE event_counter SET count=:count WHERE event_counter_case = 2";
        namedParameterJdbcTemplate.update(sql, params);
    }

    private static final class EventCounterMapper implements RowMapper<EventCounter> {
        @Override
        public EventCounter mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            EventCounter eventCounter = new EventCounter();
            eventCounter.setId(resultSet.getLong("id"));
            eventCounter.setEventCounterCases(EventCounterCases.values()[resultSet.getInt("event_counter_case")]);
            eventCounter.setCount(resultSet.getLong("count"));
            return eventCounter;
        }
    }


}
