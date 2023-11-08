package ru.itis.listeners;

import lombok.SneakyThrows;
import ru.itis.utils.ConnectionContainer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class DatabaseListener implements ServletContextListener {

    @Override
    @SneakyThrows
    public void contextInitialized(ServletContextEvent sce) {
        ConnectionContainer.getConnection();
        System.out.println("Database initialized");
    }

    @Override
    @SneakyThrows
    public void contextDestroyed(ServletContextEvent sce) {
        ConnectionContainer.getConnection().close();
        System.out.println("Database connection closed");
    }
}
