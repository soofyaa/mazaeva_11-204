package ru.itis.animerec.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnimeDTO {

    private Long id;
    private String description;
    private String poster;
    private String title;
    private String minAge;
    private String animeStatus;
    private String type;
    private Integer episodes;
    private Integer year;
    private Integer views;
    private Integer season;
    private String original;
    private Double rating;

    private List<GenreDTO> genres;
    private List<CreatorDTO> creators;
    private List<StudioDTO> studios;

    private boolean liked = false;
    private boolean disliked = false;

    private double score;
}
