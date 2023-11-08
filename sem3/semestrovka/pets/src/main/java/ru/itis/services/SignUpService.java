package ru.itis.services;

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
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        // Проверка уникальности имени пользователя и email
        if (UserDAO.isUsernameExists(username)) {
            request.setAttribute("errorMessage", "Пользователь с таким именем уже существует");
            return false; // Флаг shouldRedirect устанавливается в false
        }

        if (UserDAO.isEmailExists(email)) {
            request.setAttribute("errorMessage", "Пользователь с такой почтой уже существует");
            return false; // Флаг shouldRedirect устанавливается в false
        }

        // Проверка соответствия email стандарту
        if (!email.matches("^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$")) {
            request.setAttribute("errorMessage", "Некорректный формат email");
            return false; // Флаг shouldRedirect устанавливается в false
        }

        // Добавление пользователя в базу данных
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        UserDAO.addUser(user);

        return true; // Флаг shouldRedirect остается true после успешной регистрации
    }
}
