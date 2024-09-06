package ru.itis.demo.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.demo.entity.User;
import ru.itis.demo.form.SignUpForm;
import ru.itis.demo.repository.UserRepository;
import ru.itis.demo.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void signUp(SignUpForm form) {
        User user = User.builder()
                .passwordHash(passwordEncoder.encode(form.getPassword()))
                .email(form.getEmail())
                .firstName(form.getFirstName())
                .lastName(form.getLastName())
                .role(User.Role.USER)
                .state(User.State.ACTIVE)
                .build();

        userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public void banAllUsers() {
        userRepository.updateAllNonAdminUsersToBanned(User.State.BANNED, User.Role.ADMIN);
    }
}
