package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<String>> getUsersByPetName(@RequestParam String petName) {
        List<String> userNames = userService.getUsersByPetName(petName);

        if (userNames.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userNames);
    }


    @GetMapping("/usersWithMultipleToys")
    public ResponseEntity<List<String>> getUsersWithMultipleToys() {
        List<String> users = userService.getUsersWithMultipleToys();

        if (users.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(users);
    }


}
