package ru.itsjava.service;

import ru.itsjava.domain.User;

import java.util.List;

public interface UserService {

    void createUser(User user);

    List<User> getAllUsers();

    void updateUser(User user);

    void deleteUser(User user);

    User getUserById(long id);

}
