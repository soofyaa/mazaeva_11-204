package ru.itis.services;

import org.mindrot.jbcrypt.BCrypt;
import ru.itis.dao.UserDAO;
import ru.itis.model.User;

public class SignInService {
    public static boolean authenticateUser(String username, String password) {
        User user = UserDAO.findUserByUsername(username);

        return user != null && BCrypt.checkpw(password, user.getPassword());
    }
}

