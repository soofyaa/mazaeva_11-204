package ru.itis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class PetController {

    @Autowired
    private PetService petService;

    @GetMapping("/createPet")
    public String showCreatePetForm(Model model) {
        model.addAttribute("pet", new Pet());
        return "create-pet";
    }

    @PostMapping("/createPet")
    public String addPet(@ModelAttribute("pet") Pet pet) {
        petService.addPet(pet);
        return "redirect:/petsList";
    }

    @GetMapping("/petsList")
    public String showPetsList(Model model) {
        List<Pet> pets = petService.getAllPets();
        model.addAttribute("pets", pets);
        return "pets-list";
    }
}
