package ru.itis.servlets;

import ru.itis.dao.PostDAO;
import ru.itis.model.Post;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/feed-posts")
public class FeedPostsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Post> posts = PostDAO.getAllPostsWithUsernames();
        req.setAttribute("posts", posts);
        req.getRequestDispatcher("/views/feed-posts.jsp").forward(req, resp);
    }
}
