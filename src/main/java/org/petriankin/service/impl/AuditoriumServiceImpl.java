package org.petriankin.service.impl;

import org.petriankin.domain.Auditorium;
import org.petriankin.service.AuditoriumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Set;

@Service
public class AuditoriumServiceImpl implements AuditoriumService {
    private final Set<Auditorium> auditoriumSet;

    @Autowired
    public AuditoriumServiceImpl(Set<Auditorium> auditoriumSet) {
        this.auditoriumSet = auditoriumSet;
    }



    @Nonnull
    @Override
    public Set<Auditorium> getAll() {
        return this.auditoriumSet;
    }

    @Nullable
    @Override
    public Auditorium getByName(@Nonnull String name) {
        Auditorium auditorium = null;
        for (Auditorium aud : auditoriumSet) {
            String audName = aud.getName();
            if (audName.equals(name)) {
                auditorium = aud;
            }
        }
        return auditorium;
    }
}
