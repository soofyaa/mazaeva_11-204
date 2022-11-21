import java.io.*;

public class Race {
    public static void start(Car[] car, int duration) throws IOException {

        int[] way = new int[car.length];
        int wayMax = 0;
        int index = 0;

        for (int i = 0; i < car.length; i++) {
            way[i] = car[i].countDistance(duration);
            if (way[i] > wayMax) {
                wayMax = way[i];
                index = i;
            }
        }
        car[index].beep();
        System.out.println("Car with index " + index + " win!");
    }
}
