package ru.itsjava.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itsjava.domain.User;
import ru.itsjava.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public void create(User user) {
        userRepository.save(user);
        System.out.println("В базу данных добавлен новый участник");
    }

    @Transactional(readOnly = true)
    @Override
    public void printAllUsers() {
        System.out.println("Участники клуба любителей домашних животных:");
        List<User> userList = userRepository.findAll();
        for (User users : userList) {
            System.out.println(users);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
