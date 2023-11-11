package ru.itis.servlets;

import ru.itis.dao.PostDAO;
import ru.itis.dao.UserDAO;
import ru.itis.model.Post;
import ru.itis.model.User;
import ru.itis.services.CommentService;
import ru.itis.services.PostService;
import ru.itis.utils.EmailSender;
import ru.itis.utils.SessionManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/post/*")
public class PostServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Post post = PostService.getPostDetails(req);

        req.setAttribute("isAdmin", SessionManager.getAttribute(req, "isAdmin"));
        req.setAttribute("post", post);
        req.setAttribute("selectedFeedType", "posts");
        req.getRequestDispatcher("/views/post-page.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int postId = Integer.parseInt(req.getPathInfo().substring(1));
        String commentText = req.getParameter("commentText");

        CommentService.addComment(req, postId, commentText);

        int userId = PostDAO.findUserIdByPostId(postId);

        User user = UserDAO.findUserById(userId);



        String userEmail = user.getEmail();
        System.out.println(userEmail);

        EmailSender emailSender = new EmailSender(userEmail, "New Comment Notification", "Someone commented on your post!");

        Thread emailThread = new Thread(emailSender);
        emailThread.start();

        resp.sendRedirect(req.getContextPath() + "/post/" + postId);
    }

}
