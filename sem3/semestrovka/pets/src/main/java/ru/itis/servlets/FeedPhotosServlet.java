package ru.itis.servlets;

import ru.itis.dao.FileDAO;
import ru.itis.model.Photo;
import ru.itis.utils.SessionManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/feed-photos")
public class FeedPhotosServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Photo> photos = FileDAO.getAllPhotosWithUsernamesAndFileId();
        req.setAttribute("isAdmin", SessionManager.getAttribute(req,"isAdmin"));
        req.setAttribute("photos", photos);
        req.getRequestDispatcher("/views/feed-photos.jsp").forward(req, resp);
    }
}
