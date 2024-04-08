package ru.itis.springdatajpaexample.dto;

import lombok.*;
import ru.itis.springdatajpaexample.entity.Pet;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PetDto {

    private Long id;
    private String name;
    private Integer age;

    public static PetDto fromEntity(Pet pet){
        return PetDto.builder()
                .age(pet.getAge())
                .name(pet.getName())
                .id(pet.getId()).build();
    }
}
