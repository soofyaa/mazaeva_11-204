package ru.itis.animerec.controller.user;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.itis.animerec.dto.UserDTO;
import ru.itis.animerec.dto.WatchlistDTO;
import ru.itis.animerec.entity.UserEntity;
import ru.itis.animerec.security.UserDetailsImpl;
import ru.itis.animerec.service.UserService;
import ru.itis.animerec.service.WatchlistService;

import java.util.List;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final WatchlistService watchlistService;

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "Найти профиль пользователя", description = "Этот эндпоинт перенаправляет на страницу профиля текущего пользователя.")
    public String findUserProfile() {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserEntity user = userDetails.getUser();
        return "redirect:/user/" + user.getId();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить профиль пользователя", description = "Этот эндпоинт возвращает профиль пользователя по его идентификатору.")
    public String getUserProfile(@PathVariable Long id, Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserDTO userDto = userService.getUserById(id);
        List<WatchlistDTO> watchlists = watchlistService.findListsByUserId(id);

        boolean isAuthenticated = authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof UserDetails;
        boolean isCurrentUser = false;
        boolean isAdmin = false;

        if (isAuthenticated) {
            isCurrentUser = userService.isCurrentUser(id);
            isAdmin = userService.isAdmin();
        }

        model.addAttribute("isCurrentUser", isCurrentUser);
        model.addAttribute("isAuthenticated", isAuthenticated);
        model.addAttribute("isAdmin", isAdmin);
        model.addAttribute("user", userDto);
        model.addAttribute("watchlists", watchlists);

        return "/user/user-profile";
    }

    @GetMapping("/settings")
    @Operation(summary = "Получить настройки пользователя", description = "Этот эндпоинт возвращает страницу настроек текущего пользователя.")
    public String getUserSettings(Model model) {
        UserDTO userDTO = userService.getCurrentUserDto();
        model.addAttribute("user", userDTO);
        return "user/user-settings";
    }

    @PostMapping("/settings/updateName")
    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "Обновить имя пользователя", description = "Этот эндпоинт обновляет имя текущего пользователя.")
    public String updateUserName(@RequestParam String newName, RedirectAttributes redirectAttributes) {
        boolean isUpdated = userService.updateUserName(newName);
        if (!isUpdated) {
            redirectAttributes.addFlashAttribute("error", "Имя пользователя уже занято");
        }
        return "redirect:/user/settings";
    }

    @PostMapping("/settings/updateAvatar")
    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "Обновить аватар пользователя", description = "Этот эндпоинт обновляет аватар текущего пользователя.")
    public String updateUserAvatar(@RequestParam("avatar") MultipartFile avatarFile) {
        userService.updateUserAvatar(avatarFile);
        return "redirect:/user/settings";
    }
}
