import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main1 {

    public static void main(String[] args) {
        List<User> users = new ArrayList<>();
        List<Follows> follows = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("users.txt"))) {
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] userData = line.split(",");
                int year = Integer.parseInt(userData[2]);
                User user = new User(userData[0], userData[1], year);
                users.add(user);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (BufferedReader br = new BufferedReader(new FileReader("follows.txt"))) {
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] userData = line.split(",");
                Follows follow = new Follows(userData[0], userData[1]);
                follows.add(follow);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Map<String, Integer> map = new HashMap<>();
        for (User user : users) {
            String city = user.getCity();
            if (map.containsKey(city)) {
                map.put(city, map.get(city)+1);
            } else {
                map.put(city, 1);
            }
        }

        System.out.println("Статистика по городам");
        System.out.println("город_количество");
        map.entrySet().stream().forEach(current -> System.out.println(current.getKey() + "_" + current.getValue()));

        System.out.println("---");

        String cityToCheck = "moscow";
        int subscribedToCity = 0;
        int subscribedToOther = 0;

        List<String> emailOfUsersFromCity = new ArrayList<>();
        users.stream()
                .filter(user -> user.getCity().equals(cityToCheck))
                .forEach(user -> emailOfUsersFromCity.add(user.getEmail()));

        for (Follows follow : follows) {
            String emailUser = follow.getEmailUser();
            String emailFollow = follow.getEmailFollow();
            if (emailOfUsersFromCity.contains(emailUser) && !emailOfUsersFromCity.contains(emailFollow)) {
                subscribedToOther++;
            } else if (!emailOfUsersFromCity.contains(emailUser) && emailOfUsersFromCity.contains(emailFollow))
                subscribedToCity++;
        }

        if (subscribedToCity > subscribedToOther) {
            System.out.println("Больше людей из города " + cityToCheck + " подписаны на людей из других городов.");
        } else if (subscribedToCity < subscribedToOther) {
            System.out.println("Больше людей из других городов подписаны на людей из города " + cityToCheck + ".");
        } else {
            System.out.println("Количество подписок одинаково.");
        }
    }

    public static class User {
        private String email;
        private String city;
        private int year;

        public String getCity() {
            return city;
        }

        public String getEmail() {
            return email;
        }

        public User(String email, String city, int year) {
            this.email = email;
            this.city = city;
            this.year = year;
        }
    }

    public static class Follows {
        private String emailUser;

        public String getEmailUser() {
            return emailUser;
        }

        public String getEmailFollow() {
            return emailFollow;
        }

        private String emailFollow;

        public Follows(String emailUser, String emailFollow) {
            this.emailUser = emailUser;
            this.emailFollow = emailFollow;
        }
    }
}
