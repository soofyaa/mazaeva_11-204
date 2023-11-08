package ru.itis.servlets;

import ru.itis.dao.PetDAO;
import ru.itis.model.Pet;
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

@WebServlet("/pet/*")
public class PetServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        int petId = Integer.parseInt(req.getPathInfo().substring(1));

        Pet pet = PetDAO.findPetById(petId);
        req.setAttribute("pet", pet);
        if (pet != null) {
            int avatarId = pet.getAvatarId();
            String avatarData = ServletHelper.getFileData(avatarId);
            req.setAttribute("avatarData", avatarData);
        }

        List<String> petPhotos = new ArrayList<>();

        if (pet != null) {
            for (Integer photoId : pet.getPicsId()) {
                String photoData = ServletHelper.getFileData(photoId);
                petPhotos.add(photoData);
            }
        }

        session.setAttribute("petId", petId);

        req.setAttribute("petPhotos", petPhotos);

        req.getRequestDispatcher("/views/pet.jsp").forward(req, resp);
    }
}
