package com.example.demo.dao;

import com.example.demo.extractors.PetResultExtractor;
import com.example.demo.models.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PetDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Pet> getPetsWithUserNameLengthEqualTen() {
        String sql = """
                    SELECT p.id AS pet_id, p.name AS pet_name, p.user_id as user_id, t.id AS toy_id, t.name AS toy_name
                    FROM pets p
                             JOIN my_users u ON p.user_id = u.id
                             LEFT JOIN pet_toy pt ON p.id = pt.pet_id
                             LEFT JOIN toys t ON pt.toy_id = t.id
                    WHERE LENGTH(CONCAT(u.name, u.surname)) = 10
                    """;

        return jdbcTemplate.query(sql, new PetResultExtractor());
    }
}
