package org.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.demo.entity.Pet;
import org.example.demo.entity.Toy;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ToyDTO {

    private Long id;

    private String name;

    public static PetDTO fromEntity(Toy toy){
        return PetDTO.builder()
                .name(toy.getName())
                .id(toy.getId()).build();
    }
}
