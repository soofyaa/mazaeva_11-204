package ru.itis.animerec.animeimport;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.itis.animerec.animeimport.jsonmodel.AnimeApiResponse;
import ru.itis.animerec.animeimport.jsonmodel.Creator;
import ru.itis.animerec.animeimport.jsonparser.AnimeJsonParser;
import ru.itis.animerec.entity.AnimeEntity;
import ru.itis.animerec.entity.CreatorEntity;
import ru.itis.animerec.entity.GenreEntity;
import ru.itis.animerec.entity.StudioEntity;
import ru.itis.animerec.repository.AnimeRepository;
import ru.itis.animerec.repository.CreatorRepository;
import ru.itis.animerec.repository.GenreRepository;
import ru.itis.animerec.repository.StudioRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
@Transactional
@RequiredArgsConstructor
public class GetAllAnimeFromApi implements CommandLineRunner {

    private final AnimeRepository animeRepository;

    private final CreatorRepository creatorRepository;

    private final GenreRepository genreRepository;

    private final StudioRepository studioRepository;

    private final AnimeJsonParser animeJsonParser;

    private final RestTemplate restTemplate;

    private static final String BASE_URL = "https://api.yani.tv/anime?sort=views&limit=50&offset=";
    private static final int TOTAL_REQUESTS = 200;
    private static final int LIMIT = 50;

    public void fetchAndSaveAllAnime() {
        IntStream.range(0, TOTAL_REQUESTS).forEach(i -> {
            String url = BASE_URL + (i * LIMIT);
            AnimeApiResponse response = restTemplate.getForObject(url, AnimeApiResponse.class);

            if (response != null && response.getResponse() != null) {
                List<AnimeEntity> animeEntities = response.getResponse().stream()
                        .map(animeJsonParser::getAnimeFromJsonToEntity)
                        .filter(animeEntity -> animeEntity.getTitle() != null && !animeEntity.getTitle().isEmpty())
                        .toList();

                animeEntities.forEach(this::ensureUniqueRelationsAndSave);
            }
        });
    }

    private void ensureUniqueRelationsAndSave(AnimeEntity animeEntity) {
        // Ensure unique creators
        Set<CreatorEntity> uniqueCreators = animeEntity.getCreators().stream()
                .map(creator -> creatorRepository.findByTitle(creator.getTitle())
                        .orElse(creator))
                .collect(Collectors.toSet());
        animeEntity.setCreators(uniqueCreators);

        // Ensure unique genres
        Set<GenreEntity> uniqueGenres = animeEntity.getGenres().stream()
                .map(genre -> genreRepository.findByTitle(genre.getTitle())
                        .orElse(genre))
                .collect(Collectors.toSet());
        animeEntity.setGenres(uniqueGenres);

        // Ensure unique studios
        Set<StudioEntity> uniqueStudios = animeEntity.getStudios().stream()
                .map(studio -> studioRepository.findByTitle(studio.getTitle())
                        .orElse(studio))
                .collect(Collectors.toSet());
        animeEntity.setStudios(uniqueStudios);

        animeRepository.save(animeEntity);
    }

    @Override
    public void run(String... args) throws Exception {

        long animeCount = animeRepository.count();

        if (animeCount < 200) {
            fetchAndSaveAllAnime();
        }
    }
}

