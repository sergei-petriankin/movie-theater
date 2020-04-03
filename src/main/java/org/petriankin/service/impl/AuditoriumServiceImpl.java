package org.petriankin.service.impl;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.petriankin.domain.Auditorium;
import org.petriankin.service.AuditoriumService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuditoriumServiceImpl implements AuditoriumService {
    @Getter
    private final Map<String, Auditorium> auditoriumSet;

    @Override
    public Auditorium getByName(String name) {
        return auditoriumSet.get(name);
    }
}
