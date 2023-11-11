package ru.itis.servlets;

import ru.itis.services.PetService;

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
        req.getRequestDispatcher("/views/add-pet-page.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String petName = req.getParameter("petName");
        String petDescription = req.getParameter("petDescription");
        int petId = PetService.addPet(petName, petDescription, req);
        resp.sendRedirect(req.getContextPath() + "/pet/" + petId);
    }
}
