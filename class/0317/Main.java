import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Чтение данных из файлов
        List<User> users = readUsersFromFile("users.txt");
        List<Subscription> subscriptions = readSubscriptionsFromFile("subscriptions.txt");

        // Составление статистики по городам
        Map<String, Integer> birthsByCity = new HashMap<>();
        for (User user : users) {
            String city = user.getCity();
            int count = birthsByCity.getOrDefault(city, 0);
            birthsByCity.put(city, count + 1);
        }
        System.out.println("Статистика по городам:");
        for (Map.Entry<String, Integer> entry : birthsByCity.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        // Проверка подписок
        String cityToCheck = "Х"; // Замените на нужный город
        int subscribedToCity = 0;
        int subscribedToOther = 0;
        Set<String> usersFromCity = new HashSet<>();
        for (User user : users) {
            if (user.getCity().equals(cityToCheck)) {
                usersFromCity.add(user.getEmail());
            }
        }
        for (Subscription subscription : subscriptions) {
            String subscriberEmail = subscription.getSubscriber();
            String subscribedEmail = subscription.getSubscribed();
            if (usersFromCity.contains(subscriberEmail) && !usersFromCity.contains(subscribedEmail)) {
                subscribedToOther++;
            } else if (!usersFromCity.contains(subscriberEmail) && usersFromCity.contains(subscribedEmail)) {
                subscribedToCity++;
            }
        }
        if (subscribedToCity > subscribedToOther) {
            System.out.println("Больше людей из города " + cityToCheck + " подписаны на людей из других городов.");
        } else if (subscribedToCity < subscribedToOther) {
            System.out.println("Больше людей из других городов подписаны на людей из города " + cityToCheck + ".");
        } else {
            System.out.println("Количество подписок одинаково.");
        }
    }

    private static List<User> readUsersFromFile(String fileName) {
        List<User> users = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNextLine()) {
                String[] fields = scanner.nextLine().split(",");
                String email = fields[0];
                String city = fields[1];
                int birthYear = Integer.parseInt(fields[2]);
                users.add(new User(email, city, birthYear));
            }
        } catch (FileNotFoundException e) {
            System.err.println("Файл не найден: " + fileName);
        } catch (NumberFormatException e) {
            System.err.println("Ошибка в формате данных: " + e.getMessage());
        }
        return users;
    }

    private static List<Subscription> readSubscriptionsFromFile(String fileName) {
        List<Subscription> subscriptions = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNextLine()) {
                String[] fields = scanner.nextLine().split(",");
                String subscriber = fields[0];
                String subscribed = fields[1];
                subscriptions.add(new Subscription(subscriber, subscribed));
            }
        } catch (FileNotFoundException e) {
            System.err.println("Файл не найден: " + fileName);
        }
        return subscriptions;
    }
}

class User {
    private final String email;
    private final String city;
    private final int birthYear;

    public User(String email, String city, int birthYear) {
        this.email = email;
        this.city = city;
        this.birthYear = birthYear;
    }

    public String getEmail() {
        return email;
    }

    public String getCity() {
        return city;
    }

    public int getBirthYear() {
        return birthYear;
    }
}

class Subscription {
    private final String subscriber;
    private final String subscribed;

    public Subscription(String subscriber, String subscribed) {
        this.subscriber = subscriber;
        this.subscribed = subscribed;
    }

    public String getSubscriber() {
        return subscriber;
    }

    public String getSubscribed() {
        return subscribed;
    }
}