package ru.itis.servlets;

import ru.itis.dao.PetDAO;
import ru.itis.model.Pet;
import ru.itis.utils.SessionManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/pet/settings")
public class PetSettingsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int petId = (int) SessionManager.getAttribute(request,"petId");
        Pet pet = PetDAO.findPetById(petId);
        request.setAttribute("pet", pet);
        request.getRequestDispatcher("/views/pet-settings.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int petId = (int) SessionManager.getAttribute(request,"petId");

        String name = request.getParameter("name");

        String description = request.getParameter("description");

        PetDAO.updatePetInfo(petId, name, description);
        response.sendRedirect("/petbook/pet/" + petId);
    }
}
