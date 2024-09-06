package ru.itis.animerec.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.itis.animerec.entity.CommentAnimeEntity;

import java.util.List;

public interface CommentAnimeRepository extends JpaRepository<CommentAnimeEntity, Long> {
    @Query("SELECT cae FROM CommentAnimeEntity cae JOIN FETCH cae.user WHERE cae.anime.id = :id")
    List<CommentAnimeEntity> findByAnimeId(Long id);
}