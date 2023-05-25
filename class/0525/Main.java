import java.util.ArrayList;
import java.util.List;

public class Main {
    public static char c = 'a';

    public static void main(String[] args) {
        CharPrinter charPrinter = new CharPrinter();
        List<MyThread> myThreads = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            myThreads.add(new MyThread());
        }

        for (MyThread thread : myThreads) {
            thread.start();
        }

    }

    public static class CharPrinter {
        public synchronized void printChar() {
            for (int i = 0; i < 1000; i++) {
                System.out.println(c);
            }
            c++;
        }
    }

    public static class MyThread extends Thread {
        @Override
        public void run(){
            new CharPrinter().printChar();
        }
    }
}