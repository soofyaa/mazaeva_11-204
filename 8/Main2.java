import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CasualCar casualCar = new CasualCar(1000, 450, 100, 150);
        F1Car f1Car = new F1Car(1000, 450, 60, 220);
        BoostedCar boostedCar = new BoostedCar(1000,450,80, 200, 1);
        System.out.print("Длительность заезда (минуты): ");
        int duration = scanner.nextInt();
        System.out.println("машина CasualCar проехала " + casualCar.countDistance(duration) + " метров");
        System.out.println("машина F1Car проехала " + f1Car.countDistance(duration) + " метров");
        System.out.println("машина BoostedCar проехала " + boostedCar.countDistance(duration) + " метров");
        Race.start(casualCar, f1Car, boostedCar, duration);
    }
}
