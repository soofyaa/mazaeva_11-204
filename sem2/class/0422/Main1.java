import java.util.Scanner;

public class Main1 {
    public static void main(String[] args) {
        //вводим массив, 2 потока max & min
        Scanner scanner = new Scanner(System.in);
        System.out.print("n: ");
        int n = scanner.nextInt();
        System.out.println("elements:");
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        Thread thread1 = new Thread(() -> {
            int max = arr[0];
            for (int i = 0; i < n; i++) {
                if (arr[i] > max) {
                    max = arr[i];
                }
            }
            System.out.println("max: " + max);
        });

        Thread thread2 = new Thread(() -> {
            int min = arr[0];
            for (int i = 0; i < n; i++) {
                if (arr[i] < min) {
                    min = arr[i];
                }
            }
            System.out.println("min: " + min);
        });

        thread1.start();
        thread2.start();
    }
}
