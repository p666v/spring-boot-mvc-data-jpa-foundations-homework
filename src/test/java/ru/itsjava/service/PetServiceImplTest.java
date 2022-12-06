package ru.itsjava.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.itsjava.domain.Pet;
import ru.itsjava.repository.PetRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@DataJpaTest
@DisplayName("Класс PetServiceImpl")
@Import(PetServiceImpl.class)
public class PetServiceImplTest {

    @Autowired
    private PetService petService;

    @Autowired
    private PetRepository petRepository;

    @DisplayName("Корректно добавляет питомца в БД")
    @Test
    public void shouldHaveCorrectCreatePet() {
        var expectedPet = new Pet(4L, "Snake");
        petService.createPet(expectedPet);
        var actualPet = petService.getPetById(4L).get();

        assertEquals(expectedPet, actualPet);
    }

    @DisplayName("Корректно возвращает список всех питомцев")
    @Test
    public void shouldHaveCorrectGetAllPets() {
        var expectedPets = petRepository.findAll();
        var actualPets = petService.getAllPets();

        assertEquals(expectedPets, actualPets);
    }

    @DisplayName("Корректно обновляет питомца в БД")
    @Test
    public void shouldHaveCorrectUpdatePet() {
        var expectedPet = petService.getPetById(1L).get();
        expectedPet.setBreed("Snake");
        petService.updatePet(expectedPet);
        var actualPet = petService.getPetById(1L).get();

        assertEquals(expectedPet, actualPet);
    }

    @DisplayName("Корректно удаляет питомца")
    @Test
    public void shouldHaveCorrectDeletePet() {
        Pet pet = petService.getPetById(1L).get();
        petService.deletePet(pet);
        var deletedPet = petService.getPetById(1L);

        assertFalse(deletedPet.isPresent());
    }

    @DisplayName("Корректно возвращает питомца по id")
    @Test
    public void shouldHaveCorrectGetPetById() {
        var expectedPet = petRepository.getReferenceById(1L);
        var actualPet = petService.getPetById(1L).get();

        assertEquals(expectedPet, actualPet);
    }
}
