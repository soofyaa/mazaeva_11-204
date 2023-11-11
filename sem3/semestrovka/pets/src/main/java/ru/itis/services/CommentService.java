package ru.itis.services;

import ru.itis.dao.CommentDAO;
import ru.itis.model.Comment;
import ru.itis.model.User;
import ru.itis.utils.SessionManager;

import javax.servlet.http.HttpServletRequest;

public class CommentService {
    public static void deleteCommentById(int commentId) {
        CommentDAO.deleteCommentById(commentId);
    }

    public static void addComment(HttpServletRequest req, int postId, String commentText) {
        User user = SessionManager.getUserFromSession(req);

        Comment comment = new Comment();
        comment.setPostId(postId);
        comment.setUserId(user.getId());
        comment.setText(commentText);
        comment.setUsername(user.getUsername());

        // Добавление комментария в базу данных
        CommentDAO.addComment(comment);
    }
}
