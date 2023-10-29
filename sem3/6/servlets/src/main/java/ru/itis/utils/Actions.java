package ru.itis.utils;

import lombok.SneakyThrows;
import ru.itis.model.Pet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Actions {

    private final static String SQL_GET_ALL_PETS = "SELECT * FROM pets;";
    private final static String SQL_GET_USER_PETS = "SELECT * FROM pets WHERE owner_name = ?;";
    public final static String SQL_UPDATE_PET = "UPDATE pets SET pet_name = ?, type = ?, age = ?, sex = ?, color = ? WHERE owner_name = ? AND pet_name = ?;";
    public final static String SQL_GET_PET_BY_NAMES = "SELECT * FROM pets WHERE owner_name = ? AND pet_name = ?;";
    public final static String SQL_CREATE_PET = "INSERT INTO pets (owner_name, pet_name, type, age, sex, color) VALUES (?, ?, ?, ?, ?, ?)";
    private final static String SQL_DELETE_BY_NAMES = "DELETE FROM pets WHERE owner_name = ? AND pet_name = ?";

    @SneakyThrows
    public static List<Pet> getAllPets(Connection connection) {
        List<Pet> pets = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_ALL_PETS);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Pet pet = createPetFromResultSet(resultSet);
            pets.add(pet);
        }
        return pets;
    }

    @SneakyThrows
    public static List<Pet> getUserPets(Connection connection, String username) {
        List<Pet> pets = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_USER_PETS);
        preparedStatement.setString(1, username);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Pet pet = createPetFromResultSet(resultSet);
            pets.add(pet);
        }
        return pets;
    }

    @SneakyThrows
    public static void updatePet(Connection connection, String ownerName, String petName, String[] parameters) {
        Pet pet = getPetByNames(connection, ownerName, petName);
        if (pet != null) {
            PreparedStatement updatedStatement = connection.prepareStatement(SQL_UPDATE_PET);
            updatedStatement.setString(1, ((Objects.equals(parameters[0], "")) ? pet.getPetName() : parameters[0]));
            updatedStatement.setString(2, ((Objects.equals(parameters[1], "")) ? pet.getType() : parameters[1]));
            updatedStatement.setInt(3, ((Objects.equals(parameters[2], "")) ? pet.getAge() : Integer.parseInt(parameters[2])));
            updatedStatement.setString(4, ((Objects.equals(parameters[3], "")) ? pet.getSex() : parameters[3]));
            updatedStatement.setString(5, ((Objects.equals(parameters[4], "")) ? pet.getColor() : parameters[4]));
            updatedStatement.setString(6, ownerName);
            updatedStatement.setString(7, petName);
            updatedStatement.executeUpdate();
        }
    }

    @SneakyThrows
    public static void createPet(Connection connection, String[] parameters) {
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE_PET);
        preparedStatement.setString(1, parameters[0]);
        preparedStatement.setString(2, parameters[1]);
        preparedStatement.setString(3, parameters[2]);
        preparedStatement.setInt(4, Integer.parseInt(parameters[3]));
        preparedStatement.setString(5, parameters[4]);
        preparedStatement.setString(6, parameters[5]);
        preparedStatement.executeUpdate();
    }

    @SneakyThrows
    public static void removePet(Connection connection, String ownerName, String petName) {
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_BY_NAMES);
        preparedStatement.setString(1, ownerName);
        preparedStatement.setString(2, petName);
        preparedStatement.executeUpdate();
    }

    @SneakyThrows
    private static Pet createPetFromResultSet(ResultSet resultSet) {
        String ownerName = resultSet.getString("owner_name");
        String petName = resultSet.getString("pet_name");
        String type = resultSet.getString("type");
        int age = resultSet.getInt("age");
        String sex = resultSet.getString("sex");
        String color = resultSet.getString("color");
        return new Pet(ownerName, petName, type, age, sex, color);
    }

    @SneakyThrows
    private static Pet getPetByNames(Connection connection, String ownerName, String petName) {
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_PET_BY_NAMES);
        preparedStatement.setString(1, ownerName);
        preparedStatement.setString(2, petName);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return Pet.builder().
                    petName(resultSet.getString("pet_name")).
                    type(resultSet.getString("type")).
                    age(resultSet.getInt("age")).
                    sex(resultSet.getString("sex")).
                    color(resultSet.getString("color")).
                    build();
        }
        throw new RuntimeException();
    }
}