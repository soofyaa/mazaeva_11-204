package ru.itis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Server {
    private final Map<String, List<String>> users = new HashMap<>();
    private static Server server;

    public static Server getServer() {
        if (server == null) {
            server = new Server();
        }
        return server;
    }


    public void setUserName(String userName) {
        users.put(userName, new ArrayList<>());
    }

    public void sendMessage(String recipient, String message, String userName) {
        if (users.containsKey(recipient)) {
            users.get(recipient).add(userName + ": " + message);
        } else {
            throw new RuntimeException("No such recipient");
        }
    }

    public List<String> getMessages(String userName) {
        List<String> messages = users.get(userName);
        users.put(userName, new ArrayList<>());
        return messages;
    }
}
