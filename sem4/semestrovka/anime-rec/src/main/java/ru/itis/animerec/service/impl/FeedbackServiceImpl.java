package ru.itis.animerec.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.itis.animerec.entity.*;
import ru.itis.animerec.repository.AnimeRepository;
import ru.itis.animerec.repository.FeedbackRepository;
import ru.itis.animerec.repository.UserRepository;
import ru.itis.animerec.security.UserDetailsImpl;
import ru.itis.animerec.service.FeedbackService;
import ru.itis.animerec.utils.preference.PreferenceUtil;
import ru.itis.animerec.utils.preference.UserPreferences;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
@Slf4j
public class FeedbackServiceImpl implements FeedbackService {

    private final AnimeRepository animeRepository;
    private final UserRepository userRepository;
    private final FeedbackRepository feedbackRepository;

    @Transactional
    public void likeAnime(Long animeId) {
        log.info("Liking anime with id: {}", animeId);
        FeedbackEntity feedback = getUserAnimeFeedback(animeId);
        feedback.setLiked(true);
        feedback.setDislike(false);

        feedbackRepository.save(feedback);
        updateUserPreferences(feedback.getUser(), feedback.getAnime(), true);
        userRepository.save(feedback.getUser());
        log.info("Anime liked successfully");
    }

    @Transactional
    public void dislikeAnime(Long animeId) {
        log.info("Disliking anime with id: {}", animeId);
        FeedbackEntity feedback = getUserAnimeFeedback(animeId);
        feedback.setLiked(false);
        feedback.setDislike(true);

        feedbackRepository.save(feedback);
        updateUserPreferences(feedback.getUser(), feedback.getAnime(), false);
        userRepository.save(feedback.getUser());
        log.info("Anime disliked successfully");
    }

    private FeedbackEntity getUserAnimeFeedback(Long animeId) {
        log.debug("Fetching feedback for anime with id: {}", animeId);
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserEntity user = userDetails.getUser();
        AnimeEntity anime = animeRepository.findById(animeId)
                .orElseThrow(() -> new RuntimeException("Anime not found with id: " + animeId));

        return feedbackRepository.findByUserAndAnime(user, anime)
                .orElse(FeedbackEntity.builder().user(user).anime(anime).build());
    }

    private void updateUserPreferences(UserEntity user, AnimeEntity anime, boolean isLike) {
        log.debug("Updating user preferences for anime with id: {}", anime.getId());
        UserPreferences preferences = user.getPreferences();
        if (preferences == null) {
            preferences = new UserPreferences();
            user.setPreferences(preferences);
        }

        preferences.setType(updatePreference(preferences.getType(), isLike, anime.getType()));
        preferences.setAnimeStatus(updatePreference(preferences.getAnimeStatus(), isLike, anime.getAnimeStatus()));
        preferences.setStudio(updatePreference(preferences.getStudio(), isLike, anime.getStudios().stream().map(StudioEntity::getTitle).toArray(String[]::new)));
        preferences.setCreator(updatePreference(preferences.getCreator(), isLike, anime.getCreators().stream().map(CreatorEntity::getTitle).toArray(String[]::new)));
        preferences.setYear(updatePreference(preferences.getYear(), isLike, String.valueOf(anime.getYear())));
        preferences.setGenres(updatePreference(preferences.getGenres(), isLike, anime.getGenres().stream().map(GenreEntity::getTitle).toArray(String[]::new)));
        log.debug("User preferences updated successfully");
    }

    private UserPreferences.Preferences updatePreference(UserPreferences.Preferences preference, boolean isLike, String... subTypes) {
        log.debug("Updating preferences: isLike={}, subTypes={}", isLike, Arrays.toString(subTypes));
        if (isLike) {
            return PreferenceUtil.incrementPreference(preference, subTypes);
        } else {
            return PreferenceUtil.decrementPreference(preference, subTypes);
        }
    }
}
