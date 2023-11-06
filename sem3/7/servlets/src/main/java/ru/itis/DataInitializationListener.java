package ru.itis;

import lombok.SneakyThrows;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
@WebListener
public class DataInitializationListener implements ServletContextListener {
    @Override
    @SneakyThrows
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        BufferedReader reader = new BufferedReader(new FileReader("src/main/java/ru/itis/data.sql"));
        String line;
        StringBuilder sql = new StringBuilder();

        while ((line = reader.readLine()) != null) {
            sql.append(line);
        }

        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                "postgres","sofya");

        PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
    }

    @Override
    @SneakyThrows
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                "postgres","sofya");

        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users");
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
    }
}
