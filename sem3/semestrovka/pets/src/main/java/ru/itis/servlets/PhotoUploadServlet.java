package ru.itis.servlets;

import ru.itis.services.PhotoUploadService;
import ru.itis.utils.SessionManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/photo-upload")
@MultipartConfig
public class PhotoUploadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/photo-upload-page.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PhotoUploadService.processPhotoUpload(request);
        String cameFromUser = request.getParameter("cameFromUser");
        if (cameFromUser == null) {
            int petId = (Integer) SessionManager.getAttribute(request, "petId");
            response.sendRedirect(request.getContextPath() + "/pet/" + petId);
        } else {
            response.sendRedirect(request.getContextPath() + "/main/photos");
        }
    }
}

