package ru.itis.services;

import org.mindrot.jbcrypt.BCrypt;
import ru.itis.dao.UserDAO;
import ru.itis.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
сделать проверку на корректную почту с помощью js
 */

public class SignUpService {
    public static boolean registerUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String plainPassword = request.getParameter("password");
        String email = request.getParameter("email");

        if (UserDAO.isUsernameExists(username)) {
            request.setAttribute("errorMessage", "Пользователь с таким именем уже существует");
            return false;
        }

        if (UserDAO.isEmailExists(email)) {
            request.setAttribute("errorMessage", "Пользователь с такой почтой уже существует");
            return false;
        }

        // Хеширование пароля
        String hashedPassword = BCrypt.hashpw(plainPassword, BCrypt.gensalt());

        User user = new User();
        user.setUsername(username);
        user.setPassword(hashedPassword);
        user.setEmail(email);
        UserDAO.addUser(user);

        return true;
    }
}

