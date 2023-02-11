import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Main2 {
    public static void main(String[] args) throws IOException {

        int duration = 4;

        File file = new File("cars.txt");
        Path path = Paths.get(file.toURI());
        int lines = 0;

        try {
            lines = (int) Files.lines(path).count();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] data = new String[lines];

        try {
            Scanner scanner = new Scanner(file);
            for (int i = 0; i < lines; i++) {
                data[i] = scanner.nextLine();
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        Car[] car = new Car[lines];

        for (int i = 0; i < lines; i++) {
            String[] carInf = data[i].split(" ");

            if (carInf.length == 3) {
                BoostedCar bc = new BoostedCar();
                bc.speedFirst3Minutes = Integer.parseInt(carInf[0]);
                bc.speedAfter = Integer.parseInt(carInf[1]);
                bc.boostStartTime = Integer.parseInt(carInf[2]);
                car[i] = bc;
            } else {
                CasualCar cc = new CasualCar();
                cc.speedFirst3Minutes = Integer.parseInt(carInf[0]);
                cc.speedAfter = Integer.parseInt(carInf[1]);
                car[i] = cc;
            }
        }

        for (int i = 0; i < car.length; i++) {
            System.out.print("car " + i + ": " + car[i].countDistance(duration) + " meters and ");
            car[i].beep();
        }

        System.out.println();
        System.out.print("first: ");

        Race.start(car, duration);
    }
}
