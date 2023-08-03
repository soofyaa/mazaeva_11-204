import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HistoricalData {

    public static void main(String[] args) {
        String personsFile = "persons.txt"; // имя файла с информацией об исторических личностях
        String eventsFile = "events.txt"; // имя файла с информацией о событиях

        // Считываем данные о личностях из файла
        List<HistoricalPerson> persons = readPersonsFromFile(personsFile);

        // Считываем данные о событиях из файла
        List<HistoricalEvent> events = readEventsFromFile(eventsFile);

        // Составляем статистику по странам
        Map<String, Integer> countryCount = new HashMap<>();
        for (HistoricalPerson person : persons) {
            String country = person.getCountry();
            if (!countryCount.containsKey(country)) {
                countryCount.put(country, 0);
            }
            countryCount.put(country, countryCount.get(country) + 1);
        }
        System.out.println("Статистика по странам:");
        for (String country : countryCount.keySet()) {
            System.out.println(country + ": " + countryCount.get(country));
        }

        // Считаем количество событий, связанных с каждой личностью, начавшихся во второй половине их жизни
        System.out.println("Количество событий, начавшихся во второй половине жизни:");
        for (HistoricalPerson person : persons) {
            int eventsCount = 0;
            int yearOfDeath = person.getYearOfDeath();
            for (HistoricalEvent event : events) {
                if (event.getPersonName().equals(person.getName()) && event.getYearStart() > yearOfDeath / 2) {
                    eventsCount++;
                }
            }
            System.out.println(person.getName() + ": " + eventsCount);
        }
    }

    private static List<HistoricalPerson> readPersonsFromFile(String fileName) {
        List<HistoricalPerson> persons = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                String name = fields[0];
                String country = fields[1];
                int yearOfBirth = Integer.parseInt(fields[2]);
                int yearOfDeath = Integer.parseInt(fields[3]);
                HistoricalPerson person = new HistoricalPerson(name, country, yearOfBirth, yearOfDeath);
                persons.add(person);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return persons;
    }

    private static List<HistoricalEvent> readEventsFromFile(String fileName) {
        List<HistoricalEvent> events = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                String name = fields[0];
                String personName = fields[1];
                int yearStart = Integer.parseInt(fields[2]);
                int yearEnd = Integer.parseInt(fields[3]);
                HistoricalEvent event = new HistoricalEvent(name, personName, yearStart, yearEnd);
                events.add(event);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return events;
    }
}

class HistoricalPerson {
    private String name;
    private String country;
    private int yearOfBirth;
    private int yearOfDeath;

    public HistoricalPerson(String name, String country, int yearOfBirth, int yearOfDeath) {
        this.name = name;
        this.country = country;
        this.yearOfBirth = yearOfBirth;
        this.yearOfDeath = yearOfDeath;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public int getYearOfDeath() {
        return yearOfDeath;
    }
}

class HistoricalEvent {
    private String name;
    private String personName;
    private int yearStart;
    private int yearEnd;

    public HistoricalEvent(String name, String personName, int yearStart, int yearEnd) {
        this.name = name;
        this.personName = personName;
        this.yearStart = yearStart;
        this.yearEnd = yearEnd;
    }

    public String getName() {
        return name;
    }

    public String getPersonName() {
        return personName;
    }

    public int getYearStart() {
        return yearStart;
    }

    public int getYearEnd() {
        return yearEnd;
    }
}
