package ru.itis.animerec.service;

import ru.itis.animerec.dto.AnimeDTO;
import ru.itis.animerec.dto.UserDTO;
import ru.itis.animerec.entity.UserEntity;
import ru.itis.animerec.utils.preference.UserPreferences;

import java.util.List;

public interface AnimeService {
    List<AnimeDTO> getTopNew100Animes();

    AnimeDTO getAnimeDetails(Long id, Boolean isAuthenticated);

    List<AnimeDTO> getRecommendedAnimes(List<AnimeDTO> allAnimes);

    List<AnimeDTO> getAllAnimes();

    List<AnimeDTO> getTopCommentedAnimes();
}
