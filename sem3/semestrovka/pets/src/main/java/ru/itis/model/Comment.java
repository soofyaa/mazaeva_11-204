package ru.itis.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Comment {
    private int id;
    private int postId;
    private int userId;
    private String text;
    private String username;
}
/*
CREATE TABLE comments (
    id SERIAL PRIMARY KEY,
    post_id INT,
    user_id INT,
    text TEXT
);
 */
