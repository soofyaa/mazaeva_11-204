package ru.itis.servlets;

import ru.itis.model.Pet;
import ru.itis.services.PetService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/pet-settings")
public class PetSettingsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int petId = Integer.parseInt(request.getParameter("petId"));
        Pet pet = PetService.getPetById(petId);
        request.setAttribute("pet", pet);
        request.getRequestDispatcher("/views/pet-settings-page.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int petId = Integer.parseInt(request.getParameter("petId"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        PetService.updatePetInfo(petId, name, description);

        String username = request.getParameter("username");
        PetService.linkPetToUser(username, petId);

        response.sendRedirect(request.getContextPath() + "/pet/" + petId);
    }
}
