package ru.itis.services;

import ru.itis.dao.CommentDAO;
import ru.itis.dao.PostDAO;
import ru.itis.dao.UserDAO;
import ru.itis.model.Comment;
import ru.itis.model.Post;
import ru.itis.model.User;
import ru.itis.utils.SessionManager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class PostService {
    public static int addPost(HttpServletRequest req, String title, String postText) {
        User user = SessionManager.getUserFromSession(req);
        return PostDAO.addPost(user.getId(), title, postText);
    }

    public static void deletePostById(int postId) {
        PostDAO.deletePostById(postId);
    }

    public static Post getPostDetails(HttpServletRequest req) {
        int postId = Integer.parseInt(req.getPathInfo().substring(1));
        Post post = PostDAO.findPostById(postId);
        List<Comment> comments = CommentDAO.getCommentsByPost(postId);

        if (post != null) {
            String username = UserDAO.findUsernameById(post.getUserId());
            post.setUsername(username);
            post.setComments(comments);
        }

        return post;
    }
}
