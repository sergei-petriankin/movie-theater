package org.petriankin.repository;

import org.petriankin.domain.Ticket;
import org.petriankin.domain.User;
import org.springframework.stereotype.Repository;

import javax.annotation.Nonnull;
import java.util.Map;
import java.util.Set;

@Repository
public interface TicketRepository {
    Ticket save(@Nonnull Ticket ticket);

    void remove(@Nonnull Ticket ticket);

    Set<Ticket> getByUser(@Nonnull User user);

    @Nonnull
    Map<User, Set<Ticket>> getAll();
}
