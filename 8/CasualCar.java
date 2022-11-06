public class CasualCar extends Car {
    public CasualCar(double weight, double horsePower, int speedFirst3Minutes, int speedAfter){
        super(weight, horsePower, speedFirst3Minutes, speedAfter);
    }

    @Override
    public void beep(){
        System.out.println("beeee");
    }

    @Override
    public String toString() {
        return "CasualCar{" + "weight=" + weight + ", horsePower=" + horsePower + ", speedFirst3Minutes=" + speedFirst3Minutes + ", speedAfter=" + speedAfter + '}';
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
