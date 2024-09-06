package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.SpecificationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserService {

    @Autowired
    private SpecificationUserRepository specificationUserRepository;

    public List<String> getUsersByPetName(String petName) {
        List<User> users = specificationUserRepository.findUsersWithPetWithName(petName);
        return extractUserNames(users);
    }

    public List<String> getUsersWithMultipleToys() {
        List<User> users = specificationUserRepository.findUsersWithPetsHavingTwoOrMoreToys();
        return extractUserNames(users);
    }

    private List<String> extractUserNames(List<User> users) {
        return users.stream()
                .map(User::getName)
                .collect(Collectors.toList());
    }
}
