package ru.itis.dao;

import lombok.SneakyThrows;
import ru.itis.model.File;
import ru.itis.model.Photo;
import ru.itis.utils.ConnectionContainer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class FileDAO {
    final private static String SQL_ADD_FILE = "INSERT INTO files (data, user_id, pet_id) VALUES (?, ?, ?)";
    final private static String SQL_FIND_FILE_BY_ID = "SELECT data FROM files WHERE id = ?";
    final private static String SQL_FIND_PET_PICS = "SELECT id FROM files WHERE pet_id = ?";
    final private static String SQL_DELETE_FILE = "DELETE FROM files WHERE id = ?";
    final private static String SQL_GET_ALL_PHOTOS_WITH_USERNAMES_AND_FILEID =
            """
            SELECT
                (SELECT username FROM users WHERE users.id = files.user_id) AS username,
                files.data,
                files.id AS file_id  -- Added to get file_id
            FROM files;
            """;


    @SneakyThrows
    public static int saveFile(File file) {
        Connection connection = ConnectionContainer.getConnection();
        PreparedStatement statement = connection.prepareStatement(SQL_ADD_FILE, Statement.RETURN_GENERATED_KEYS);

        statement.setBytes(1, file.getData());
        statement.setInt(2, file.getUserId());
        statement.setInt(3, file.getPetId());

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
    public static File findFileById(int fileId) {
        Connection connection = ConnectionContainer.getConnection();
        PreparedStatement statement = connection.prepareStatement(SQL_FIND_FILE_BY_ID);
        statement.setInt(1, fileId);

        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            byte[] data = resultSet.getBytes("data");

            File file = new File();
            file.setId(fileId);
            file.setData(data);

            return file;
        }
        return null;
    }

    @SneakyThrows
    static List<Integer> getPetPicsById(int petId) {
        List<Integer> picsId = new ArrayList<>();
        Connection connection = ConnectionContainer.getConnection();
        PreparedStatement statement = connection.prepareStatement(SQL_FIND_PET_PICS);
        statement.setInt(1, petId);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            int picId = resultSet.getInt("id");
            picsId.add(picId);
        }
        return picsId;
    }

    @SneakyThrows
    public static List<Photo> getAllPhotosWithUsernamesAndFileId() {
        List<Photo> photos = new ArrayList<>();

        Connection connection = ConnectionContainer.getConnection();
        PreparedStatement statement = connection.prepareStatement(SQL_GET_ALL_PHOTOS_WITH_USERNAMES_AND_FILEID);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            Photo photo = new Photo();
            String username = resultSet.getString("username");
            String photoData = Base64.getEncoder().encodeToString(resultSet.getBytes("data"));
            int fileId = resultSet.getInt("file_id");  // Added to get file_id
            photo.setUsername(username);
            photo.setPhotoData(photoData);
            photo.setId(fileId);  // Added to set file_id
            photos.add(photo);
        }

        return photos;
    }

    @SneakyThrows
    public static void deleteFileById(int fileId) {
        Connection connection = ConnectionContainer.getConnection();

        PreparedStatement statement = connection.prepareStatement(SQL_DELETE_FILE);
        statement.setInt(1, fileId);

        statement.executeUpdate();
    }
}
