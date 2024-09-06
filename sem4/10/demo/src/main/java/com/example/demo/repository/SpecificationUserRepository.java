package com.example.demo.repository;

import com.example.demo.entity.Pet;
import com.example.demo.entity.Toy;
import com.example.demo.entity.User;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.awt.print.Book;
import java.util.List;

@Repository
public interface SpecificationUserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    default List<User> findUsersWithPetWithName(String petName) {
        Specification<User> spec = (user, query, criteriaBuilder) -> {
            Join<User, Pet> petJoin = user.join("pets", JoinType.INNER);
            return criteriaBuilder.equal(petJoin.get("name"), petName);
        };
        return findAll(spec);
    }

    default List<User> findUsersWithPetsHavingTwoOrMoreToys() {
        Specification<User> spec = (user, query, criteriaBuilder) -> {
            Join<User, Pet> petsJoin = user.join("pets");
            Join<Pet, Toy> toysJoin = petsJoin.join("toys");
            query.groupBy(user.get("id"));
            query.having(criteriaBuilder.ge(criteriaBuilder.count(toysJoin), 2));
            return null;
        };
        return findAll(spec);
    }
}
