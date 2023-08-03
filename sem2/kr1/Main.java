import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        MyStack<Car> cars = new MyStack<>();
        Scanner file = new Scanner(new File("honda.csv"));
        file.nextLine();
        while (file.hasNextLine()) {
            String[] strings = file.nextLine().split(",");
            int year = Integer.parseInt(strings[0]);
            int kms = Integer.parseInt(strings[1].split(" ")[0]);
            double price = Double.parseDouble(strings[4].split(" ")[0]);
            cars.push(new Car(year, kms, strings[2], strings[3], price, strings[5]));
        }
        /*
        Создать List<Car> sortedCars в который поместить отсортированные
        по возрастанию по полю km driven машины из MyStack.
        Использовать stream sorted() внутрь передать компаратор как lambda выражение.
        */

        Comparator<Car> comparator = new Comparator<Car>() {
            @Override
            public int compare(Car o1, Car o2) {
                return Integer.compare(o1.getKms(), o2.getKms());
                //return o1.getKms().compareTo(o2.getKms());
            }
        };

        List<Car> sortedCars = cars.stream().sorted(comparator).collect(Collectors.toList());

        /*
        В 1 строчку используя Stream API среди машин,
        которые ездят на бензине, найти самую дешевую и вывести.
         */
        System.out.println(cars.stream().filter(c -> c.getType().equals("Petrol")).min(Comparator.comparing(Car::getPrice)));

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


    public static class MyStack<T>  extends AbstractCollection<T> {
        //Stack
        List<T> list;

        public MyStack() {
            this.list = new ArrayList<>();
        }

        public MyStack(List<T> list) {
            this.list = list;
        }

        @Override
        public Iterator<T> iterator() {
            return list.iterator();
        }

        @Override
        public int size() {
            return list.size();
        }

        public T push(T elem) {
            list.add(elem);
            return elem;
        }

        public T peek() {
            if (empty()) {
                throw new EmptyStackException();
            }
            return list.get(0);
        }

        public T pop() {
            if (empty()) {
                throw new EmptyStackException();
            }
            return list.remove(0);
        }

        public boolean empty() {
            return list.isEmpty();
        }

        public int search(Object o) {
            int i = list.lastIndexOf(o);
            if (i >= 0) {
                return size() - i;
            }
            return -1;
        }
    }

    public static class MyIterator<Car> implements Iterator<Main.Car> {

        /*
        Создать итератор, который принимает Collection<Car>
        в конструкторе и итерирует только машины, у которых km driven меньше 30 000.
        */

        private List<Main.Car> cars;
        private int current = 0;

        public MyIterator(Collection<Main.Car> cars) {
            this.cars = new ArrayList<>();
            for (Main.Car car : cars) {
                if (car.getKms() < 30000) {
                    this.cars.add(car);
                }
            }
        }

        @Override
        public boolean hasNext() {
            return current < cars.size()-1;
        }

        @Override
        public Main.Car next() {
            return cars.get(current++);
        }
    }
}
