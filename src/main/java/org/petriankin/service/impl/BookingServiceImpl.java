package org.petriankin.service.impl;

import org.petriankin.domain.Auditorium;
import org.petriankin.domain.Event;
import org.petriankin.domain.Ticket;
import org.petriankin.domain.User;
import org.petriankin.repository.TicketRepository;
import org.petriankin.service.BookingService;
import org.petriankin.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class BookingServiceImpl implements BookingService {
    private final DiscountService discountService;
    private final TicketRepository ticketRepository;

    @Autowired
    public BookingServiceImpl(DiscountService discountService, TicketRepository ticketRepository) {
        this.discountService = discountService;
        this.ticketRepository = ticketRepository;
    }

    @Override
    public double getTicketsPrice(@Nonnull Event event, @Nonnull LocalDateTime dateTime, @Nullable User user, @Nonnull Set<Long> seats) {
        Auditorium auditoriumForGivenDateTime = event.getAuditoriums().get(dateTime);
        long totalAmountOfSeats = seats.size();
        long amountOfVIpSeats = auditoriumForGivenDateTime.countVipSeats(seats);
        long amountOfRegularSeats = totalAmountOfSeats - amountOfVIpSeats;

        double singleTicketPriceBasedOnEventRatio = calculatePriceBasedOnEventRating(event);

        double priceWithoutDiscount = singleTicketPriceBasedOnEventRatio * (2 * amountOfVIpSeats + amountOfRegularSeats);

        int discount = discountService.getDiscount(user, event, dateTime, seats.size());

        return priceWithoutDiscount * (1 - ((double) discount / 100));
    }

    @Override
    public void bookTickets(@Nonnull Set<Ticket> tickets) {
        for (Ticket ticket : tickets) {
            if (ticket.getUser() != null) {
                ticketRepository.save(ticket);
            } else {
                ticket.setUser(new User());
                ticketRepository.save(ticket);
            }
        }
    }

    @Nonnull
    @Override
    public Set<Ticket> getPurchasedTicketsForEvent(@Nonnull Event event, @Nonnull LocalDateTime dateTime) {
        Map<User, Set<Ticket>> userTicketMap = ticketRepository.getAll();
        Set<Ticket> purchasedTickets = new HashSet<>();
        for (Map.Entry<User, Set<Ticket>> pair : userTicketMap.entrySet()) {
            for (Ticket ticket : pair.getValue()) {
                if ((ticket.getEvent().equals(event)) && (ticket.getDateTime().equals(dateTime))) {
                    purchasedTickets.add(ticket);
                }
            }
        }
        return purchasedTickets;

    }

    private double calculatePriceBasedOnEventRating(Event event) {
        switch (event.getRating()) {
            case LOW:
                return (event.getBasePrice() * 0.8);
            case HIGH:
                return (event.getBasePrice() * 1.2);
            case MID:
                return event.getBasePrice();
            default:
                return event.getBasePrice();
        }
    }
}
