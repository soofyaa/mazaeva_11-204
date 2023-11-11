package ru.itis.services;

import ru.itis.dao.UserDAO;

public class UserService {
    public static void updateUserDescription(String username, String description) {
        UserDAO.updateDescription(username, description);
    }
}
