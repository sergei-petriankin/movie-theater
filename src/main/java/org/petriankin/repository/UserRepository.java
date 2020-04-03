package org.petriankin.repository;

import org.petriankin.domain.User;
import org.springframework.stereotype.Repository;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;

@Repository
public interface UserRepository {

    User save(@Nonnull User object);

    void remove(@Nonnull User object);

    User getById(@Nonnull Long id);

    @Nonnull
    Map<Long, User> getAll();

    @Nullable
    User getUserByEmail(@Nonnull String email);


}
