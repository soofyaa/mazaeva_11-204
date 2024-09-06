package ru.itis.animerec.repository;

import ru.itis.animerec.entity.AnimeEntity;

import java.util.List;

public interface AnimeRepositoryCustom {
    List<AnimeEntity> findTop100ByYearAndRating();
}
