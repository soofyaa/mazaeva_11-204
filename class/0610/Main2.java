import java.util.Random;

public class Main2 {

    public static void main(String[] args) throws InterruptedException {
        int n = 10; // размер массива
        int m = 4; // количество потоков
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = i+1;
        }

        // создание массива задач
        SumTask[] tasks = new SumTask[m];
        int size = n/m; // размер части массива для каждой задачи
        for (int i = 0; i < m; i++) {
            int start = i * size;
            int end = (i == m - 1) ? n : (i + 1) * size;
            tasks[i] = new SumTask(array, start, end);
        }

        Thread[] threads = new Thread[m];
        for (int i = 0; i < m; i++) {
            threads[i] = new Thread(tasks[i]);
            threads[i].start();
            threads[i].join();
        }

        int sum = 0;
        for (int i = 0; i < m; i++) {
            sum += tasks[i].getResult();
        }

        System.out.println("Сумма элементов массива: " + sum);
    }

    // задача для подсчета суммы элементов массива в заданном диапазоне индексов
    static class SumTask implements Runnable {
        private int[] array;
        private int start;
        private int end;
        private int result;

        public SumTask(int[] array, int start, int end) {
            this.array = array;
            this.start = start;
            this.end = end;
        }

        public int getResult() {
            return result;
        }

        @Override
        public void run() {
            int sum = 0;
            for (int i = start; i < end; i++) {
                sum += array[i];
            }
            result = sum;
        }
    }
}

