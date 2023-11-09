package ru.itis.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Post {
    private int id;
    private int userId;
    private String title;
    private String postText;
    private String username;
    private List<Comment> comments;
}
/*
CREATE TABLE posts (
    id SERIAL PRIMARY KEY,
    user_id INT,
    title VARCHAR(255),
    post_text TEXT
);
 */
