package ru.itsjava.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itsjava.domain.Pet;
import ru.itsjava.repository.PetRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PetServiceImpl implements PetService {
    private final PetRepository petRepository;

    @Transactional(readOnly = true)
    @Override
    public Pet findById(long id) {
        return petRepository.findById(id).get();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    @Transactional
    @Override
    public void createPet(Pet pet) {
        petRepository.save(pet);
    }

    @Transactional(readOnly = true)
    @Override
    public void printPet(String breed) {
        Pet pet = petRepository.getByBreed(breed).get();
        System.out.println(pet);
    }

    @Transactional
    @Override
    public void changePet(String oldBreed, String updateBreed) {
        Pet pet = petRepository.getByBreed(oldBreed).get();
        pet.setBreed(updateBreed);
        petRepository.save(pet);
        System.out.println("Successfully saved!");
    }

    @Transactional
    @Override
    public void deletePetById(long id) {
        petRepository.deleteById(id);
    }
}
