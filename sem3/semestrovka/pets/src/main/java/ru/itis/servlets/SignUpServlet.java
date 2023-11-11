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
        boolean isAllCorrect = true;
        String username = req.getParameter("username");
        HttpSession session = req.getSession();
        isAllCorrect = SignUpService.registerUser(req, resp);

        if (isAllCorrect) {
            session.setAttribute("username", username);
            resp.sendRedirect(req.getContextPath() + "/main/photos");
        } else {
            req.setAttribute("errorMessage", "The username or email you entered is exist.");
            req.getRequestDispatcher("/views/sign-up.jsp").forward(req, resp);
        }
    }
}