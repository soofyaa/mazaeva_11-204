package ru.itis.utils;

import ru.itis.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionManager {
    public static User getUserFromSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        return user;
    }

    public static void setAttribute(HttpServletRequest request, String attributeName, Object attributeValue) {
        HttpSession session = request.getSession();
        session.setAttribute(attributeName, attributeValue);
    }

    public static Object getAttribute(HttpServletRequest request, String attributeName) {
        HttpSession session = request.getSession();
        return session.getAttribute(attributeName);
    }
}
