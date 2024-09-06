package ru.itis.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "second_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    @Column(length = 300)
    private String passwordHash;

    private String firstName;
    private String lastName;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private State state;

    public enum Role{
        USER,ADMIN
    }

    public enum State{
        ACTIVE,BANNED
    }

    public Boolean isActive(){
        return this.state == State.ACTIVE;
    }

    public Boolean isBanned(){
        return this.state == State.BANNED;
    }

    public Boolean isAdmin(){
        return this.role == Role.ADMIN;
    }
}
