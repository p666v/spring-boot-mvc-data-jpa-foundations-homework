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

    @Transactional(readOnly = true)
    @Override
    public User findById(long id) {
        return userRepository.findById(id).get();
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    @Override
    public void createUser(User user) {
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    @Override
    public void printUser(String name) {
        User user = userRepository.findByName(name).get();
        System.out.println(user);
    }

    @Transactional
    @Override
    public void changeUser(String oldName, String updateName) {
        User user = userRepository.findByName(oldName).get();
        user.setName(updateName);
        userRepository.save(user);
        System.out.println("Successfully saved!");
    }

    @Transactional
    @Override
    public void deleteUserById(long id) {
        userRepository.deleteById(id);
    }

//    @Transactional(readOnly = true)
//    @Override
//    public void printAllUsers() {
//        System.out.println("Участники клуба любителей домашних животных:");
//        List<User> userList = userRepository.findAll();
//        for (User users : userList) {
//            System.out.println(users);
//        }
//    }


}
