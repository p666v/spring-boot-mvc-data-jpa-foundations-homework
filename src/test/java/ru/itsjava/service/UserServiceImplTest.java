package ru.itsjava.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.itsjava.domain.Pet;
import ru.itsjava.domain.User;
import ru.itsjava.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@DataJpaTest
@DisplayName("Класс UserServiceImpl")
@Import(UserServiceImpl.class)
public class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @DisplayName("Корректно добавляет пользователя в БД")
    @Test
    public void shouldHaveCorrectCreatePet() {
        Pet pet = new Pet(1, "Rabbit");
        var expectedUser = new User(4L, "Sasha", 9, pet);
        userService.createUser(expectedUser);
        var actualUser = userService.getUserById(4L).get();

        assertEquals(expectedUser, actualUser);
    }

    @DisplayName("Корректно возвращает список всех пользователей")
    @Test
    public void shouldHaveCorrectGetAllPets() {
        var expectedUsers = userRepository.findAll();
        var actualUsers = userService.getAllUsers();

        assertEquals(expectedUsers, actualUsers);
    }

    @DisplayName("Корректно обновляет пользователя в БД")
    @Test
    public void shouldHaveCorrectUpdatePet() {
        var expectedUser = userService.getUserById(1L).get();
        expectedUser.setAge(500);
        userService.updateUser(expectedUser);
        var actualPet = userService.getUserById(1L).get();

        assertEquals(expectedUser, actualPet);
    }

    @DisplayName("Корректно удаляет пользователя")
    @Test
    public void shouldHaveCorrectDeletePet() {
        User user = userService.getUserById(1L).get();
        userService.deleteUser(user);
        var deletedUser = userService.getUserById(1L);

        assertFalse(deletedUser.isPresent());
    }

    @DisplayName("Корректно возвращает пользователя по id")
    @Test
    public void shouldHaveCorrectGetPetById() {
        var expectedUser = userRepository.getReferenceById(1L);
        var actualUser = userService.getUserById(1L).get();

        assertEquals(expectedUser, actualUser);
    }
}
