package org.petriankin.repository;

import org.petriankin.domain.Event;
import org.springframework.stereotype.Repository;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;

@Repository
public interface EventRepository {
    @Nullable
    Event getByName(@Nonnull String name);

    Event save(@Nonnull Event object);

    void remove(@Nonnull Event object);

    Event getById(@Nonnull Long id);

    @Nonnull
    Map<Long, Event> getAll();

    /*
     * Finding all events that air on specified date range
     *
     * @param from Start date
     *
     * @param to End date inclusive
     *
     * @return Set of events
     */
    // public @Nonnull Set<Event> getForDateRange(@Nonnull LocalDate from,
    // @Nonnull LocalDate to);

    /*
     * Return events from 'now' till the the specified date time
     *
     * @param to End date time inclusive
     * s
     * @return Set of events
     */
    // public @Nonnull Set<Event> getNextEvents(@Nonnull LocalDateTime to);
}
