package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "pets")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
    @JoinTable(name = "pet_toy",
            joinColumns = {@JoinColumn(name = "pet_id")},
            inverseJoinColumns = {@JoinColumn(name = "toy_id")})
    private List<Toy> toys;
}
