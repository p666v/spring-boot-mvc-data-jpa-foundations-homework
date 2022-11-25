package ru.itsjava.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itsjava.domain.Pet;
import ru.itsjava.rest.dto.PetDto;
import ru.itsjava.service.PetService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class PetController {
    private final PetService petService;

    @GetMapping("/pets")
    public String petsPage(Model model) {
        List<Pet> allPets = petService.getAllPets();
        List<PetDto> petDtos = new ArrayList<>();

        for (Pet pet : allPets) {
            petDtos.add(PetDto.toDto(pet));
        }

        model.addAttribute("pets", petDtos);
        return "pets-page";
    }

    @GetMapping("pets/{id}")
    public String getPage(@PathVariable("id") long id, Model model) {
        model.addAttribute("pets", PetDto.toDto(petService.getPetById(id)));
        return "pets-page";
    }

    @GetMapping("pets/add")
    public String addPage() {
        return "add-pets-page";
    }

    @PostMapping("pets/add")
    public String afterAddPage(PetDto petDto) {
        petService.createPet(PetDto.fromDto(petDto));
        return "redirect:/pets";
    }

    @GetMapping("pets/{id}/delete")
    public String deletePage(@PathVariable("id") long id, Model model) {
        Pet petById = petService.getPetById(id);
        model.addAttribute("petDto", PetDto.toDto(petById));
        return "delete-pets-page";
    }

    @PostMapping("pets/{id}/delete")
    public String afterDeletePage(PetDto petDto) {
        petService.deletePet(PetDto.fromDto(petDto));
        return "redirect:/pets";
    }

    @GetMapping("pets/{id}/edit")
    public String editPage(@PathVariable("id") long id, Model model) {
        Pet petById = petService.getPetById(id);
        model.addAttribute("petDto", PetDto.toDto(petById));
        return "edit-pets-page";
    }

    @PostMapping("pets/{id}/edit")
    public String afterEditPage(PetDto petDto) {
        petService.updatePet(PetDto.fromDto(petDto));
        return "redirect:/pets";
    }
}
