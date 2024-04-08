package ru.itis.homework5.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.homework5.services.MyService;

@Controller
@RequiredArgsConstructor
public class MyController {

    @Autowired
    private MyService myService;

    @GetMapping("/reverse")
    public String index() {
        return "hello";
    }

    @PostMapping("/reverse")
    public String reverseString(@RequestParam("inputString") String inputString, Model model) {
        String reversedString = myService.reverse(inputString);
        model.addAttribute("inputString", inputString);
        model.addAttribute("reversedString", reversedString);
        return "answer";
    }
}
