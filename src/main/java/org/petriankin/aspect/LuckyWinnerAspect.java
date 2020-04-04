package org.petriankin.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.petriankin.domain.Ticket;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.Set;

@Aspect
@Component
public class LuckyWinnerAspect {

    private static final double CHANCE_TO_WIN = 0.2;

    @Around("execution(* org.petriankin.service.impl.BookingServiceImpl.bookTickets(..))")
    private void checkLucky(ProceedingJoinPoint joinPoint) throws Throwable {
        if (!isLucky()) {
            joinPoint.proceed();
        } else {
            Set<Ticket> ticketSet = (Set<Ticket>) joinPoint.getArgs()[0];
            for (Ticket ticket : ticketSet) {
                ticket.getEvent().setBasePrice(0);
            }
            joinPoint.proceed();
        }
    }

    private boolean isLucky() {
        Random random = new Random();
        return random.nextDouble() <= CHANCE_TO_WIN;
    }

}
