package ru.itsjava.service;

import ru.itsjava.domain.Pet;

import java.util.List;

public interface PetService {
    Pet findById(long id);

    List<Pet> getAllPets();

    void createPet(Pet pet);

    void printPet(String name);

    void changePet(String oldName, String updateName);

    void deletePetById(long id);

}
