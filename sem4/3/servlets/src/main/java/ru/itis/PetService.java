package ru.itis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PetService {

    private final DataSource dataSource;

    @Autowired
    public PetService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private final String ADD_PET = "INSERT INTO pets (name, age, height, weight, breed, color) VALUES (?, ?, ?, ?, ?, ?)";
    private final String GET_ALL_PETS = "SELECT * FROM pets";

    public void addPet(Pet pet) {

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_PET)) {
            preparedStatement.setString(1, pet.getName());
            preparedStatement.setInt(2, pet.getAge());
            preparedStatement.setDouble(3, pet.getHeight());
            preparedStatement.setDouble(4, pet.getWeight());
            preparedStatement.setString(5, pet.getBreed());
            preparedStatement.setString(6, pet.getColor());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Pet> getAllPets() {
        List<Pet> pets = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(GET_ALL_PETS)) {

            while (resultSet.next()) {
                Pet pet = new Pet();
                pet.setName(resultSet.getString("name"));
                pet.setAge(resultSet.getInt("age"));
                pet.setHeight(resultSet.getDouble("height"));
                pet.setWeight(resultSet.getDouble("weight"));
                pet.setBreed(resultSet.getString("breed"));
                pet.setColor(resultSet.getString("color"));
                pets.add(pet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pets;
    }
}
