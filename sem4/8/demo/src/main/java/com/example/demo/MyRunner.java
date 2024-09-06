package com.example.demo;

import com.example.demo.services.PetService;
import com.example.demo.services.ToyService;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyRunner implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Autowired
    private ToyService toyService;

    @Autowired
    private PetService petService;



    @Override
    public void run(String... args) throws Exception {
        userService.printPeopleWithToys();
        toyService.printToysWithMinTwoAnimals();
        petService.printPetsWithUserNameLengthEqualTe();



    }
}
