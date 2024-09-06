package com.example.demo.extractors;

import com.example.demo.models.Pet;
import com.example.demo.models.Toy;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PetResultExtractor implements ResultSetExtractor<List<Pet>> {

    @Override
    public List<Pet> extractData(ResultSet rs) throws SQLException {
        Map<Long, Pet> petMap = new HashMap<>();

        while (rs.next()) {
            long petId = rs.getLong("pet_id");
            Pet pet = petMap.get(petId);

            if (pet == null) {
                pet = Pet.builder()
                        .id(petId)
                        .name(rs.getString("pet_name"))
                        .userId(rs.getLong("user_id"))
                        .toys(new ArrayList<>())
                        .build();
                petMap.put(petId, pet);
            }

            Long toyId = rs.getLong("toy_id");
            if (toyId != null) {
                Toy toy = Toy.builder()
                        .id(toyId)
                        .name(rs.getString("toy_name"))
                        .build();
                pet.getToys().add(toy);
            }
        }

        return new ArrayList<>(petMap.values());
    }
}
