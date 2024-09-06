package ru.itis.animerec.service;

import ru.itis.animerec.dto.CommentDTO;
import ru.itis.animerec.dto.UserDTO;
import ru.itis.animerec.entity.UserEntity;

import java.util.List;

public interface CommentAnimeService {
    CommentDTO addCommentForAnime(Long id, String content);
    List<CommentDTO> getCommentsByAnimeId(Long animeId);

    void deleteCommentById(Long id);
}
