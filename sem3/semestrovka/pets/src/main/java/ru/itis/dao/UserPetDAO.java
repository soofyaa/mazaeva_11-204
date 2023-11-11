package ru.itis.dao;

import lombok.SneakyThrows;
import ru.itis.utils.ConnectionContainer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserPetDAO {
    final private static String SQL_LINKED_PET_TO_USER = "INSERT INTO user_pet (user_id, pet_id) VALUES (?, ?)";
    final private static String SQL_FIND_PET_IDS_BY_USER_ID = "SELECT pet_id FROM user_pet WHERE user_id = ?";
    final private static String SQL_FIND_USER_IDS_BY_PET_ID = "SELECT user_id FROM user_pet WHERE pet_id = ?";
    final private static String SQL_USER_IS_PET_OWNER = "SELECT * FROM user_pet WHERE user_id = ? AND pet_id = ?";

    @SneakyThrows
    public static void linkPetToUser(int petId, int userId) {
        Connection connection = ConnectionContainer.getConnection();
        PreparedStatement statement = connection.prepareStatement(SQL_LINKED_PET_TO_USER);
        statement.setInt(1, userId);
        statement.setInt(2, petId);
        statement.executeUpdate();
    }

    @SneakyThrows
    public static List<Integer> findPetIdsByUserId(int userId) {
        List<Integer> petIds = new ArrayList<>();
        Connection connection = ConnectionContainer.getConnection();
        PreparedStatement statement = connection.prepareStatement(SQL_FIND_PET_IDS_BY_USER_ID);
        statement.setInt(1, userId);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int petId = resultSet.getInt("pet_id");
            petIds.add(petId);
        }
        return petIds;
    }

    @SneakyThrows
    public static List<Integer> findUserIdsByPetId(int petId) {
        List<Integer> userIds = new ArrayList<>();
        Connection connection = ConnectionContainer.getConnection();
        PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_IDS_BY_PET_ID);
        statement.setInt(1, petId);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int userId = resultSet.getInt("user_id");
            userIds.add(userId);
        }
        return userIds;
    }

    @SneakyThrows
    public static boolean isPetOwner(int userId, int petId) {
        Connection connection = ConnectionContainer.getConnection();
        PreparedStatement statement = connection.prepareStatement(SQL_USER_IS_PET_OWNER);
        statement.setInt(1, userId);
        statement.setInt(2, petId);
        ResultSet resultSet = statement.executeQuery();
        return resultSet.next();
    }
}
