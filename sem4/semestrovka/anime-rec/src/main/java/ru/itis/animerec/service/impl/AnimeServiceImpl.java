package ru.itis.animerec.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.itis.animerec.dto.*;
import ru.itis.animerec.entity.AnimeEntity;
import ru.itis.animerec.entity.FeedbackEntity;
import ru.itis.animerec.entity.UserEntity;
import ru.itis.animerec.repository.AnimeRepository;
import ru.itis.animerec.repository.AnimeRepositoryCustom;
import ru.itis.animerec.repository.FeedbackRepository;
import ru.itis.animerec.security.UserDetailsImpl;
import ru.itis.animerec.service.AnimeService;
import ru.itis.animerec.service.WatchlistService;
import ru.itis.animerec.utils.mapper.UserMapper;
import ru.itis.animerec.utils.score.ScoreCalculator;
import ru.itis.animerec.utils.preference.UserPreferences;
import ru.itis.animerec.utils.mapper.AnimeMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AnimeServiceImpl implements AnimeService {

    private final AnimeRepository animeRepository;
    private final FeedbackRepository feedbackRepository;
    private final ScoreCalculator scoreCalculator;
    private final AnimeMapper animeMapper;
    private final AnimeRepositoryCustom animeRepositoryCustom;

    @Override
    public List<AnimeDTO> getTopNew100Animes() {
        log.info("Getting top 100 new animes");
        List<AnimeEntity> animeEntities = animeRepositoryCustom.findTop100ByYearAndRating();
        return animeEntities.stream()
                .map(animeMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<AnimeDTO> getAllAnimes() {
        log.info("Getting all animes");
        List<AnimeEntity> animes = animeRepository.findAll();
        return animes.stream()
                .map(animeMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public AnimeDTO getAnimeDetails(Long id, Boolean isAuthenticated) {
        log.info("Getting details for anime with id: {}", id);
        AnimeEntity anime = animeRepository.findById(id).orElseThrow();
        AnimeDTO animeDTO = animeMapper.toDto(anime);

        if (isAuthenticated) {
            UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            UserEntity user = userDetails.getUser();
            if (user != null) {
                FeedbackEntity feedback = feedbackRepository.findByUserAndAnime(user, anime).orElse(null);
                if (feedback != null) {
                    animeDTO.setLiked(feedback.isLiked());
                    animeDTO.setDisliked(feedback.isDislike());
                }
            }
        }

        return animeDTO;
    }

    @Transactional
    public List<AnimeDTO> getRecommendedAnimes(List<AnimeDTO> allAnimes) {
        log.info("Getting recommended animes");
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserEntity user = userDetails.getUser();
        List<AnimeDTO> recommendedAnimes = new ArrayList<>();

        for (AnimeDTO anime : allAnimes) {
            double score = scoreCalculator.calculateScore(anime, user.getPreferences());
            score += anime.getRating() / 100;

            anime.setScore(score);
            recommendedAnimes.add(anime);
        }

        recommendedAnimes.sort((a1, a2) -> Double.compare(a2.getScore(), a1.getScore()));

        return recommendedAnimes;
    }

    @Override
    public List<AnimeDTO> getTopCommentedAnimes() {
        log.info("Getting top commented animes");
        LocalDateTime fromDate = LocalDateTime.now().minusHours(24);
        Pageable topFive = PageRequest.of(0, 5);
        List<AnimeEntity> animeEntities = animeRepository.findTopCommentedAnimes(fromDate, topFive);
        return animeEntities.stream()
                .map(animeMapper::toDto)
                .collect(Collectors.toList());
    }
}