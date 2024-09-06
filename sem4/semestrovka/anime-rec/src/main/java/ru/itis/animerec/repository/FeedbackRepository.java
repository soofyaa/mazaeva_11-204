package ru.itis.animerec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.animerec.entity.AnimeEntity;
import ru.itis.animerec.entity.FeedbackEntity;
import ru.itis.animerec.entity.UserEntity;

import java.util.Optional;

@Repository
public interface FeedbackRepository extends JpaRepository<FeedbackEntity, Long> {
    Optional<FeedbackEntity> findByUserAndAnime(UserEntity user, AnimeEntity anime);
}
