package ru.itis.animerec.controller.anime;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.animerec.dto.AnimeDTO;
import ru.itis.animerec.dto.CommentDTO;
import ru.itis.animerec.dto.UserDTO;
import ru.itis.animerec.dto.WatchlistDTO;
import ru.itis.animerec.service.AnimeService;
import ru.itis.animerec.service.UserService;
import ru.itis.animerec.service.WatchlistService;
import ru.itis.animerec.service.impl.CommentAnimeServiceImpl;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/anime")
@RequiredArgsConstructor
@Slf4j
public class AnimeController {

    private final AnimeService animeService;
    private final WatchlistService watchlistService;
    private final CommentAnimeServiceImpl commentAnimeService;
    private final UserService userService;

    @GetMapping
    @Operation(summary = "Показать новые аниме", description = "Этот эндпоинт отображает список топ-100 новых аниме.")
    public String showNewAnime(Model model) {
        List<AnimeDTO> animes = animeService.getTopNew100Animes();
        model.addAttribute("animes", animes);
        return "/anime/anime-all";
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить аниме по ID", description = "Этот эндпоинт получает информацию о конкретном аниме по его идентификатору.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешно найдено аниме"),
            @ApiResponse(responseCode = "404", description = "Аниме с указанным ID не найдено")
    })
    public String getAnimeById(@PathVariable Long id, Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<WatchlistDTO> userListsNames = new ArrayList<>();

        boolean isAuthenticated = authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof UserDetails;
        log.info("auth: {}", isAuthenticated);
        boolean isAdmin = false;

        if (isAuthenticated) {
            isAdmin = userService.isAdmin();
            userListsNames = watchlistService.findListsNamesCurrentUser();
        }

        AnimeDTO animeDTO = animeService.getAnimeDetails(id, isAuthenticated);
        List<CommentDTO> comments = commentAnimeService.getCommentsByAnimeId(id);

        model.addAttribute("anime", animeDTO);
        model.addAttribute("userLists", userListsNames);
        model.addAttribute("isAuthenticated", isAuthenticated);
        model.addAttribute("isAdmin", isAdmin);
        model.addAttribute("comments", comments);

        return "anime/anime-details";
    }

    @GetMapping("/rec")
    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "Показать рекомендованные аниме", description = "Этот эндпоинт отображает список рекомендованных аниме для текущего пользователя.")
    public String showRecAnime(Model model) {

        List<AnimeDTO> allAnimes = animeService.getAllAnimes();
        List<AnimeDTO> recommendedAnimes = animeService.getRecommendedAnimes(allAnimes);

        model.addAttribute("animes", recommendedAnimes);

        return "/anime/anime-all";
    }

    @GetMapping("/popular")
    @Operation(summary = "Показать популярные аниме", description = "Этот эндпоинт отображает список топ-100 наиболее комментируемых аниме.")
    public String showPopularAnime(Model model) {
        List<AnimeDTO> animes = animeService.getTopCommentedAnimes();
        model.addAttribute("animes", animes);
        return "/anime/anime-all";
    }
}

