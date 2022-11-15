package ru.itsjava.service;

import ru.itsjava.domain.User;

import java.util.List;

public interface UserService {
    User findById(long id);

    List<User> getAllUsers();

    void createUser(User user);

    void printUser(String name);

    void changeUser(String oldName, String updateName);

    void deleteUserById(long id);

//    void printAllUsers();

}
