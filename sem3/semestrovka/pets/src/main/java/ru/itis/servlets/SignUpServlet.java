package ru.itis.servlets;

import ru.itis.services.SignUpService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/sign-up")
public class SignUpServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/sign-up.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean isAllCorrect = true; // Изначально устанавливаем флаг в true
        String username = req.getParameter("username");
        HttpSession session = req.getSession();

        session.setAttribute("username", username);

        isAllCorrect = SignUpService.registerUser(req, resp);

        if (isAllCorrect) {
            resp.sendRedirect("/petbook/user");
        } else {
            req.setAttribute("errorMessage", "Измените имя пользователя или введите корректный адрес почты.");
            req.getRequestDispatcher("/views/sign-up.jsp").forward(req, resp);
        }
    }
}