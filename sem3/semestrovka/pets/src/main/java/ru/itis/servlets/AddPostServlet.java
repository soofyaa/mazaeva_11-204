package ru.itis.servlets;

import ru.itis.dao.PostDAO;
import ru.itis.model.User;
import ru.itis.utils.SessionManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/add-post")
public class AddPostServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/add-post.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = SessionManager.getUserFromSession(req);
        String title = req.getParameter("title");
        String postText = req.getParameter("postText");
        int postId = PostDAO.addPost(user.getId(), title, postText);

        resp.sendRedirect("/petbook/post/" + postId);
    }
}
