package ru.itis.demo.controller.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.demo.form.SignUpForm;
import ru.itis.demo.service.UserService;

@Controller
public class SignUpController {

    @Autowired
    private UserService userService;

    @GetMapping("/sign-up")
    public String getSignUpPage() {
        return "/security/sign_up";
    }

    @PostMapping("/sign-up")
    public String signUpUser(SignUpForm signUpForm) {
        userService.signUp(signUpForm);
        return "redirect:/sign-in";
    }
}
