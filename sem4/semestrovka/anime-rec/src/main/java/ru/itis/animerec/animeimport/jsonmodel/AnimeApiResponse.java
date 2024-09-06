package ru.itis.animerec.animeimport.jsonmodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.ArrayList;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AnimeApiResponse {
    private ArrayList<AnimeData> response;
}