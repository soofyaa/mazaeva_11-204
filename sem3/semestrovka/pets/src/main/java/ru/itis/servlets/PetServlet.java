package ru.itis.servlets;

import ru.itis.model.Pet;
import ru.itis.services.PetService;
import ru.itis.utils.SessionManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/pet/*")
public class PetServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Pet pet = PetService.getPetDetails(req);

        SessionManager.setAttribute(req, "petId", pet.getId());

        req.setAttribute("pet", pet);
        req.setAttribute("isPetOwner", req.getAttribute("isPetOwner"));
        req.getRequestDispatcher("/views/pet-page.jsp").forward(req, resp);
    }
}
