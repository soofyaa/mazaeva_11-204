package ru.itis.demo.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.demo.entity.User;
import ru.itis.demo.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/get-all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String getAllUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "/user/users";
    }

    @PostMapping("/ban-all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> banAllUsers() {
        userService.banAllUsers();
        return ResponseEntity.ok("All non-admin users have been banned.");
    }

    @GetMapping("/ban-all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String showBanButton() {
        return "/admin/ban_button";
    }


}
