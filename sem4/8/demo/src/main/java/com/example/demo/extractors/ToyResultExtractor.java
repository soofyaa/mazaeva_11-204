package com.example.demo.extractors;

import com.example.demo.models.Toy;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ToyResultExtractor implements ResultSetExtractor<List<Toy>> {
    @Override
    public List<Toy> extractData(ResultSet rs) throws SQLException {
        List<Toy> toys = new ArrayList<>();
        while (rs.next()) {
            toys.add(Toy.builder().id(rs.getLong("id")).name(rs.getString("name")).build());
        }
        return toys;
    }
}
