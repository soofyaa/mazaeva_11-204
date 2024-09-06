package ru.itis.animerec.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.*;
import ru.itis.animerec.entity.WatchlistEntity;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WatchlistDTO {
    private Long id;
    private String name;
    private String description;
    private String posterBase64;
    private Set<AnimeDTO> animes;
    private Long userId;

    public WatchlistDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}