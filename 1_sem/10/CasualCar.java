public class CasualCar extends Car implements Beepable {
    public CasualCar(int speedFirst3Minutes, int speedAfter) {
        super(speedFirst3Minutes, speedAfter);
    }

    public CasualCar() {
    }

    @Override
    public String toString() {
        return "CasualCar {" + "speedFirst3Minutes = " + speedFirst3Minutes + ", speedAfter = " + speedAfter + '}';
    }

    @Override
    public void beep() {
        System.out.println("beeee");
    }

    @Override
    public int countDistance(int duration) {
        if (duration > 3) {
            return speedFirst3Minutes * 3 + speedAfter * (duration - 3);
        } else {
            return speedFirst3Minutes * duration;
        }
    }
}
