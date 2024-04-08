package ru.itis.springdatajpaexample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

import ru.itis.springdatajpaexample.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u JOIN u.pets p WHERE p.name = :name AND size(p.toys) = :toyCount")
    List<User> findByPetsNameAndPetsToysCount(String name, Integer toyCount);

    List<User> findByPetsNameAndPetsAgeGreaterThan(String name, Integer ageGreater);
}