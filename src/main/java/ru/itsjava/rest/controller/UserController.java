package ru.itsjava.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itsjava.domain.Pet;
import ru.itsjava.domain.User;
import ru.itsjava.rest.dto.PetDto;
import ru.itsjava.rest.dto.UserDto;
import ru.itsjava.service.PetService;
import ru.itsjava.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final PetService petService;

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
        Optional<User> userById = userService.getUserById(id);
        userById.ifPresent(user -> model.addAttribute("users", UserDto.toDto(user)));
        return "users-page";
    }

    @GetMapping("users/add")
    public String addPage(Model model) {
        List<Pet> allPets = petService.getAllPets();
        List<PetDto> petDtos = new ArrayList<>();

        for (Pet pet : allPets) {
            petDtos.add(PetDto.toDto(pet));
        }

        model.addAttribute("pets", petDtos);
        return "add-users-page";
    }

    @PostMapping("users/add")
    public String afterAddPage(UserDto userDto) {
        userService.createUser(UserDto.fromDto(userDto));
        return "redirect:/users";
    }

    @GetMapping("users/{id}/delete")
    public String deletePage(@PathVariable("id") long id, Model model) {
        Optional<User> userById = userService.getUserById(id);
        userById.ifPresent(user -> model.addAttribute("userDto", UserDto.toDto(user)));
        return "delete-users-page";
    }

    @PostMapping("users/{id}/delete")
    public String afterDeletePage(UserDto userDto) {
        userService.deleteUser(UserDto.fromDto(userDto));
        return "redirect:/users";
    }

    @GetMapping("users/{id}/edit")
    public String editPage(@PathVariable("id") long id, Model model) {
        Optional<User> userById = userService.getUserById(id);
        userById.ifPresent(user -> model.addAttribute("userDto", UserDto.toDto(user)));
        return "edit-users-page";
    }

    @PostMapping("users/{id}/edit")
    public String afterEditPage(UserDto userDto) {
        userService.updateUser(UserDto.fromDto(userDto));
        return "redirect:/users";
    }
}
