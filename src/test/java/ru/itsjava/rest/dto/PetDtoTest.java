package ru.itsjava.rest.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.itsjava.domain.Pet;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@DisplayName("Класс PetDto")
public class PetDtoTest {
    public static final Pet pet = new Pet(1, "Dog");
    public static final PetDto petDto = new PetDto("1", "Dog");

    @DisplayName("Корректно преобразует в Pet из DTO")
    @Test
    public void shouldHaveCorrectFromDto() {
        Pet petFromDto = PetDto.fromDto(petDto);
        assertEquals(pet, petFromDto);
    }

    @DisplayName("Корректно преобразует из DTO в Pet")
    @Test
    public void shouldHaveCorrectToDto() {
        PetDto petToDto = PetDto.toDto(pet);
        assertEquals(petDto, petToDto);
    }
}
