package ru.itis.utils;

import lombok.SneakyThrows;
import ru.itis.dao.PhotoDAO;
import ru.itis.model.Photo;

import java.util.Base64;

public class Helper {

    @SneakyThrows
    public static String getPhotoData(int avatarId) {
        if (avatarId != -1) {
            Photo file = PhotoDAO.findPhotoById(avatarId);
            if (file != null) {
                return Base64.getEncoder().encodeToString(file.getData());
            }
        }
        return null;
    }
}
