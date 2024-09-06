package ru.itis.animerec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.animerec.entity.StudioEntity;

import java.util.Optional;

@Repository
public interface StudioRepository extends JpaRepository<StudioEntity, Long> {
    Optional<StudioEntity> findByTitle(String name);}