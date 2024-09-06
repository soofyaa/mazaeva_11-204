package ru.itis.animerec.animeimport.jsonmodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AnimeData {
    private String description;
    private Poster poster;
    private String title;
    private Integer status;
    private Rating rating;
    private Integer year;
    private AgeRating min_age;
    private Integer views;
    private Integer season;
    private String original;
    private AnimeStatus anime_status;
    private Type type;
    private List<Creator> creators;
    private List<Studio> studios;
    private List<Genre> genres;
    private Episodes episodes;
}