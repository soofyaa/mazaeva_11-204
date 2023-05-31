import java.util.List;

public class CountThread extends Thread{
    private List<Integer> integers;
    private int sum;

    public CountThread(List<Integer> integers) {
        this.integers = integers;
    }

    public int getSum() {
        return sum;
    }

    @Override
    public void run() {
        sum = 0;
        for (int i : integers) {
            sum += i;
        }
    }
}