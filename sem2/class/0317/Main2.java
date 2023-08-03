import java.util.Arrays;

public class Main2 {

    /*
    Разделить задачу суммирования массива с n элементами на m потоков
     */

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int m = 4;
        int n = array.length;
        int[] results = new int[m];

        Thread[] threads = new Thread[m];
        for (int i = 0; i < m; i++) {
            int finalI = i;
            threads[i] = new Thread(() -> {
                int sum = 0;
                for (int j = finalI * (n / m); j < (finalI + 1) * (n / m); j++) {
                    sum += array[j];
                }
                results[finalI] = sum;
            });
            threads[i].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        int totalSum = Arrays.stream(results).sum();
        System.out.println("Total sum: " + totalSum);
    }
}