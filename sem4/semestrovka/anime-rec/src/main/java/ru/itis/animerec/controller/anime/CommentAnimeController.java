package ru.itis.animerec.controller.anime;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.itis.animerec.dto.CommentDTO;
import ru.itis.animerec.dto.UserDTO;
import ru.itis.animerec.entity.UserEntity;
import ru.itis.animerec.security.UserDetailsImpl;
import ru.itis.animerec.service.CommentAnimeService;
import ru.itis.animerec.service.UserService;
import ru.itis.animerec.service.impl.CommentAnimeServiceImpl;

@RestController
@RequestMapping("/anime/comment")
@RequiredArgsConstructor
@Slf4j
public class CommentAnimeController {

    private final CommentAnimeService commentAnimeService;

    @PostMapping("/add")
    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "Добавить комментарий к аниме", description = "Этот эндпоинт добавляет комментарий к указанному аниме.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Комментарий успешно добавлен"),
            @ApiResponse(responseCode = "400", description = "Некорректные параметры запроса")
    })
    @ResponseBody
    public ResponseEntity<?> addAnimeComment(@RequestParam Long animeId, @RequestParam String content) {
        CommentDTO newComment = commentAnimeService.addCommentForAnime(animeId, content);
        return ResponseEntity.ok().body(newComment);
    }
}
