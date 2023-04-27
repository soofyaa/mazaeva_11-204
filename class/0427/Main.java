import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        URLConnection urlConnection = new URL("http://l92167fj.beget.tech").openConnection();
        urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0");
        InputStream inputStream = urlConnection.getInputStream();
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes);
        String json = new String(bytes);
        ObjectMapper objectMapper = new ObjectMapper();
        List<User> user = new ObjectMapper().readValue(json, new TypeReference<List<User>>(){});
        System.out.println(user);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class User {
        public String name;
        public String favoriteFruit;
        public List<UserFriend> friends;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class UserFriend {
        public String name;
    }
}