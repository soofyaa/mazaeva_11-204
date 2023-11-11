package ru.itis.dao;

import lombok.SneakyThrows;
import ru.itis.model.Post;
import ru.itis.utils.ConnectionContainer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PostDAO {
    final private static String SQL_ADD_POST = "INSERT INTO posts (user_id, title, post_text) VALUES (?, ?, ?)";
    final private static String SQL_FIND_POST_BY_ID = "SELECT * FROM posts WHERE id = ?";
    final private static String SQL_DELETE_POST_BY_ID = "DELETE FROM posts WHERE id = ?";
    final private static String SQL_FIND_USER_ID_BY_POST_ID = "SELECT user_id FROM posts WHERE id = ?";
    final private static String SQL_GET_ALL_POSTS_WHITH_USERNAMES =
            """
            SELECT posts.id, posts.user_id, users.username, posts.title, posts.post_text
            FROM posts
            JOIN users ON posts.user_id = users.id;
            """;
    @SneakyThrows
    public static int addPost(int userId, String title, String postText) {
        int postId = -1;
        Connection connection = ConnectionContainer.getConnection();
        PreparedStatement statement = connection.prepareStatement(SQL_ADD_POST, Statement.RETURN_GENERATED_KEYS);

        statement.setInt(1, userId);
        statement.setString(2, title);
        statement.setString(3, postText);

        int affectedRows = statement.executeUpdate();

        if (affectedRows > 0) {
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                postId = resultSet.getInt(1);
            }
        }
        return postId;
    }

    @SneakyThrows
    public static Post findPostById(int postId) {
        Connection connection = ConnectionContainer.getConnection();
        PreparedStatement statement = connection.prepareStatement(SQL_FIND_POST_BY_ID);

        statement.setInt(1, postId);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            Post post = new Post();
            post.setId(postId);
            post.setUserId(resultSet.getInt("user_id"));
            post.setTitle(resultSet.getString("title"));
            post.setPostText(resultSet.getString("post_text"));

            return post;
        } else {
            return null;
        }
    }

    @SneakyThrows
    public static List<Post> getAllPostsWithUsernames() {
        List<Post> posts = new ArrayList<>();

        try (Connection connection = ConnectionContainer.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_GET_ALL_POSTS_WHITH_USERNAMES);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                Post post = Post.builder()
                        .id(resultSet.getInt("id"))
                        .userId(resultSet.getInt("user_id"))
                        .username(resultSet.getString("username"))
                        .title(resultSet.getString("title"))
                        .postText(resultSet.getString("post_text"))
                        .build();

                posts.add(post);
            }
        }

        return posts;
    }

    @SneakyThrows
    public static void deletePostById(int postId) {
        Connection connection = ConnectionContainer.getConnection();

        PreparedStatement statement = connection.prepareStatement(SQL_DELETE_POST_BY_ID);
        statement.setInt(1, postId);

        statement.executeUpdate();
    }

    @SneakyThrows
    public static int findUserIdByPostId(int postId) {
        Connection connection = ConnectionContainer.getConnection();
        PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_ID_BY_POST_ID);

        statement.setInt(1, postId);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            return resultSet.getInt("user_id");
        }
        return -1;
    }
}
