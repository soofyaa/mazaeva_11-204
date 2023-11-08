package ru.itis.servlets;

import lombok.SneakyThrows;
import ru.itis.dao.FileDAO;
import ru.itis.dao.UserDAO;
import ru.itis.model.File;
import ru.itis.model.User;
import ru.itis.utils.SessionManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@WebServlet("/file-upload")
@MultipartConfig
public class FileUploadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/file-upload.jsp").forward(req, resp);
    }

    @SneakyThrows()
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String cameFromUser = request.getParameter("cameFromUser");
        String avatarParam = request.getParameter("avatar");

        User user = SessionManager.getUserFromSession(request);

        int userId = user.getId();

        Part filePart = request.getPart("file");
        InputStream fileContent = filePart.getInputStream();

        byte[] fileBytes = readInputStream(fileContent);

        // Создайте объект File для сохранения информации о файле
        File file = new File();
        file.setData(fileBytes);
        file.setUserId(userId);
        if (cameFromUser == null) {
            int petId = (int) SessionManager.getAttribute(request,"petId");
            SessionManager.removeAttribute(request, "petId");
            file.setPetId(petId);
        }

        int fileId = FileDAO.saveFile(file);

        if (avatarParam != null && avatarParam.equals("true")) {
            UserDAO.updateAvatarId(user.getUsername(), fileId);
        }

        if (cameFromUser == null) {
            response.sendRedirect("/petbook/pet/" + file.getPetId());
        } else {
            response.sendRedirect("/petbook/user");
        }
    }

    private byte[] readInputStream(InputStream inputStream) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int bytesRead;

        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }
        return outputStream.toByteArray();
    }
}
