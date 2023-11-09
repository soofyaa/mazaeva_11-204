package ru.itis.dao;

import lombok.SneakyThrows;
import ru.itis.model.Comment;
import ru.itis.utils.ConnectionContainer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CommentDAO {

    private static final String SQL_ADD_COMMENT = "INSERT INTO comments (post_id, user_id, text, username) VALUES (?, ?, ?, ?)";
    private static final String SQL_GET_ALL_COMMENTS = "SELECT * FROM comments WHERE post_id = ?";
    private static final String SQL_DELETE_COMMENT_BY_ID = "DELETE FROM comments WHERE id = ?";

    @SneakyThrows
    public static void addComment(Comment comment) {
        Connection connection = ConnectionContainer.getConnection();

        PreparedStatement statement = connection.prepareStatement(SQL_ADD_COMMENT);
        statement.setInt(1, comment.getPostId());
        statement.setInt(2, comment.getUserId());
        statement.setString(3, comment.getText());
        statement.setString(4, comment.getUsername());

        statement.executeUpdate();
    }

    @SneakyThrows
    public static List<Comment> getCommentsByPost(int postId) {
        List<Comment> comments = new ArrayList<>();
        Connection connection = ConnectionContainer.getConnection();

        PreparedStatement statement = connection.prepareStatement(SQL_GET_ALL_COMMENTS);
        statement.setInt(1, postId);

        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Comment comment = new Comment();
            comment.setId(resultSet.getInt("id"));
            comment.setPostId(resultSet.getInt("post_id"));
            comment.setUserId(resultSet.getInt("user_id"));
            comment.setText(resultSet.getString("text"));
            comment.setUsername(resultSet.getString("username"));

            comments.add(comment);
        }
        return comments;
    }

    @SneakyThrows
    public static void deleteCommentById(int commentId) {
        Connection connection = ConnectionContainer.getConnection();

        PreparedStatement statement = connection.prepareStatement(SQL_DELETE_COMMENT_BY_ID);
        statement.setInt(1, commentId);

        statement.executeUpdate();
    }
}
