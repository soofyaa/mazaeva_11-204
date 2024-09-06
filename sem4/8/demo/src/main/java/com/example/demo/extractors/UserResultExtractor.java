package com.example.demo.extractors;

import com.example.demo.models.Pet;
import com.example.demo.models.Toy;
import com.example.demo.models.User;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserResultExtractor implements ResultSetExtractor<List<User>> {
    @Override
    public List<User> extractData(ResultSet resultSet) throws SQLException {
        Map<Long, User> userMap = new HashMap<>();
        while (resultSet.next()) {
            long userId = resultSet.getLong("user_id");
            User user = userMap.get(userId);
            if (user == null) {
                user = new User();
                user.setId(userId);
                user.setName(resultSet.getString("user_name"));
                user.setSurname(resultSet.getString("user_surname"));
                user.setPets(new ArrayList<>());
                userMap.put(userId, user);
            }

            Pet pet = new Pet();
            pet.setId(resultSet.getLong("pet_id"));
            pet.setName(resultSet.getString("pet_name"));
            pet.setUserId(userId);
            pet.setToys(new ArrayList<>());
            user.getPets().add(pet);

            Toy toy = new Toy();
            toy.setId(resultSet.getLong("toy_id"));
            toy.setName(resultSet.getString("toy_name"));
            pet.getToys().add(toy);
        }
        return new ArrayList<>(userMap.values());
    }
}
