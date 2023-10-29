package ru.itis.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Pet {
    String ownerName;
    String petName;
    String type;
    int age;
    String sex;
    String color;
}
