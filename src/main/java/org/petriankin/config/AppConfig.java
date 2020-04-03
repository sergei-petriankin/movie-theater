package org.petriankin.config;

import org.petriankin.repository.*;
import org.petriankin.repository.impl.EventRepositoryImpl;
import org.petriankin.repository.impl.TicketRepositoryImpl;
import org.petriankin.repository.impl.UserRepositoryImpl;
import org.petriankin.service.*;
import org.petriankin.service.impl.BookingServiceImpl;
import org.petriankin.service.impl.EventServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;

@Configuration
@EnableAspectJAutoProxy
@Import({DiscountConfig.class, AuditoriumConfig.class, DBConfig.class})
@ComponentScan({"org.petriankin"})
public class AppConfig {
}
