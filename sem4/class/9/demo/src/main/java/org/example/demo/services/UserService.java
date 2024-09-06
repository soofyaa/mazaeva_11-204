package org.example.demo.services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import org.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private EntityManager entityManager;

    public User getUser(Long id) {

        entityManager.getTransaction().begin();
        String queryStr = "SELECT DISTINCT u FROM User u "
                + "LEFT JOIN fetch u.pets p "
                + "LEFT JOIN fetch p.toys "
                + "WHERE u.id = :userId";

        TypedQuery<User> query = entityManager.createQuery(queryStr, User.class);
        query.setParameter("userId", id);
        entityManager.getTransaction().commit();

        return query.getSingleResult();
    }







    public void updateUserParameter(Long userId, String parameterName, Object parameterValue) {
        User user = entityManager.find(User.class, userId);
        if (user != null) {
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            switch (parameterName) {
                case "name":
                    user.setName((String) parameterValue);
                    break;
                case "surname":
                    user.setSurname((String) parameterValue);
                    break;
                default:
                    throw new IllegalArgumentException("Неверное имя параметра: " + parameterName);
            }
            entityManager.merge(user);
            transaction.commit();
        }
    }
}
