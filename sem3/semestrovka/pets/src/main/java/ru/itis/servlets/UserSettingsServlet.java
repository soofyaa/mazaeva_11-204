package ru.itis.servlets;

import ru.itis.dao.UserDAO;
import ru.itis.model.User;
import ru.itis.utils.SessionManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/user/settings")
public class UserSettingsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = SessionManager.getUserFromSession(request);
        request.setAttribute("user", user);
        request.getRequestDispatcher("/views/user-settings.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        String description = request.getParameter("description");
        // Сохраните новое описание в базе данных
        UserDAO.updateDescription(user.getUsername(), description);

        response.sendRedirect("/petbook/user");
    }
}
