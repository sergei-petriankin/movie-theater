package org.petriankin.config;

import org.petriankin.service.DiscountService;
import org.petriankin.service.discount.DiscountStrategy;
import org.petriankin.service.discount.DiscountStrategyBirthday;
import org.petriankin.service.discount.DiscountStrategyTenthTicket;
import org.petriankin.service.impl.DiscountServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Configuration
public class DiscountConfig {

    @Bean
    public DiscountService discountService() {
        List<DiscountStrategy> discountStrategies = new ArrayList<>();
        Collections.addAll(discountStrategies, discountStrategyBirthday(), discountStrategyTenthTicket());
        return new DiscountServiceImpl(discountStrategies);
    }

    @Bean
    public DiscountStrategy discountStrategyBirthday() {
        return new DiscountStrategyBirthday();
    }

    @Bean
    public DiscountStrategy discountStrategyTenthTicket() {
        return new DiscountStrategyTenthTicket();
    }


}
