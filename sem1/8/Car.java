public class Car {
    public double weight;
    public double horsePower;
    public int speedFirst3Minutes;
    public int speedAfter;
    public int boostStartTime = -1;

    public Car(){
    }

    public Car(double weight, double horsePower, int speedFirst3Minutes, int speedAfter) {
        this.weight = weight;
        this.horsePower = horsePower;
        this.speedFirst3Minutes = speedFirst3Minutes;
        this.speedAfter = speedAfter;
    }

    public void beep(){
        System.out.println("beep beep");
    }

    public String toString() {
        return "Car{" + "weight = " + weight + ", horsePower = " + horsePower + ", speedFirst3Minutes = " + speedFirst3Minutes + ", speedAfter = " + speedAfter + '}';
    }

    public int countDistance(int duration) {
        if (duration > 3) {
            return speedFirst3Minutes*3 + speedAfter*(duration-3);
        } else {
            return speedFirst3Minutes*duration;
        }
    }
}
