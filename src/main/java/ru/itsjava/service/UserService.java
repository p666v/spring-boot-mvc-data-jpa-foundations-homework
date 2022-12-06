package ru.itsjava.service;

import ru.itsjava.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void createUser(User user);

    List<User> getAllUsers();

    void updateUser(User user);

    void deleteUser(User user);

    Optional<User> getUserById(long id);
}
