package org.petriankin.service;

import org.petriankin.domain.Auditorium;

public interface AuditoriumService {
    Auditorium getByName(String name);
}
