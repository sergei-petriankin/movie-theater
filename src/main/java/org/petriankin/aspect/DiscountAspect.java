package org.petriankin.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.petriankin.domain.User;
import org.petriankin.repository.DiscountCounterRepository;
import org.petriankin.service.discount.DiscountStrategyBirthday;
import org.petriankin.service.discount.DiscountStrategyTenthTicket;
import org.springframework.aop.framework.Advised;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DiscountAspect {

    private final DiscountCounterRepository discountCounterRepository;

    @Autowired
    public DiscountAspect(DiscountCounterRepository discountCounterRepository) {
        this.discountCounterRepository = discountCounterRepository;
    }

    @After("execution(*  org.petriankin.service.discount.DiscountStrategyBirthday.getDiscount(..))")
    private void countBirthdayDiscountsCalling(JoinPoint jp) throws Exception {
        User user = (User) jp.getArgs()[0];
        if (user != null) {
            DiscountStrategyBirthday discountStrategyBirthday = (DiscountStrategyBirthday) ((Advised) jp.getThis()).getTargetSource().getTarget();
            discountCounterRepository.save(user, discountStrategyBirthday);
        }
    }

    @After("execution(*  org.petriankin.service.discount.DiscountStrategyTenthTicket.getDiscount(..))")
    private void countTenthTicketDiscountsCalling(JoinPoint jp) throws Exception {
        User user = (User) jp.getArgs()[0];
        if (user != null) {
            DiscountStrategyTenthTicket discountStrategyTenthTicket = (DiscountStrategyTenthTicket) ((Advised) jp.getThis()).getTargetSource().getTarget();
            discountCounterRepository.save(user, discountStrategyTenthTicket);
        }
    }
}
