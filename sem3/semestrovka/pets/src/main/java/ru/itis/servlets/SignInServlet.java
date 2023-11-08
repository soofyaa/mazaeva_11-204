package ru.itis.servlets;

import ru.itis.services.SignInService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            response.sendRedirect("/petbook/user");
        } else {
            request.setAttribute("errorMessage", "Неверное имя пользователя или пароль");
            request.getRequestDispatcher("/views/sign-in.jsp").forward(request, response);
        }
    }
}
