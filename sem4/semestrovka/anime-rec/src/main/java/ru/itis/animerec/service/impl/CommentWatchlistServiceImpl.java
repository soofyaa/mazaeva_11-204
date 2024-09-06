package ru.itis.animerec.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.itis.animerec.dto.CommentDTO;
import ru.itis.animerec.dto.UserDTO;
import ru.itis.animerec.entity.CommentAnimeEntity;
import ru.itis.animerec.entity.CommentWatchlistEntity;
import ru.itis.animerec.entity.UserEntity;
import ru.itis.animerec.entity.WatchlistEntity;
import ru.itis.animerec.repository.*;
import ru.itis.animerec.security.UserDetailsImpl;
import ru.itis.animerec.service.CommentWatchlistService;
import ru.itis.animerec.utils.mapper.CommentWatchlistMapper;
import ru.itis.animerec.utils.mapper.UserMapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
public class CommentWatchlistServiceImpl implements CommentWatchlistService {

    private final CommentWatchlistRepository commentWatchlistRepository;
    private final WatchlistRepository watchlistRepository;
    private final CommentWatchlistMapper commentWatchlistMapper;

    @Override
    public CommentDTO addCommentForWatchlist(Long watchlistId, String content) {
        log.info("Adding comment for watchlist with id: {}", watchlistId);
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserEntity user = userDetails.getUser();
        WatchlistEntity watchlist = watchlistRepository.findById(watchlistId)
                .orElseThrow(() -> new NoSuchElementException("Watchlist not found with id: " + watchlistId));

        CommentWatchlistEntity commentWatchlistEntity = CommentWatchlistEntity.builder()
                .user(user)
                .watchlist(watchlist)
                .content(content)
                .createdAt(LocalDateTime.now())
                .build();

        commentWatchlistEntity = commentWatchlistRepository.save(commentWatchlistEntity);
        log.info("Comment added successfully with id: {}", commentWatchlistEntity.getId());

        return commentWatchlistMapper.toDTO(commentWatchlistEntity);
    }

    @Override
    @Transactional
    public List<CommentDTO> getCommentsByWatchlistId(Long watchlistId) {
        log.info("Fetching comments for watchlist with id: {}", watchlistId);
        List<CommentWatchlistEntity> comments = commentWatchlistRepository.findByWatchlistId(watchlistId);
        log.info("Fetched {} comments", comments.size());
        return comments.stream()
                .map(commentWatchlistMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteCommentById(Long id) {
        log.info("Deleting comment with id: {}", id);
        commentWatchlistRepository.deleteById(id);
        log.info("Comment deleted successfully with id: {}", id);
    }
}

