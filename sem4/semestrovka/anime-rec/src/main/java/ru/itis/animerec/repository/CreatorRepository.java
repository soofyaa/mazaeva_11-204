package ru.itis.animerec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.animerec.entity.CreatorEntity;

import java.util.Optional;

@Repository
public interface CreatorRepository extends JpaRepository<CreatorEntity, Long> {
    Optional<CreatorEntity> findByTitle(String name);
}