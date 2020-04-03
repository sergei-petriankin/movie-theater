package org.petriankin.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.petriankin.repository.EventCounterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CounterAspect {
    private final EventCounterRepository eventCounterRepository;

    @Autowired
    public CounterAspect(EventCounterRepository eventCounterRepository) {
        this.eventCounterRepository = eventCounterRepository;
    }

    @After("execution(* com.epam.spring.hometask.service.EventService.getByName(..))")
    private void countCallingsByName(JoinPoint jp) {
        eventCounterRepository.putCallingsByName(1);
    }

    @After("execution(* com.epam.spring.hometask.service.BookingService.getTicketsPrice(..))")
    private void countGettingPrice(JoinPoint jp) {
        eventCounterRepository.putCallingsByPrice(1);
    }

    @After("execution(* com.epam.spring.hometask.service.BookingService.bookTickets(..))")
    private void countBooking(JoinPoint jp) {
        eventCounterRepository.putCallingsByBooking(1);
    }


}
