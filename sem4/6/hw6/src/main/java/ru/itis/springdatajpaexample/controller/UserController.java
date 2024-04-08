package ru.itis.springdatajpaexample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.Collectors;

import ru.itis.springdatajpaexample.dto.UserDto;
import ru.itis.springdatajpaexample.repository.UserRepository;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users/findByPetNameAndToyCount")
    public List<UserDto> getUsersWithPetNameAndToysCount(@RequestParam(name = "name") String name, @RequestParam(name = "toyCount") Integer toyCount) {
        return userRepository.findByPetsNameAndPetsToysCount(name, toyCount).stream()
                .map(UserDto::fromEntity)
                .collect(Collectors.toList());
    }

    @GetMapping("/users/findByPetNameAndPetAgeGreaterThan")
    public List<UserDto> getUsersWithPetNameAndPetAgeGreaterThan(@RequestParam(name = "name") String name, @RequestParam(name = "ageGreater") Integer ageGreater) {
        return userRepository.findByPetsNameAndPetsAgeGreaterThan(name, ageGreater).stream()
                .map(UserDto::fromEntity)
                .collect(Collectors.toList());
    }
}