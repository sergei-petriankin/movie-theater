package org.petriankin.service;

import org.petriankin.domain.User;
import org.petriankin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Nullable
    @Override
    public User getUserByEmail(@Nonnull String email) {
        return userRepository.getUserByEmail(email);
    }

    @Override
    public User save(@Nonnull User user) {
        return userRepository.save(user);
    }

    @Override
    public void remove(@Nonnull User user) {
        userRepository.save(user);
    }

    @Override
    public User getById(@Nonnull Long id) {
        return userRepository.getById(id);
    }

    @Nonnull
    @Override
    public Collection<User> getAll() {
        return (Collection<User>) userRepository.getAll();
    }
}
