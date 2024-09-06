package com.example.demo.services;

import com.example.demo.dao.PetDAO;
import com.example.demo.dao.ToyDAO;
import com.example.demo.models.Pet;
import com.example.demo.models.Toy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {
    @Autowired
    private PetDAO petDAO;

    public void printPetsWithUserNameLengthEqualTe() {
        List<Pet> pets = petDAO.getPetsWithUserNameLengthEqualTen();
        pets.forEach(pet -> System.out.println(pet.toString()));
    }
}
