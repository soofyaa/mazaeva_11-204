package ru.itis.demo.service;

import org.springframework.stereotype.Service;
import ru.itis.demo.entity.User;
import ru.itis.demo.form.SignUpForm;

import java.util.List;

public interface UserService {
    void signUp(SignUpForm form);
    List<User> getAllUsers();
    void banAllUsers();
}
