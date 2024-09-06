package ru.itis.animerec.animeimport.jsonmodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Rating {
    private Double average;
    private Integer counters;
}

