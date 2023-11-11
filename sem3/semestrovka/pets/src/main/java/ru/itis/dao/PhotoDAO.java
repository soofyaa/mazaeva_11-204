package ru.itis.dao;

import lombok.SneakyThrows;
import ru.itis.model.Photo;
import ru.itis.utils.ConnectionContainer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class PhotoDAO {
    final private static String SQL_ADD_PHOTO = "INSERT INTO photos (data, user_id, pet_id) VALUES (?, ?, ?)";
    final private static String SQL_FIND_PHOTOS_BY_ID = "SELECT data FROM photos WHERE id = ?";
    final private static String SQL_FIND_PET_PHOTO_IDS = "SELECT id FROM photos WHERE pet_id = ?";
    final private static String SQL_DELETE_PHOTO = "DELETE FROM photos WHERE id = ?";
    final private static String SQL_GET_ALL_PHOTOS_WITH_USERNAMES =
            """
            SELECT
                (SELECT name FROM pets WHERE pets.id = photos.pet_id) AS pet_name,
                photos.data,
                photos.pet_id AS pet_id,
                photos.id AS photo_id
            FROM photos;
            """;


    @SneakyThrows
    public static int savePhoto(Photo photo) {
        Connection connection = ConnectionContainer.getConnection();
        PreparedStatement statement = connection.prepareStatement(SQL_ADD_PHOTO, Statement.RETURN_GENERATED_KEYS);

        statement.setBytes(1, photo.getData());
        statement.setInt(2, photo.getUserId());
        statement.setInt(3, photo.getPetId());

        int rowsAffected = statement.executeUpdate();

        if (rowsAffected > 0) {
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1); // Возвращаем ID файла
            }
        }
        return -1;
    }

    @SneakyThrows
    public static Photo findPhotoById(int photoId) {
        Connection connection = ConnectionContainer.getConnection();
        PreparedStatement statement = connection.prepareStatement(SQL_FIND_PHOTOS_BY_ID);
        statement.setInt(1, photoId);

        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            byte[] data = resultSet.getBytes("data");

            Photo photo = new Photo();
            photo.setId(photoId);
            photo.setData(data);

            return photo;
        }
        return null;
    }

    @SneakyThrows
    static List<Integer> getPetPhotoIdsByPetId(int petId) {
        List<Integer> photoIds = new ArrayList<>();
        Connection connection = ConnectionContainer.getConnection();
        PreparedStatement statement = connection.prepareStatement(SQL_FIND_PET_PHOTO_IDS);
        statement.setInt(1, petId);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            int photoId = resultSet.getInt("id");
            photoIds.add(photoId);
        }
        return photoIds;
    }

    @SneakyThrows
    public static List<Photo> getAllPhotosWithUsernamesAndPhotoId() {
        List<Photo> photos = new ArrayList<>();

        Connection connection = ConnectionContainer.getConnection();
        PreparedStatement statement = connection.prepareStatement(SQL_GET_ALL_PHOTOS_WITH_USERNAMES);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            Photo photo = Photo.builder().
                    petName(resultSet.getString("pet_name")).
                    photoData(Base64.getEncoder().encodeToString(resultSet.getBytes("data"))).
                    petId(resultSet.getInt("pet_id")).
                    id(resultSet.getInt("photo_id")).build();
            if (photo.getPetName() == null) {
                continue;
            }
            photos.add(photo);
        }
        return photos;
    }

    @SneakyThrows
    public static void deletePhotoById(int photoId) {
        Connection connection = ConnectionContainer.getConnection();
        PreparedStatement statement = connection.prepareStatement(SQL_DELETE_PHOTO);
        statement.setInt(1, photoId);
        statement.executeUpdate();
    }
}
