package ru.itis.servlets;

import ru.itis.dao.PostDAO;
import ru.itis.model.Pet;
import ru.itis.model.Post;
import ru.itis.model.User;
import ru.itis.services.MainPageService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/main/posts")
public class PostsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        MainPageService.setupUserAttributes(req, session);

        List<Pet> userPets = MainPageService.getUserPets((User) session.getAttribute("user"));
        req.setAttribute("userPets", userPets);

        List<Post> posts = PostDAO.getAllPostsWithUsernames();
        req.setAttribute("posts", posts);
        req.getRequestDispatcher("/views/main-page-posts.jsp").forward(req, resp);
    }
}