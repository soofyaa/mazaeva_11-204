package ru.itis.servlets;

import ru.itis.model.User;
import ru.itis.services.UserService;
import ru.itis.utils.SessionManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/settings")
public class UserSettingsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = SessionManager.getUserFromSession(request);
        request.setAttribute("user", user);
        request.getRequestDispatcher("/views/user-settings-page.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = SessionManager.getUserFromSession(request);

        String description = request.getParameter("description");

        UserService.updateUserDescription(user.getUsername(), description);

        String fromPhotos = request.getParameter("fromPhotos");
        if (fromPhotos != null && fromPhotos.equals("true")) {
            response.sendRedirect(request.getContextPath() + "/main/photos");
        } else {
            response.sendRedirect(request.getContextPath() + "/main/posts");
        }
    }
}
