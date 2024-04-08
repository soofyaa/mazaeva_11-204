package ru.itis.springdatajpaexample.dto;


import lombok.*;
import ru.itis.springdatajpaexample.entity.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private Long id;
    private String name;
    private String surname;
    private Integer age;

    public static UserDto fromEntity(User user){
        return UserDto.builder()
                .age(user.getAge())
                .name(user.getName())
                .surname(user.getSurname())
                .id(user.getId())
                .build();
    }

}