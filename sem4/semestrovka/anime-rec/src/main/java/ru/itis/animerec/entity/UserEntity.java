package ru.itis.animerec.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import ru.itis.animerec.utils.preference.UserPreferences;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "animerec_users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 300)
    private String passwordHash;

    private String email;

    private String username;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private State state;

    @Lob
    private byte[] avatar;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "json")
    private UserPreferences preferences;

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