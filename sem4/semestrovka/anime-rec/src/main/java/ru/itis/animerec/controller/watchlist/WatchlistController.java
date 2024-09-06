package ru.itis.animerec.controller.watchlist;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.animerec.dto.CommentDTO;
import ru.itis.animerec.dto.UserDTO;
import ru.itis.animerec.dto.WatchlistDTO;
import ru.itis.animerec.entity.UserEntity;
import ru.itis.animerec.security.UserDetailsImpl;
import ru.itis.animerec.service.CommentWatchlistService;
import ru.itis.animerec.service.UserService;
import ru.itis.animerec.service.WatchlistService;
import org.springframework.ui.Model;

import java.util.List;

@Controller
@RequestMapping("/watchlist")
@RequiredArgsConstructor
public class WatchlistController {

    private final WatchlistService watchlistService;
    private final CommentWatchlistService commentWatchlistService;
    private final UserService userService;

    @GetMapping("/create")
    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "Показать форму создания списка аниме", description = "Этот эндпоинт отображает форму для создания нового списка аниме.")
    public String showCreateForm() {
        return "/watchlist/watchlist-create";
    }

    @PostMapping("/create")
    @Operation(summary = "Создать список аниме", description = "Этот эндпоинт создает новый список аниме.")
    public String createWatchlist(@ModelAttribute WatchlistDTO watchlistDTO,
                                  @RequestParam("poster") MultipartFile poster) {

        Long watchlistId = watchlistService.createWatchlist(watchlistDTO, poster);
        return "redirect:/watchlist/" + watchlistId;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить список аниме", description = "Этот эндпоинт возвращает информацию о списке аниме по его идентификатору.")
    public String getWatchlist(@PathVariable Long id, Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        boolean isAuthenticated = authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof UserDetails;
        boolean isAdmin = false;

        if (isAuthenticated) {
            isAdmin = userService.isAdmin();
        }

        WatchlistDTO watchlist = watchlistService.findById(id);
        List<CommentDTO> comments = commentWatchlistService.getCommentsByWatchlistId(id);
        String username = userService.getNameById(watchlist.getUserId());

        model.addAttribute("username", username);
        model.addAttribute("watchlist", watchlist);
        model.addAttribute("isAuthenticated", isAuthenticated);
        model.addAttribute("isAdmin", isAdmin);
        model.addAttribute("comments", comments);

        return "/watchlist/watchlist-details";
    }
}

