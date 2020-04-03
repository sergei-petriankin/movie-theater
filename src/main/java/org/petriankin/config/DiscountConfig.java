package org.petriankin.config;

import org.petriankin.service.DiscountService;
import org.petriankin.service.discount.DiscountStrategy;
import org.petriankin.service.discount.DiscountStrategyBirthday;
import org.petriankin.service.discount.DiscountStrategyTenthTicket;
import org.petriankin.service.impl.DiscountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Configuration
@ComponentScan("org.petriankin.service.discount")
public class DiscountConfig {

    @Autowired
    private DiscountStrategyBirthday discountStrategyBirthday;

    @Autowired
    private DiscountStrategyTenthTicket discountStrategyTenthTicket;

    @Bean
    public DiscountService discountService() {
        List<DiscountStrategy> discountStrategies = new ArrayList<>();
        Collections.addAll(discountStrategies, discountStrategyBirthday, discountStrategyTenthTicket);
        return new DiscountServiceImpl(discountStrategies);
    }
}
