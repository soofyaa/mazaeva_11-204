import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        List<Car> cars = new ArrayList<>();
        Scanner file = new Scanner(new File("honda.csv"));
        file.nextLine();
        while (file.hasNextLine()) {
            String[] strings = file.nextLine().split(",");
            int year = Integer.parseInt(strings[0]);
            int kms = Integer.parseInt(strings[1].split(" ")[0]);
            double price = Double.parseDouble(strings[4].split(" ")[0]);
            cars.add(new Car(year, kms, strings[2], strings[3], price, strings[5]));
        }

        // Получить и вывести список машин, у которых тип топлива бензин и автоматическая коробка передач
        System.out.println(cars.stream()
                .filter(car -> car.getType().equals("Petrol") && car.getSuspension().equals("Automatic"))
                .collect(Collectors.toList()));

        // Получить список цен на машины, перевести их в тип Integer и вывести количество четных значений
        System.out.println(cars.stream()
                .map(car -> (int) car.getPrice())
                .collect(Collectors.toList())
                .stream()
                .filter(price -> price % 2 == 0)
                .count());

        // Получить список цен на машины, посчитать среднюю цену, вывести количество машин, которые больше этой цены
        List<Double> prices = cars.stream()
                .map(Car::getPrice)
                .collect(Collectors.toList());

        double averagePrice = prices.stream()
                .mapToDouble(Double::doubleValue)
                .average()
                .getAsDouble();

        System.out.println(prices.stream()
                .filter(price -> price > averagePrice)
                .count());

        /*
        в одну строчку:

        System.out.println(cars.stream()
                .filter(car -> car.getPrice() > (cars.stream()
                        .mapToDouble(Car::getPrice)
                        .average()
                        .getAsDouble()))
                .count());

        */

        // Получить список машин, на которых ездили меньше чем 10 тысяч километров в год
        System.out.println(cars.stream()
                .filter(car -> car.getKms() < 10000)
                .collect(Collectors.toList()));

        // Получить количество машин, название которых начинается с Honda City и год выпуска не меньше 2015
        System.out.println(cars.stream()
                .filter(car -> car.getModel().startsWith("Honda City") && car.getYear() >= 2015)
                .count());
    }
}
