package ru.itis.animerec.controller.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.itis.animerec.form.SignUpForm;
import ru.itis.animerec.service.UserService;

@Controller
@RequiredArgsConstructor
public class SignUpController {

    private final UserService userService;

    @GetMapping("/sign-up")
    public String getSignUpPage() {
        return "/security/sign_up";
    }

    @PostMapping("/sign-up")
    public String signUpUser(SignUpForm signUpForm,  RedirectAttributes redirectAttributes) {
        boolean isSuccess = userService.signUp(signUpForm);
        if (isSuccess) {
            return "redirect:/sign-in";
        } else {
            redirectAttributes.addFlashAttribute("error", "Имя пользователя уже занято или некорректная почта");
            return "redirect:/sign-up";
        }
    }
}