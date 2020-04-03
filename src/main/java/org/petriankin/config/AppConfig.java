package org.petriankin.config;

import org.petriankin.domain.Auditorium;
import org.petriankin.repository.*;
import org.petriankin.repository.impl.EventRepositoryImpl;
import org.petriankin.repository.impl.TicketRepositoryImpl;
import org.petriankin.repository.impl.UserRepositoryImpl;
import org.petriankin.service.*;
import org.petriankin.service.impl.AuditoriumServiceImpl;
import org.petriankin.service.impl.BookingServiceImpl;
import org.petriankin.service.impl.EventServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Configuration
@EnableAspectJAutoProxy
@Import({DiscountConfig.class, AuditoriumConfig.class, DBConfig.class})
@ComponentScan({"org.petriankin"})
public class AppConfig {
    @Autowired
    DiscountConfig discountConfig;
    @Autowired
    AuditoriumConfig auditoriumConfig;
    @Autowired
    DBConfig dbConfig;

    @Bean
    public BookingService bookingService() {
        return new BookingServiceImpl(discountConfig.discountService(), ticketRepository());
    }

    @Bean
    public AuditoriumService auditoriumService() {
        Set<Auditorium> auditoriums = new HashSet<>();
        Collections.addAll(auditoriums, auditoriumConfig.bigAuditorium(), auditoriumConfig.mediumAuditorium(), auditoriumConfig.smallAuditorium());
        return new AuditoriumServiceImpl(auditoriums);
    }

    @Bean
    public EventService eventService() {
        return new EventServiceImpl(eventRepository());
    }

    @Bean
    public UserService userService() {
        return new UserServiceImpl(userRepository());
    }

    @Bean
    public DiscountCounterRepository discountCounterRepository() {
        return new DiscountCounterRepository();
    }

    @Bean
    public EventCounterRepository eventCounterRepository() {
        return new EventCounterRepository();
    }

    @Bean
    public EventRepository eventRepository() {
        return new EventRepositoryImpl();
    }

    @Bean
    public TicketRepository ticketRepository() {
        return new TicketRepositoryImpl();
    }

    @Bean
    public UserRepository userRepository() {
        return new UserRepositoryImpl();
    }


}
