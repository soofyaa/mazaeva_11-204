package ru.itis.servlets;

import ru.itis.dao.FileDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete-photo")
public class DeletePhotoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int photoId = Integer.parseInt(req.getParameter("photoId"));
        FileDAO.deleteFileById(photoId);
        resp.sendRedirect("/petbook/feed-photos");
    }
}
