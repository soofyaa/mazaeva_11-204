package ru.itis.animerec.controller.error;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ErrorController {

    @GetMapping("/error")
    public String handleError(@RequestParam(value = "message", required = false) String message, Model model) {
        if (message == null) {
            message = "Какая-то проблема. Пожалуйста, попробуйте позже.";
        }
        model.addAttribute("message", message);
        return "/error/error";
    }
}