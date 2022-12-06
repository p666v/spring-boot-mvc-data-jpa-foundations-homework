package ru.itsjava.service;

import ru.itsjava.domain.Pet;

import java.util.List;
import java.util.Optional;

public interface PetService {

    void createPet(Pet pet);

    List<Pet> getAllPets();

    void updatePet(Pet pet);

    void deletePet(Pet pet);

    Optional<Pet> getPetById(long id);
}
