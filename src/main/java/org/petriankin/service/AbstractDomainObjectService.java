package org.petriankin.service;

import org.petriankin.domain.DomainObject;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import java.util.Collection;

@Service
public interface AbstractDomainObjectService<T extends DomainObject> {

    T save(@Nonnull T object);

    void remove(@Nonnull T object);

    T getById(@Nonnull Long id);

    @Nonnull
    Collection<T> getAll();
}
