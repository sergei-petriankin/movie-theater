package org.petriankin;

import org.petriankin.config.AppConfig;
import org.petriankin.config.AuditoriumConfig;
import org.petriankin.config.DiscountConfig;
import org.petriankin.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
class App {
    private final UserService userService;
    private final EventService eventService;
    private final BookingService bookingService;
    private final AuditoriumService auditoriumService;
    private final DiscountService discountService;

    @Autowired
    public App(UserService userService, EventService eventService, BookingService bookingService, AuditoriumService auditoriumService, DiscountService discountService) {
        this.userService = userService;
        this.eventService = eventService;
        this.bookingService = bookingService;
        this.auditoriumService = auditoriumService;
        this.discountService = discountService;
    }

    public static void main(String[] args) {
        ApplicationContext applicationContext
                = new AnnotationConfigApplicationContext(AppConfig.class, AuditoriumConfig.class, DiscountConfig.class);


    }
}
