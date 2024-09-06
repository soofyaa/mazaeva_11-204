package ru.itis.animerec.service;

import ru.itis.animerec.dto.CommentDTO;
import ru.itis.animerec.dto.UserDTO;
import ru.itis.animerec.entity.UserEntity;

import java.util.List;

public interface CommentWatchlistService {
    CommentDTO addCommentForWatchlist(Long id, String content);
    List<CommentDTO> getCommentsByWatchlistId(Long watchlistId);
    void deleteCommentById(Long id);
}
