package ru.itis.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Pet {
    private int id;
    private int userId;
    private String name;
    private String description;

    private int avatarId;
    private List<Integer> picsId;
}

/*
CREATE TABLE pets (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    avatar_id INT,
    pics INT[] DEFAULT ARRAY[]::INT[]
);
 */
