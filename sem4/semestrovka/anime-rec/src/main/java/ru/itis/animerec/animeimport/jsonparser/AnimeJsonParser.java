package ru.itis.animerec.animeimport.jsonparser;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.animerec.animeimport.jsonmodel.AnimeData;
import ru.itis.animerec.entity.AnimeEntity;
import ru.itis.animerec.entity.CreatorEntity;
import ru.itis.animerec.entity.GenreEntity;
import ru.itis.animerec.entity.StudioEntity;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AnimeJsonParser {

    private final AnimeJsonMapper animeJsonMapper;

    public AnimeEntity getAnimeFromJsonToEntity(AnimeData anime) {
        if (anime == null) {
            return null;
        }

        return AnimeEntity.builder()
                .description(anime.getDescription())
                .poster(getFullsizePoster(anime))
                .title(anime.getTitle())
                .minAge(getMinAgeTitle(anime))
                .animeStatus(getAnimeStatusTitle(anime))
                .type(getTypeName(anime))
                .episodes(getEpisodeCount(anime))
                .year(anime.getYear())
                .views(anime.getViews())
                .season(anime.getSeason())
                .original(anime.getOriginal())
                .rating(getAverageRating(anime))
                .genres(getGenreEntities(anime))
                .creators(getCreatorEntities(anime))
                .studios(getStudioEntities(anime))
                .build();
    }

    private String getFullsizePoster(AnimeData anime) {
        if (anime.getPoster() != null) {
            return anime.getPoster().getFullsize();
        }
        return null;
    }

    private String getMinAgeTitle(AnimeData anime) {
        if (anime.getMin_age() != null) {
            return anime.getMin_age().getTitle();
        }
        return null;
    }

    private String getAnimeStatusTitle(AnimeData anime) {
        if (anime.getAnime_status() != null) {
            return anime.getAnime_status().getTitle();
        }
        return null;
    }

    private String getTypeName(AnimeData anime) {
        if (anime.getType() != null) {
            return anime.getType().getName();
        }
        return null;
    }

    private int getEpisodeCount(AnimeData anime) {
        if (anime.getEpisodes() != null) {
            return anime.getEpisodes().getCount();
        }
        return 0;
    }

    private double getAverageRating(AnimeData anime) {
        if (anime.getRating() != null) {
            return anime.getRating().getAverage();
        }
        return 0.0;
    }

    private Set<GenreEntity> getGenreEntities(AnimeData anime) {
        if (anime.getGenres() != null) {
            return anime.getGenres().stream()
                    .map(animeJsonMapper::toGenreEntity)
                    .collect(Collectors.toSet());
        }
        return Collections.emptySet();
    }

    private Set<CreatorEntity> getCreatorEntities(AnimeData anime) {
        if (anime.getCreators() != null) {
            return anime.getCreators().stream()
                    .map(animeJsonMapper::toCreatorEntity)
                    .collect(Collectors.toSet());
        }
        return Collections.emptySet();
    }

    private Set<StudioEntity> getStudioEntities(AnimeData anime) {
        if (anime.getStudios() != null) {
            return anime.getStudios().stream()
                    .map(animeJsonMapper::toStudioEntity)
                    .collect(Collectors.toSet());
        }
        return Collections.emptySet();
    }
}
