package ru.itis.dao;

import lombok.SneakyThrows;
import ru.itis.model.File;
import ru.itis.utils.ConnectionContainer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FileDAO {
    final private static String SQL_ADD_FILE = "INSERT INTO files (data, user_id, pet_id) VALUES (?, ?, ?)";
    final private static String SQL_FIND_FILE_BY_ID = "SELECT data FROM files WHERE id = ?";
    final private static String SQL_FIND_PET_PICS = "SELECT id FROM files WHERE pet_id = ?";


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
}
