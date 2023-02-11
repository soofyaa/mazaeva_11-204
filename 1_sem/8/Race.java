public class Race {
    public static void start(Car car1, Car car2, Car car3, int duration) {
        int way1 = car1.countDistance(duration);
        int way2 = car2.countDistance(duration);
        int way3 = car3.countDistance(duration);
        if (way1 > way2 && way1 > way3) {
            car1.beep();
        } else if (way2 > way3) {
            car2.beep();
        } else {
            car3.beep();
        }
    }
}
