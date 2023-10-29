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
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/getPets")
public class GetPetsServlet extends HttpServlet {

    @Override
    @SneakyThrows
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Connection connection = ConnectionContainer.getConnection();
        List<Pet> pets;
        String username = req.getParameter("user");

        if (username != null) {
            pets = Actions.getUserPets(connection, username);
        } else {
            pets = Actions.getAllPets(connection);
        }

        connection.close();
        req.setAttribute("pets", pets);
        req.getRequestDispatcher("/getPets.jsp").forward(req, resp);
    }
}
