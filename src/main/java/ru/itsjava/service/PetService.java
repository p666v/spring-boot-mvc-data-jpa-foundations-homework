package ru.itsjava.service;

import ru.itsjava.domain.Pet;

import java.util.List;

public interface PetService {

    void createPet(Pet pet);

    List<Pet> getAllPets();

    void updatePet(Pet pet);

    void deletePet(Pet pet);

    Pet getPetById(long id);
}
