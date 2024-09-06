package ru.itis.anime.entity.anime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "anime_data")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class AnimeData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @OneToOne(cascade = CascadeType.ALL)
    private Poster poster;

    private String title;
    private Integer status;

    @OneToOne(cascade = CascadeType.ALL)
    private Rating rating;

    private Integer year;

    @OneToOne(cascade = CascadeType.ALL)
    private AgeRating min_age;

    private Integer season;

    @OneToOne(cascade = CascadeType.ALL)
    private AnimeStatus anime_status;

    @OneToOne(cascade = CascadeType.ALL)
    private Type type;

    @ElementCollection
    private List<String> other_titles;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Creator> creators;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Studio> studios;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Genre> genres;

    @OneToOne(cascade = CascadeType.ALL)
    private Episodes episodes;
}
