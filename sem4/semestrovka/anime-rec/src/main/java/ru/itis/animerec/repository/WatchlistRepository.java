package ru.itis.animerec.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.itis.animerec.dto.WatchlistDTO;
import ru.itis.animerec.entity.WatchlistEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface WatchlistRepository extends JpaRepository<WatchlistEntity, Long> {

    @Override
    @EntityGraph(attributePaths = {"animes", "animes.genres", "animes.creators", "animes.studios"}, type = EntityGraph.EntityGraphType.FETCH)
    Optional<WatchlistEntity> findById(Long id);

    @EntityGraph(attributePaths = {"animes", "animes.genres", "animes.creators", "animes.studios"}, type = EntityGraph.EntityGraphType.FETCH)
    List<WatchlistEntity> findByUserId(Long userId);

    @Query("SELECT new ru.itis.animerec.dto.WatchlistDTO(w.id, w.name) FROM WatchlistEntity w WHERE w.user.id = :userId")
    List<WatchlistDTO> findListNamesAndIdsByUserId(@Param("userId") Long userId);

    @EntityGraph(attributePaths = {"animes", "animes.genres", "animes.creators", "animes.studios"}, type = EntityGraph.EntityGraphType.FETCH)
    @Query("SELECT w FROM WatchlistEntity w WHERE w.id = :id")
    WatchlistEntity findWatchlistWithAnimesById(@Param("id") Long id);

}