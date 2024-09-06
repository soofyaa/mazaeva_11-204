package org.example.demo.services;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import org.example.demo.entity.Pet;
import org.example.demo.entity.Toy;
import org.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CreateService {
    @Autowired
    private EntityManager entityManager;
    public void createEntities() {

        entityManager.getTransaction().begin();

        // Создаем пользователей
        User user1 = createUser("John1", "Doe");
        User user2 = createUser("Alice1", "Smith");
        User user3 = createUser("Bob1", "Johnson");

        // Создаем животных
        Pet pet1 = createPet("Max1", user1);
        Pet pet2 = createPet("Bella1", user2);
        Pet pet3 = createPet("Charlie1", user3);

        // Создаем игрушки
        Toy toy1 = createToy("Ball1");
        Toy toy2 = createToy("Bone1");
        Toy toy3 = createToy("Frisbee1");

        // Связываем животных с пользователями
        user1.setPets(List.of(pet1));
        user2.setPets(List.of(pet2));
        user3.setPets(List.of(pet3));

        // Связываем животных с игрушками
        pet1.setToys(Set.of(toy1, toy2));
        pet2.setToys(Set.of(toy2, toy3));
        pet3.setToys(Set.of(toy1, toy3));

        // Сохраняем все сущности в базе данных
        entityManager.persist(user1);
        entityManager.persist(user2);
        entityManager.persist(user3);
        entityManager.persist(pet1);
        entityManager.persist(pet2);
        entityManager.persist(pet3);
        entityManager.persist(toy1);
        entityManager.persist(toy2);
        entityManager.persist(toy3);

        entityManager.getTransaction().commit();
    }

    private User createUser(String name, String surname) {
        return User.builder()
                .name(name)
                .surname(surname)
                .pets(new ArrayList<>())
                .build();
    }

    private Pet createPet(String name, User user) {
        Pet pet = Pet.builder()
                .name(name)
                .user(user)
                .build();
        user.getPets().add(pet);
        return pet;
    }

    private Toy createToy(String name) {
        return Toy.builder()
                .name(name)
                .build();
    }
}
