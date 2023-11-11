package ru.itis.services;

import lombok.SneakyThrows;
import ru.itis.dao.PetDAO;
import ru.itis.dao.PhotoDAO;
import ru.itis.dao.UserDAO;
import ru.itis.model.Photo;
import ru.itis.model.User;
import ru.itis.utils.SessionManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class PhotoUploadService {

    @SneakyThrows
    public static void processPhotoUpload(HttpServletRequest request) {
        String cameFromUser = request.getParameter("cameFromUser");
        String avatarParam = request.getParameter("avatar");

        User user = SessionManager.getUserFromSession(request);
        int userId = user.getId();

        Part part = request.getPart("photo");
        InputStream photoContent = part.getInputStream();

        byte[] photoBytes = readInputStream(photoContent);

        Photo photo = new Photo();
        photo.setData(photoBytes);
        photo.setUserId(userId);

        if (cameFromUser == null) {
            int petId = (int) SessionManager.getAttribute(request, "petId");
            SessionManager.setAttribute(request, "petId", petId);
            photo.setPetId(petId);
        }

        int photoId = PhotoDAO.savePhoto(photo);

        if (avatarParam != null && avatarParam.equals("true")) {
            if (cameFromUser != null) {
                UserDAO.updateAvatarId(user.getUsername(), photoId);
            } else {
                PetDAO.updateAvatarId(photo.getPetId(), photoId);
            }
        }
    }

    @SneakyThrows
    private static byte[] readInputStream(InputStream inputStream) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int bytesRead;

        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }
        return outputStream.toByteArray();
    }
}
