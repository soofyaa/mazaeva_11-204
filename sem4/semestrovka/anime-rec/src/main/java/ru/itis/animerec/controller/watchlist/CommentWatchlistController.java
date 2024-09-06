package ru.itis.animerec.controller.watchlist;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.itis.animerec.dto.CommentDTO;
import ru.itis.animerec.dto.UserDTO;
import ru.itis.animerec.entity.UserEntity;
import ru.itis.animerec.security.UserDetailsImpl;
import ru.itis.animerec.service.CommentWatchlistService;
import ru.itis.animerec.service.UserService;

@RestController
@RequestMapping("/watchlist/comment")
@RequiredArgsConstructor
public class CommentWatchlistController {

    private final CommentWatchlistService commentWatchlistService;

    @PostMapping("/add")
    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "Добавить комментарий к списку просмотра", description = "Этот эндпоинт добавляет комментарий к указанному списку просмотра.")
    public ResponseEntity<?> addWatchlistComment(@RequestParam Long watchlistId, @RequestParam String content) {
        CommentDTO newComment = commentWatchlistService.addCommentForWatchlist(watchlistId, content);
        return ResponseEntity.ok().body(newComment);
    }
}

