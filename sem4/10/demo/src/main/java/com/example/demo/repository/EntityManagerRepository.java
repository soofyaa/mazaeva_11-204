package com.example.demo.repository;

import com.example.demo.entity.Pet;
import com.example.demo.entity.Toy;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EntityManagerRepository {

    @Autowired
    private EntityManager em;

    public List<String> getPetNamesWithPlasticToy() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<String> cq = cb.createQuery(String.class);

        Root<Pet> pet = cq.from(Pet.class);
        Join<Pet, Toy> toyJoin = pet.join("toys");

        cq.select(pet.get("name"))
                .distinct(true)
                .where(cb.like(cb.lower(toyJoin.get("name")), "%plastic%"));

        return em.createQuery(cq).getResultList();
    }
}
