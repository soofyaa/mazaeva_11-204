package ru.itis.servlets;

import ru.itis.dao.CommentDAO;
import ru.itis.dao.PostDAO;
import ru.itis.dao.UserDAO;
import ru.itis.model.Comment;
import ru.itis.model.Post;
import ru.itis.model.User;
import ru.itis.utils.SessionManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/post/*")
public class PostServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int postId = Integer.parseInt(req.getPathInfo().substring(1));
        Post post = PostDAO.findPostById(postId);
        List<Comment> comments = CommentDAO.getCommentsByPost(postId);

        if (post != null) {
            String username = UserDAO.findUserById(post.getUserId());
            post.setUsername(username);
            post.setComments(comments);

        }
        req.setAttribute("isAdmin", SessionManager.getAttribute(req,"isAdmin"));
        req.setAttribute("post", post);
        req.getRequestDispatcher("/views/post.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int postId = Integer.parseInt(req.getPathInfo().substring(1));
        String commentText = req.getParameter("commentText");
        User user = SessionManager.getUserFromSession(req);

        Comment comment = new Comment();
        comment.setPostId(postId);
        comment.setUserId(user.getId());
        comment.setText(commentText);
        comment.setUsername(user.getUsername());

        // Добавление комментария в базу данных
        CommentDAO.addComment(comment);
        resp.sendRedirect("/petbook/post/" + postId);

    }
}
