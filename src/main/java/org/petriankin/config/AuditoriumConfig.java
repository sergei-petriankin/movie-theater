package org.petriankin.config;

import org.petriankin.domain.Auditorium;
import org.petriankin.service.AuditoriumService;
import org.petriankin.service.impl.AuditoriumServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Configuration
public class AuditoriumConfig {
    @Bean
    public Auditorium bigAuditorium(){
        Auditorium auditorium = new Auditorium();
        Properties properties = new Properties();
        try (InputStream inputStream = new FileInputStream("C:\\Users\\Sergei_Petriankin\\IdeaProjects\\movie-theater\\src\\main\\resources\\auditorium.properties")) {
            properties.load(inputStream);
            auditorium.setName(properties.getProperty("big.name"));
            auditorium.setNumberOfSeats(Long.parseLong(properties.getProperty("big.seats")));
            Set<Long> vipSeats = new HashSet<>();
            String[] vipSeatsString = properties.getProperty("big.vip-seats").split(",");
            for (String vipSeat: vipSeatsString) {
                vipSeats.add(Long.parseLong(vipSeat));
            }
            auditorium.setVipSeats(vipSeats);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return auditorium;
    }
    @Bean
    public Auditorium mediumAuditorium(){
        Auditorium auditorium = new Auditorium();
        Properties properties = new Properties();
        try (InputStream inputStream = new FileInputStream("C:\\Users\\Sergei_Petriankin\\IdeaProjects\\movie-theater\\src\\main\\resources\\auditorium.properties")) {
            properties.load(inputStream);
            auditorium.setName(properties.getProperty("medium.name"));
            auditorium.setNumberOfSeats(Long.parseLong(properties.getProperty("medium.seats")));
            Set<Long> vipSeats = new HashSet<>();
            String[] vipSeatsString = properties.getProperty("medium.vip-seats").split(",");
            for (String vipSeat: vipSeatsString) {
                vipSeats.add(Long.parseLong(vipSeat));
            }
            auditorium.setVipSeats(vipSeats);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return auditorium;
    }
    @Bean
    public Auditorium smallAuditorium(){
        Auditorium auditorium = new Auditorium();
        Properties properties = new Properties();
        try (InputStream inputStream = new FileInputStream("C:\\Users\\Sergei_Petriankin\\IdeaProjects\\movie-theater\\src\\main\\resources\\auditorium.properties")) {
            properties.load(inputStream);
            auditorium.setName(properties.getProperty("small.name"));
            auditorium.setNumberOfSeats(Long.parseLong(properties.getProperty("small.seats")));
            Set<Long> vipSeats = new HashSet<>();
            String[] vipSeatsString = properties.getProperty("small.vip-seats").split(",");
            for (String vipSeat: vipSeatsString) {
                vipSeats.add(Long.parseLong(vipSeat));
            }
            auditorium.setVipSeats(vipSeats);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return auditorium;
    }

    @Bean
    public AuditoriumService auditoriumService() {
        Map<String, Auditorium> auditoriums = new HashMap<>();
        auditoriums.put(bigAuditorium().getName(), bigAuditorium());
        auditoriums.put(mediumAuditorium().getName(), mediumAuditorium());
        auditoriums.put(smallAuditorium().getName(), smallAuditorium());

        return new AuditoriumServiceImpl(auditoriums);
    }
}
