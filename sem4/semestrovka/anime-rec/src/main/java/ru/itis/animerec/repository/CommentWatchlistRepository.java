package ru.itis.animerec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.itis.animerec.entity.CommentAnimeEntity;
import ru.itis.animerec.entity.CommentWatchlistEntity;

import java.util.List;

public interface CommentWatchlistRepository extends JpaRepository<CommentWatchlistEntity, Long>{

    @Query("SELECT cwe FROM CommentWatchlistEntity cwe JOIN FETCH cwe.user WHERE cwe.watchlist.id = :id")
    List<CommentWatchlistEntity> findByWatchlistId(Long id);

    void deleteByWatchlistId(Long watchlistId);

}
