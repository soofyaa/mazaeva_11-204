public class F1Car extends Car {
    public F1Car(double weight, double horsePower, int speedFirst3Minutes, int speedAfter){
        super(weight, horsePower, speedFirst3Minutes, speedAfter);
    }

    @Override
    public void beep(){
        System.out.println("be be be be");
    }

    @Override
    public String toString() {
        return "F1Car{" + "weight=" + weight + ", horsePower=" + horsePower + ", speedFirst3Minutes=" + speedFirst3Minutes + ", speedAfter=" + speedAfter + '}';
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
