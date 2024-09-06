package com.example.demo.dao;

import com.example.demo.extractors.UserResultExtractor;
import com.example.demo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> findPeopleWithToys() {
        String sql = """
                    SELECT u.id as user_id, u.name as user_name, u.surname as user_surname, 
                    p.id as pet_id, p.name AS pet_name, t.id as toy_id, t.name AS toy_name
                    FROM my_users u
                    INNER JOIN pets p ON u.id = p.user_id
                    LEFT JOIN (
                        SELECT pet_id, COUNT(name) AS toy_count
                        FROM pet_toy
                        INNER JOIN toys ON pet_toy.toy_id = toys.id
                        WHERE name LIKE '%plastic%'
                        GROUP BY pet_id
                        HAVING COUNT(name) >= 2
                    ) pt ON p.id = pt.pet_id
                    LEFT JOIN pet_toy pt2 ON p.id = pt2.pet_id
                    LEFT JOIN toys t ON pt2.toy_id = t.id
                    WHERE p.name LIKE '%t%' OR pt.toy_count >= 2;
                    """;

        return jdbcTemplate.query(sql, new UserResultExtractor());
    }
}
