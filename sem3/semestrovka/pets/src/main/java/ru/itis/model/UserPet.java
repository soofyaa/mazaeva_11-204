package ru.itis.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserPet {
    private int userId;
    private int petId;
}

/*
CREATE TABLE user_pets (
    user_id INTEGER REFERENCES users(id),
    pet_id INTEGER REFERENCES pets(id),
    PRIMARY KEY (user_id, pet_id)
);
 */
