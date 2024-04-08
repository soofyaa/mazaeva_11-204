package ru.itis.springdatajpaexample.dto;

import lombok.*;
import ru.itis.springdatajpaexample.entity.Toy;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ToyDto {

    private Long id;
    private String name;

    public static ToyDto fromEntity(Toy toy) {
        return ToyDto.builder()
                .id(toy.getId())
                .name(toy.getName())
                .build();
    }

}
