package ru.itis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FactController {

    @Qualifier("Cat")
    @Autowired
    private FactResolver factResolverCat;

    @Qualifier("Norris")
    @Autowired
    private FactResolver factResolverNorris;


    @GetMapping("/fact")
    public String getFact(@RequestParam("type") String type, ModelMap model) {
        String message;
        if ("Norris".equals(type)) {
            message = factResolverNorris.getFact();
        } else if ("Cat".equals(type)) {
            message = factResolverCat.getFact();
        } else {
            message = "Invalid type. Please use 'Norris' or 'Cat'.";
        }

        model.addAttribute("message", message);
        return "fact";
    }
}
