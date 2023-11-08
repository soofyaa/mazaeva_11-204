package ru.itis.servlets;

import ru.itis.dao.PetDAO;
import ru.itis.dao.UserDAO;
import ru.itis.dao.UserPetDAO;
import ru.itis.model.Pet;
import ru.itis.model.User;
import ru.itis.utils.ServletHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        User user = UserDAO.findUserByUsername(username);
        session.setAttribute("user", user);
        request.setAttribute("user", user);

        if (user != null) {
            int avatarId = user.getAvatarId();
            String avatarData = ServletHelper.getFileData(avatarId);
            request.setAttribute("avatarData", avatarData);
        }

        // Загрузите список идентификаторов питомцев пользователя из базы данных
        List<Integer> userPetIds = UserPetDAO.findPetIdsByUserId(user.getId());

        // Создайте список для хранения питомцев пользователя
        List<Pet> userPets = new ArrayList<>();

        // Переберите идентификаторы и найдите соответствующих питомцев
        for (Integer petId : userPetIds) {
            Pet pet = PetDAO.findPetById(petId);
            if (pet != null) {
                userPets.add(pet);
            }
        }

        request.setAttribute("userPets", userPets);

        request.getRequestDispatcher("/views/user.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        String logoutParam = request.getParameter("logout");
        if (logoutParam != null && logoutParam.equals("true")) {
            session.invalidate();
            response.sendRedirect("/petbook/sign-up");
        }
    }
}