package ru.itis.animerec.controller.user;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.animerec.entity.UserEntity;
import ru.itis.animerec.security.UserDetailsImpl;
import ru.itis.animerec.service.FeedbackService;
import ru.itis.animerec.service.WatchlistService;

@Controller
@RequestMapping("/feedback")
@RequiredArgsConstructor
@PreAuthorize("isAuthenticated()")
public class FeedbackController {
    private final WatchlistService watchlistService;
    private final FeedbackService feedbackService;

    @PostMapping("/save/{animeId}")
    @Operation(summary = "Добавить аниме в список аниме", description = "Этот эндпоинт добавляет указанное аниме в список аниме пользователя.")
    public String addToWatchlist(@PathVariable("animeId") Long animeId,
                                 @RequestParam("userListId") Long userListId) {

        watchlistService.addAnimeToWatchlist(animeId, userListId);
        return "redirect:/anime/"+animeId;
    }

    @PostMapping("/like/{animeId}")
    @Operation(summary = "Лайкнуть аниме", description = "Этот эндпоинт добавляет лайк указанному аниме.")
    public String likeAnime(@PathVariable Long animeId) {
        feedbackService.likeAnime(animeId);
        return "redirect:/anime/" + animeId;
    }

    @PostMapping("/dislike/{animeId}")
    @Operation(summary = "Дизлайкнуть аниме", description = "Этот эндпоинт добавляет дизлайк указанному аниме.")
    public String dislikeAnime(@PathVariable Long animeId) {

        feedbackService.dislikeAnime(animeId);

        return "redirect:/anime/" + animeId;
    }
}

