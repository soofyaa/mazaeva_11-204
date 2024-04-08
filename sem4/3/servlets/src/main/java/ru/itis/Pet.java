package ru.itis;

/*
CREATE TABLE pets (
                     id serial PRIMARY KEY,
                     name VARCHAR(255),
                     age INT,
                     height DECIMAL(5,2),
                     weight DECIMAL(5,2),
                     breed VARCHAR(255),
                     color VARCHAR(50)
);
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pet {
    private int id;
    private String name;
    private int age;
    private Double height;
    private Double weight;
    private String breed;
    private String color;
}
