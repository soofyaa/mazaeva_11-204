package ru.itis;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class User {
    private int id;
    private String name;
    private String surname;
    private int age;
    private String sex;
    private String email;
}
