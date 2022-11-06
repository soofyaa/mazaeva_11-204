public class BoostedCar extends Car {
    private int boostStartTime = -1;

    public int getBoostStartTime() {
        return boostStartTime;
    }

    public BoostedCar(double weight, double horsePower, int speedFirst3Minutes, int speedAfter, int boostStartTime){
        super(weight, horsePower, speedFirst3Minutes, speedAfter);
        this.boostStartTime = boostStartTime;
    }

    @Override
    public void beep(){
        System.out.println("boo");
    }

    @Override
    public String toString() {
        return "F1Car{" + "weight=" + weight + ", horsePower=" + horsePower + ", speedFirst3Minutes=" + speedFirst3Minutes + ", speedAfter=" + speedAfter + '}';
    }

    @Override
    public int countDistance(int duration) {
        int way = 0;

        if (boostStartTime != -1) {
            for (int i = 1; i < duration+1; i++) {
                if (i == boostStartTime) {
                    while (i < boostStartTime+3 && i < duration+1) {
                        if (i <= 3) {
                            way += speedFirst3Minutes*2;
                        } else {
                            way += speedAfter*2;
                        }
                        i++;
                    }
                    i--;
                } else {
                    if (i <= 3) {
                        way += speedFirst3Minutes;
                    } else {
                        way += speedAfter;
                    }
                }
            }
            return way;
        } else {
            if (duration > 3) {
                return speedFirst3Minutes * 3 + speedAfter * (duration - 3);
            } else {
                return speedFirst3Minutes * duration;
            }
        }
    }
}
