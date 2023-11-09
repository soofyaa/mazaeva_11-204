package ru.itis.dao;

import lombok.SneakyThrows;
import ru.itis.model.Pet;
import ru.itis.utils.ConnectionContainer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class PetDAO {

    final private static String SQL_ADD_PET = "INSERT INTO pets (name, description) VALUES (?, ?)";
    final private static String SQL_FIND_PET_BY_ID = "SELECT * FROM pets WHERE id = ?";
    final private static String SQL_UPDATE_PET_INFO = "UPDATE pets SET name = ?, description = ? WHERE id = ?";
    final private static String SQL_UPDATE_PET_AVATAR_ID = "UPDATE pets SET avatar_id = ? WHERE id = ?";


    @SneakyThrows
    public static int addPet(String petName, String petDescription) {
        int petId = -1; // Идентификатор питомца
        Connection connection = ConnectionContainer.getConnection();
        PreparedStatement statement = connection.prepareStatement(SQL_ADD_PET, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, petName);
        statement.setString(2, petDescription);
        int affectedRows = statement.executeUpdate();

        if (affectedRows > 0) {
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                petId = resultSet.getInt(1);
            }
        }
        return petId;
    }

    @SneakyThrows
    public static Pet findPetById(int petId) {
        Connection connection = ConnectionContainer.getConnection();
        PreparedStatement statement = connection.prepareStatement(SQL_FIND_PET_BY_ID);
        statement.setInt(1, petId);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            String petName = resultSet.getString("name");
            String petDescription = resultSet.getString("description");
            int avatarId = resultSet.getInt("avatar_id");
            List<Integer> picsId = FileDAO.getPetPicsById(petId); // Получаем список picsId

            Pet pet = new Pet();
            pet.setId(petId);
            pet.setName(petName);
            pet.setDescription(petDescription);
            pet.setAvatarId(avatarId);
            pet.setPicsId(picsId);

            return pet;
        }

        return null;
    }

    @SneakyThrows
    public static void updatePetInfo(int petId, String newName, String newDescription) {
        Connection connection = ConnectionContainer.getConnection();
        PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_PET_INFO);
        statement.setString(1, newName);
        statement.setString(2, newDescription);
        statement.setInt(3, petId);

        statement.executeUpdate();
    }

    @SneakyThrows
    public static void updateAvatarId(int petId, int avatarId) {
        Connection connection = ConnectionContainer.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_PET_AVATAR_ID);
        preparedStatement.setInt(1, avatarId);
        preparedStatement.setInt(2, petId);
        preparedStatement.executeUpdate();
    }
}