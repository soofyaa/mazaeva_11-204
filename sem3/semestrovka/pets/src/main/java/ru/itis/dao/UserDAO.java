package ru.itis.dao;

import lombok.SneakyThrows;
import ru.itis.model.User;
import ru.itis.utils.ConnectionContainer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    final private static String SQL_ADD_USER = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";
    final private static String SQL_FIND_USER_BY_USERNAME = "SELECT * FROM users WHERE username = ?";
    final private static String SQL_FIND_USER_BY_EMAIL = "SELECT * FROM users WHERE email = ?";
    final private static String SQL_UPDATE_USER_DESCRIPTION = "UPDATE users SET description = ? WHERE username = ?";
    final private static String SQL_UPDATE_USER_AVATAR_ID = "UPDATE users SET avatar_id = ? WHERE username = ?";
    final private static String SQL_FIND_USERNAME_BY_ID = "SELECT * FROM users WHERE id = ?";


    @SneakyThrows
    public static void addUser(User user) {
        Connection connection = ConnectionContainer.getConnection();
        PreparedStatement statement = connection.prepareStatement(SQL_ADD_USER);
        statement.setString(1, user.getUsername());
        statement.setString(2, user.getPassword());
        statement.setString(3, user.getEmail());
        statement.executeUpdate();
    }
    @SneakyThrows
    public static boolean isUsernameExists(String username) {
        return isParameterExists(username, SQL_FIND_USER_BY_USERNAME);
    }
    @SneakyThrows
    public static boolean isEmailExists(String email) {
        return isParameterExists(email, SQL_FIND_USER_BY_EMAIL);
    }
    @SneakyThrows
    private static boolean isParameterExists(String parameter, String sql) throws SQLException {
        Connection connection = ConnectionContainer.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, parameter);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            int count = resultSet.getInt(1);
            return count > 0;
        }
        return false;
    }

    @SneakyThrows
    public static User findUserByUsername(String username) {
        Connection connection = ConnectionContainer.getConnection();
        PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_BY_USERNAME);
        statement.setString(1, username);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getInt("id"));
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            user.setEmail(resultSet.getString("email"));
            user.setDescription(resultSet.getString("description"));
            user.setAvatarId(resultSet.getInt("avatar_id"));
            return user;
        }
        return null;
    }

    @SneakyThrows
    public static void updateDescription(String username, String description) {
        Connection connection = ConnectionContainer.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_USER_DESCRIPTION);
        preparedStatement.setString(1, description);
        preparedStatement.setString(2, username);
        preparedStatement.executeUpdate();
    }

    @SneakyThrows
    public static void updateAvatarId(String username, int avatarId) {
        Connection connection = ConnectionContainer.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_USER_AVATAR_ID);
        preparedStatement.setInt(1, avatarId);
        preparedStatement.setString(2, username);
        preparedStatement.executeUpdate();
    }

    @SneakyThrows
    public static String findUserById(int userId) {
        Connection connection = ConnectionContainer.getConnection();
        PreparedStatement statement = connection.prepareStatement(SQL_FIND_USERNAME_BY_ID);
        statement.setInt(1, userId);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getString("username");
        }
        return null;
    }
}