package ru.itis.animerec.utils.score;

import org.springframework.stereotype.Component;
import ru.itis.animerec.dto.AnimeDTO;
import ru.itis.animerec.dto.CreatorDTO;
import ru.itis.animerec.dto.GenreDTO;
import ru.itis.animerec.dto.StudioDTO;
import ru.itis.animerec.utils.preference.UserPreferences;

@Component
public class ScoreCalculator {

    public double calculateScore(AnimeDTO anime, UserPreferences preferences) {
        double totalScore = 0.0;

        totalScore += calculateTypeScore(anime, preferences);
        totalScore += calculateAnimeStatusScore(anime, preferences);
        totalScore += calculateStudioScore(anime, preferences);
        totalScore += calculateCreatorScore(anime, preferences);
        totalScore += calculateYearScore(anime, preferences);
        totalScore += calculateGenreScore(anime, preferences);

        return totalScore / 6;
    }

    private double calculateTypeScore(AnimeDTO anime, UserPreferences preferences) {
        return ScoreUtil.calculateScore(preferences.getType().getAll(), preferences.getType().getSubTypes(), anime.getType());
    }

    private double calculateAnimeStatusScore(AnimeDTO anime, UserPreferences preferences) {
        return ScoreUtil.calculateScore(preferences.getAnimeStatus().getAll(), preferences.getAnimeStatus().getSubTypes(), anime.getType());
    }

    private double calculateStudioScore(AnimeDTO anime, UserPreferences preferences) {
        return ScoreUtil.calculateScore(preferences.getStudio().getAll(), preferences.getStudio().getSubTypes(), anime.getStudios(), StudioDTO::getTitle);
    }

    private double calculateCreatorScore(AnimeDTO anime, UserPreferences preferences) {
        return ScoreUtil.calculateScore(preferences.getCreator().getAll(), preferences.getCreator().getSubTypes(), anime.getCreators(), CreatorDTO::getTitle);
    }

    private double calculateYearScore(AnimeDTO anime, UserPreferences preferences) {
        String year = String.valueOf(anime.getYear());
        return ScoreUtil.calculateScore(preferences.getYear().getAll(), preferences.getYear().getSubTypes(), year);
    }

    private double calculateGenreScore(AnimeDTO anime, UserPreferences preferences) {
        return ScoreUtil.calculateScore(preferences.getGenres().getAll(), preferences.getGenres().getSubTypes(), anime.getGenres(), GenreDTO::getTitle);
    }
}