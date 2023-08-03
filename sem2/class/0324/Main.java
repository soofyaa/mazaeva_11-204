import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    /*
    Распарсить файл в лист Car, после этого получить мапу Map<String,
    List<Car> ключ которой тип коробки передач, а значение список машин с такой коробкой передач,
    в 1 строчку используя стримы вывести машины в виде «название + стоимость» у которых коробка автомат
     */

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

        cars.stream()
                .collect(Collectors.groupingBy(c -> c.suspension))
                .entrySet()
                .stream()
                .filter(e -> e.getKey().equals("Automatic"))
                .forEach(e -> {
                    e.getValue().forEach(c -> {
                        System.out.println(c.model + " " + c.price);
                    });
                });
    }


    public static class Car {
        private int year;
        private int kms;
        private String type;
        private String suspension;
        private double price;
        private String model;

        public int getKms() {
            return kms;
        }

        public String getType() {
            return type;
        }

        public double getPrice() {
            return price;
        }

        public Car() {}

        public Car(int year, int kms, String type, String suspension, double price, String model) {
            this.year = year; //год
            this.kms = kms; //км
            this.type = type;
            this.suspension = suspension;
            this.price = price; //цена
            this.model = model;
        }

        @Override
        public String toString() {
            return "Car{year=" + year +
                    ", kms=" + kms +
                    ", type=" + type +
                    ", suspension=" + suspension +
                    ", price=" + price +
                    ", model=" + model + "}";
        }
    }


}
