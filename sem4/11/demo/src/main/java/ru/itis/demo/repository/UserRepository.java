package ru.itis.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.itis.demo.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findOneByEmail(String email);

    @Modifying
    @Query("UPDATE User u SET u.state = :state WHERE u.role != :role")
    void updateAllNonAdminUsersToBanned(@Param("state") User.State state, @Param("role") User.Role role);
}