package org.example.demo.controllers;

import lombok.RequiredArgsConstructor;
import org.example.demo.dto.UserDTO;
import org.example.demo.entity.User;
import org.example.demo.services.CreateService;
import org.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class MyController {

    @Autowired
    private final CreateService createService;

    @Autowired
    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<String> createEntities() {
        createService.createEntities();
        return ResponseEntity.status(HttpStatus.CREATED).body("Entities created successfully");
    }

    @GetMapping("/create")
    public String answerEntities() {
        return "create";
    }

    @GetMapping("/get/{userId}")
    public ResponseEntity<String> getUserWithPetsAndToys(@PathVariable Long userId) {
        User user = userService.getUser(userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(new UserDTO(user).toString());
    }

    @GetMapping("/update/{userId}")
    public ResponseEntity<String> updateUserParameter(
            @PathVariable Long userId,
            @RequestParam String parameterName,
            @RequestParam Object parameterValue
    ) {

        userService.updateUserParameter(userId, parameterName, parameterValue);
        return ResponseEntity.ok("User parameter updated successfully");
    }
}
