package com.example.demo.dao;

import com.example.demo.extractors.ToyResultExtractor;
import com.example.demo.models.Toy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ToyDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Toy> getToysWithMinTwoAnimals() {
        String sql = """
                    SELECT t.id, t.name FROM toys t
                    JOIN pet_toy ta ON t.id = ta.toy_id
                    GROUP BY t.id
                    HAVING COUNT(ta.pet_id) >= 2;
                    """;

        return jdbcTemplate.query(sql, new ToyResultExtractor());
    }
}
