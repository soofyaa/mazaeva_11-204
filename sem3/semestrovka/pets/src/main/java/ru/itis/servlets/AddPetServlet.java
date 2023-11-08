package ru.itis.servlets;

import ru.itis.dao.PetDAO;
import ru.itis.dao.UserPetDAO;
import ru.itis.utils.SessionManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/add-pet")
public class AddPetServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/add-pet.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String petName = req.getParameter("petName");
        String petDescription = req.getParameter("petDescription");
        int petId = PetDAO.addPet(petName,petDescription);
        int userId = SessionManager.getUserFromSession(req).getId();
        if (petId > 0 && userId > 0) {
            UserPetDAO.linkPetToUser(petId, userId);
        }

        resp.sendRedirect("/petbook/pet/" + petId);
    }
}
