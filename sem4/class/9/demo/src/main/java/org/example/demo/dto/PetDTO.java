package org.example.demo.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.demo.entity.Pet;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PetDTO {

    private Long id;

    private String name;

    public static PetDTO fromEntity(Pet pet){
        return PetDTO.builder()
                .name(pet.getName())
                .id(pet.getId()).build();
    }
}
