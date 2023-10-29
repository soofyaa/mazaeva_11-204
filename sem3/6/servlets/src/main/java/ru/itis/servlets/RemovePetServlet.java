package ru.itis.servlets;

import lombok.SneakyThrows;
import ru.itis.utils.Actions;
import ru.itis.utils.ConnectionContainer;
import ru.itis.model.Pet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/removePet")
public class RemovePetServlet extends HttpServlet {
    @Override
    @SneakyThrows
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String ownerName = (String) session.getAttribute("name");
        if (ownerName == null) {
            resp.sendRedirect("/servlets/name");
        } else {
            Connection connection = ConnectionContainer.getConnection();
            List<Pet> pets = Actions.getUserPets(connection, ownerName);
            connection.close();
            req.setAttribute("pets", pets);
            req.getRequestDispatcher("/removePet.jsp").forward(req, resp);
        }
    }

    @Override
    @SneakyThrows
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String ownerName = (String) session.getAttribute("name");
        String petNameToRemove = req.getParameter("petNameToRemove");
        Connection connection = ConnectionContainer.getConnection();
        Actions.removePet(connection, ownerName, petNameToRemove);
        connection.close();
        String path = "/servlets/getPets?user=" + ownerName;
        resp.sendRedirect(path);
    }
}
