package ru.itis.demo.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomePageController {

    @GetMapping("/hello")
    @ResponseBody
    public String getHello() {
        return "Hello";
    }
}
