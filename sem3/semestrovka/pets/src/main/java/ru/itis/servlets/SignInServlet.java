package ru.itis.servlets;

import ru.itis.services.SignInService;
import ru.itis.utils.SessionManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/sign-in")
public class SignInServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/sign-in.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        boolean isAuthenticated = SignInService.authenticateUser(username, password);

        if (isAuthenticated) {
            SessionManager.setAttribute(request, "username", username);
            response.sendRedirect(request.getContextPath() + "/main/photos");
        } else {
            request.setAttribute("errorMessage", "The username or password you entered is incorrect.");
            request.getRequestDispatcher("/views/sign-in.jsp").forward(request, response);
        }
    }
}
