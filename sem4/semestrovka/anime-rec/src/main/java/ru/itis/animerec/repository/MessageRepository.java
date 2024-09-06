package ru.itis.animerec.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.animerec.entity.MessageEntity;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<MessageEntity, Long> {
    @EntityGraph(attributePaths = {"user.id", "user.username", "user.avatar"})
    List<MessageEntity> findTop100ByOrderByTimestampAsc();
}