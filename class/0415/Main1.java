import java.util.Scanner;

public class Main1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Thread thread1 = new Thread(scanner::nextLine);
        Thread thread2 = new Thread(() -> {
            while (thread1.isAlive()) {
                System.out.println("Test");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}