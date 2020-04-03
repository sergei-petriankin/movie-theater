package org.petriankin.repository;

import org.petriankin.domain.User;
import org.petriankin.service.discount.DiscountStrategy;
import org.petriankin.utils.counter.DiscountCounter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class DiscountCounterRepository {
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public void save(User user, DiscountStrategy discountStrategy) {
        Map<String, Object> params = new HashMap<>();
        params.put("discount_strategy", discountStrategy.getClass().getSimpleName());
        if (user != null) {
            params.put("count", getDiscountCounterByUser(user) + 1);
            params.put("user_id", user.getId());
        }
        else {
            params.put("count", getDiscountCounterByUser(new User()) + 1);
            params.put("user_id", new User().getId());
        }
        String sql = "INSERT INTO discount_counter(discount_strategy, count, user_id) VALUES (:discount_strategy, :count, :user_id)";
        namedParameterJdbcTemplate.update(sql, params);
    }

    private long getDiscountCounterByUser(User user) {
        Map<String, Object> params = new HashMap<>();
        params.put("user_id", user.getId());
        String sql = "SELECT * FROM discount_counter WHERE user_id=:user_id";
        List<DiscountCounter> discountCounters = namedParameterJdbcTemplate.query(sql, params, new DiscountCounterMapper());
        long count = 0;
        for (DiscountCounter discountCounter : discountCounters) {
            count += discountCounter.getCount();
        }
        return count;
    }

    public long getDiscountCounterInTotal(DiscountStrategy discountStrategy) {
        String simpleName = discountStrategy.getClass().getSimpleName();
        Map<String, Object> params = new HashMap<>();
        params.put("discount_strategy", simpleName);
        String sql = "SELECT * FROM discount_counter WHERE discount_strategy=:discount_strategy";
        List<DiscountCounter> discountCounters = namedParameterJdbcTemplate.query(sql, params, new DiscountCounterMapper());
        long count = 0;
        for (DiscountCounter discountCounter : discountCounters) {
            count += discountCounter.getCount();
        }
        return count;
    }

    private static final class DiscountCounterMapper implements RowMapper<DiscountCounter> {
        @Override
        public DiscountCounter mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            DiscountCounter discountCounter = new DiscountCounter();
            discountCounter.setId(resultSet.getLong("is"));
            discountCounter.setDiscountStrategyName(resultSet.getString("discount_strategy"));
            discountCounter.setCount(resultSet.getLong("count"));
            return discountCounter;
        }
    }


}
