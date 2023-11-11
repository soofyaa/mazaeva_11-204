package ru.itis.servlets;

import ru.itis.dao.PhotoDAO;
import ru.itis.model.Pet;
import ru.itis.model.Photo;
import ru.itis.model.User;
import ru.itis.services.MainPageService;
import ru.itis.utils.SessionManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/main/photos")
public class PhotosServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        MainPageService.setupUserAttributes(req, session);

        List<Pet> userPets = MainPageService.getUserPets((User) session.getAttribute("user"));
        req.setAttribute("userPets", userPets);

        List<Photo> photos = PhotoDAO.getAllPhotosWithUsernamesAndPhotoId();
        req.setAttribute("isAdmin", SessionManager.getAttribute(req, "isAdmin"));
        req.setAttribute("photos", photos);
        req.getRequestDispatcher("/views/main-page-photos.jsp").forward(req, resp);
    }
}
