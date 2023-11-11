package ru.itis.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Photo {
    private int id;
    private int userId;

    private int petId;
    private byte[] data;
    private String username;
    private String photoData;
    private String petName;
}
