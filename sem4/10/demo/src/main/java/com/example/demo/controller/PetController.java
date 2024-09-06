package com.example.demo.controller;

import com.example.demo.repository.EntityManagerRepository;
import com.example.demo.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class PetController {

    @Autowired
    private PetService petService;

    @Autowired
    public PetController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping("/petsWithPlasticToys")
    public ResponseEntity<List<String>> getPetNamesWithPlasticToy() {
        List<String> petNames = petService.getPetNamesWithPlasticToy();
        return ResponseEntity.ok(petNames);
    }
}
