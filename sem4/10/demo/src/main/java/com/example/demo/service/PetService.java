package com.example.demo.service;

import com.example.demo.repository.EntityManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PetService {

    @Autowired
    private EntityManagerRepository entityManagerRepository;

    public List<String> getPetNamesWithPlasticToy() {
        return entityManagerRepository.getPetNamesWithPlasticToy();
    }
}
