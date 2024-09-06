package ru.itis.animerec.repository;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.itis.animerec.entity.AnimeEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AnimeRepository extends JpaRepository<AnimeEntity, Long> {

    @Override
    @EntityGraph(attributePaths = {"studios", "creators", "genres"})
    Optional<AnimeEntity> findById(Long id);

    @Query("SELECT a FROM AnimeEntity a " +
            "WHERE a.id IN (" +
            "    SELECT c.anime.id FROM CommentAnimeEntity c " +
            "    WHERE c.createdAt >= :fromDate " +
            "    GROUP BY c.anime.id " +
            "    ORDER BY COUNT(c.id) DESC" +
            ")")
    List<AnimeEntity> findTopCommentedAnimes(@Param("fromDate") LocalDateTime fromDate, Pageable pageable);
}

