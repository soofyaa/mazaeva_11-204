abstract class Car implements Beepable {
    public int speedFirst3Minutes;
    public int speedAfter;
    public int boostStartTime = -1;

    public Car(int speedFirst3Minutes, int speedAfter) {
    }

    public Car() {
    }

    public int countDistance(int duration) {
        if (duration > 3) {
            return speedFirst3Minutes*3 + speedAfter*(duration-3);
        } else {
            return speedFirst3Minutes*duration;
        }
    }

    public String toString() {
        return "Car {" + "speedFirst3Minutes = " + speedFirst3Minutes + ", speedAfter = " + speedAfter + '}';
    }

    @Override
    public void beep() {
        System.out.println("beep");
    }
}
