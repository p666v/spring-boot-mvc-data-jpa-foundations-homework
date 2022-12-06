package ru.itsjava.rest.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.itsjava.domain.Pet;
import ru.itsjava.domain.User;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@DisplayName("Класс PetDto")
public class UserDtoTest {
    public static final Pet pet = new Pet(0, "Dog");
    public static final User user = new User(1, "Pavel", 41, pet);
    public static final UserDto userDto = new UserDto("1", "Pavel", "41", "Dog");

    @DisplayName("Корректно преобразует в User из DTO")
    @Test
    public void shouldHaveCorrectFromDto() {
        User userFromDto = UserDto.fromDto(userDto);
        assertEquals(user, userFromDto);
    }

    @DisplayName("Корректно преобразует из DTO в User")
    @Test
    public void shouldHaveCorrectToDto() {
        UserDto userToDto = UserDto.toDto(user);
        assertEquals(userDto, userToDto);
    }
}
