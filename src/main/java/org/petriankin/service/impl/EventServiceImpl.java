package org.petriankin.service.impl;

import org.petriankin.domain.Event;
import org.petriankin.repository.EventRepository;
import org.petriankin.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Nullable
    @Override
    public Event getByName(@Nonnull String name) {
        return eventRepository.getByName(name);
    }

    @Override
    public Event save(@Nonnull Event event) {
        return eventRepository.save(event);
    }

    @Override
    public void remove(@Nonnull Event event) {
        eventRepository.remove(event);
    }

    @Override
    public Event getById(@Nonnull Long id) {
        return eventRepository.getById(id);
    }

    @Nonnull
    @Override
    public Collection<Event> getAll() {
        return (Collection<Event>) eventRepository.getAll();
    }
}
