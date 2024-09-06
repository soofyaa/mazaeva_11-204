package ru.itis.animerec.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.itis.animerec.dto.CommentDTO;
import ru.itis.animerec.dto.UserDTO;
import ru.itis.animerec.entity.*;
import ru.itis.animerec.repository.*;
import ru.itis.animerec.security.UserDetailsImpl;
import ru.itis.animerec.service.CommentAnimeService;
import ru.itis.animerec.utils.mapper.CommentAnimeMapper;
import ru.itis.animerec.utils.mapper.UserMapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentAnimeServiceImpl implements CommentAnimeService {

    private final CommentAnimeRepository commentAnimeRepository;
    private final AnimeRepository animeRepository;
    private final CommentAnimeMapper commentAnimeMapper;

    @Override
    public CommentDTO addCommentForAnime(Long animeId, String content) {
        log.info("Adding comment for anime with id: {}", animeId);
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserEntity user = userDetails.getUser();
        AnimeEntity anime = animeRepository.findById(animeId)
                .orElseThrow(() -> new NoSuchElementException("Anime not found with id: " + animeId));

        CommentAnimeEntity commentAnimeEntity = CommentAnimeEntity.builder()
                .user(user)
                .anime(anime)
                .content(content)
                .createdAt(LocalDateTime.now())
                .build();

        commentAnimeEntity = commentAnimeRepository.save(commentAnimeEntity);
        log.info("Comment added successfully with id: {}", commentAnimeEntity.getId());

        return commentAnimeMapper.toDTO(commentAnimeEntity);
    }

    @Override
    @Transactional
    public List<CommentDTO> getCommentsByAnimeId(Long animeId) {
        log.info("Fetching comments for anime with id: {}", animeId);
        List<CommentAnimeEntity> comments = commentAnimeRepository.findByAnimeId(animeId);
        log.info("Fetched {} comments", comments.size());
        return comments.stream()
                .map(commentAnimeMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteCommentById(Long id) {
        log.info("Deleting comment with id: {}", id);
        commentAnimeRepository.deleteById(id);
        log.info("Comment deleted successfully with id: {}", id);
    }
}