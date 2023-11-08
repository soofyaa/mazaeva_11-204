package ru.itis.utils;

import lombok.SneakyThrows;
import ru.itis.dao.FileDAO;
import ru.itis.model.File;

import java.util.Base64;

public class ServletHelper {

    @SneakyThrows
    public static String getFileData(int avatarId) {
        if (avatarId != -1) {
            File file = FileDAO.findFileById(avatarId);
            if (file != null) {
                return Base64.getEncoder().encodeToString(file.getData());
            }
        }
        return null;
    }
}
