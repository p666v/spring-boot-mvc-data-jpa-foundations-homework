package ru.itsjava.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itsjava.domain.User;
import ru.itsjava.rest.dto.PetDto;
import ru.itsjava.rest.dto.UserDto;
import ru.itsjava.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/users")
    public String usersPage(Model model) {
        List<User> allUsers = userService.getAllUsers();
        List<UserDto> userDtos = new ArrayList<>();

        for (User user : allUsers) {
            userDtos.add(UserDto.toDto(user));
        }

        model.addAttribute("users", userDtos);
        return "users-page";
    }

    @GetMapping("users/{id}")
    public String getPage(@PathVariable("id") long id, Model model) {
        model.addAttribute("users", UserDto.toDto(userService.findById(id)));
        return "users-page";
    }

    @GetMapping("users/add")
    public String addPage() {
        return "add-users-page";
    }

    @PostMapping("users/add")
    public String afterAddPage(UserDto userDto) {
        userService.createUser(UserDto.fromDto(userDto));
        return "redirect:/";
    }
}
