package org.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.demo.entity.Pet;
import org.example.demo.entity.Toy;
import org.example.demo.entity.User;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

    private Long id;
    private String name;
    private String surname;
    private List<String> pets;
    private List<String> toys;

    public UserDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.surname = user.getSurname();

        this.pets = user.getPets().stream()
                .map(Pet::getName)
                .distinct()
                .collect(Collectors.toList());

        this.toys = user.getPets().stream()
                .flatMap(pet -> pet.getToys().stream())
                    .map(Toy::getName)
                    .distinct()
                    .collect(Collectors.toList());
    }
}
