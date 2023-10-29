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

@WebServlet("/updatePet")
public class UpdatePetServlet extends HttpServlet {

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
            req.getRequestDispatcher("/updatePet.jsp").forward(req, resp);
        }
    }

    @Override
    @SneakyThrows
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String ownerName = (String) session.getAttribute("name");
        String petNameToUpdate = req.getParameter("petNameToUpdate");

        String[] parameters = new String[5];
        parameters[0] = req.getParameter("updatedPetName");
        parameters[1] = req.getParameter("updatedType");
        parameters[2] = req.getParameter("updatedAge");
        parameters[3] = req.getParameter("updatedSex");
        parameters[4] = req.getParameter("updatedColor");

        Connection connection = ConnectionContainer.getConnection();
        Actions.updatePet(connection, ownerName, petNameToUpdate, parameters);
        connection.close();
        String path = "/servlets/getPets?user=" + ownerName;
        resp.sendRedirect(path);
    }
}