package ru.itis.animerec.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "feedback")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "anime_id")
    private AnimeEntity anime;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean liked = false;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean dislike = false;
}