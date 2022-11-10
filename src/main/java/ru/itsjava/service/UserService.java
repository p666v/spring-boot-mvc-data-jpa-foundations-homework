package ru.itsjava.service;

import ru.itsjava.domain.User;

import java.util.List;

public interface UserService {
    void create(User user);

    void printAllUsers();

    List<User> getAllUsers();
}
