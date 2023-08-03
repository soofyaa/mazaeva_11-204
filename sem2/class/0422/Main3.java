public class Main3 {
    static volatile boolean exit = true;
    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            while (true) {
                while (!exit) {
                    System.out.println("A");
                    exit = true;
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            while (true) {
                while (exit) {
                    System.out.println("B");
                    exit = false;
                }
            }
        });
        thread2.start();
        thread1.start();
    }
    /*
    2 потока
    производитель
    потребитель
    mutex
     */
}
