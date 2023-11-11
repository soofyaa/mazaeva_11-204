package ru.itis.services;

import ru.itis.dao.PhotoDAO;

public class PhotoService {
    public static void deletePhotoById(int fileId) {
        PhotoDAO.deletePhotoById(fileId);
    }
}
