package ru.itis.animerec.controller.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.itis.animerec.service.CommentAnimeService;
import ru.itis.animerec.service.CommentWatchlistService;
import ru.itis.animerec.service.UserService;
import ru.itis.animerec.service.WatchlistService;
import java.net.URI;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ADMIN')")
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;
    private final CommentAnimeService commentAnimeService;
    private final CommentWatchlistService commentWatchlistService;
    private final WatchlistService watchlistService;

    @DeleteMapping("/user/{id}")
    @Operation(summary = "Удаление пользователя по ID", description = "Этот эндпоинт удаляет пользователя по его идентификатору.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "Пользователь успешно удален, происходит перенаправление на список пользователей"),
            @ApiResponse(responseCode = "404", description = "Пользователь с указанным ID не найден")
    })
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/user/" + id));
        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }

    @PostMapping("/user/{id}/block")
    @Operation(summary = "Блокировка пользователя по ID", description = "Этот эндпоинт блокирует пользователя по его идентификатору.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "Пользователь успешно заблокирован, происходит перенаправление на детали пользователя"),
            @ApiResponse(responseCode = "404", description = "Пользователь с указанным ID не найден")
    })
    public ResponseEntity<Void> blockUser(@PathVariable Long id) {
        userService.blockUserById(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/user/" + id));
        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }

    @DeleteMapping("/anime/{animeId}/comment/{commentId}")
    @Operation(summary = "Удаление комментария к аниме", description = "Этот эндпоинт удаляет комментарий к конкретному аниме.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "Комментарий успешно удален, происходит перенаправление на детали аниме"),
            @ApiResponse(responseCode = "404", description = "Аниме или комментарий с указанным ID не найдены")
    })
    public ResponseEntity<?> deleteAnimeComment(@PathVariable Long animeId, @PathVariable Long commentId) {
        commentAnimeService.deleteCommentById(commentId);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/anime/" + animeId));
        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }

    @DeleteMapping("/watchlist/{watchlistId}/comment/{commentId}")
    @Operation(summary = "Удаление комментария к списку аниме", description = "Этот эндпоинт удаляет комментарий к конкретному списку аниме.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "Комментарий успешно удален, происходит перенаправление на детали списка аниме"),
            @ApiResponse(responseCode = "404", description = "Список аниме или комментарий с указанным ID не найдены")
    })
    public ResponseEntity<?> deleteWatchlistComment(@PathVariable Long watchlistId, @PathVariable Long commentId) {
        commentWatchlistService.deleteCommentById(commentId);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/watchlist/" + watchlistId));
        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }

    @DeleteMapping("/user/{userId}/watchlist/{id}")
    @Operation(summary = "Удаление списка аниме по ID для пользователя", description = "Этот эндпоинт удаляет список аниме для конкретного пользователя.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "Список аниме успешно удален, происходит перенаправление на детали пользователя"),
            @ApiResponse(responseCode = "404", description = "Пользователь или список аниме с указанным ID не найдены")
    })
    public ResponseEntity<?> deleteAnimeList(@PathVariable Long userId, @PathVariable Long id) {
        watchlistService.deleteWatchlistById(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/user/" + userId));
        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }
}

