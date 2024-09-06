package ru.itis.animerec.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "watchlist_comment")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentWatchlistEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "watchlist_id")
    private WatchlistEntity watchlist;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createdAt;
}
