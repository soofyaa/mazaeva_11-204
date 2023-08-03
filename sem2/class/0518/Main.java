public class Main {

    static String string;
    static Integer i = 0;

    static class MyThread extends Thread {
        @Override
        public void run(){
            new PrintString().print();
        }
    }

    static class PrintString {
        public synchronized void print() {
            System.out.print(string.charAt(i) + " " + string.charAt(i) + " ");
            i++;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        string = "Azat";
        for (int j = 0; j < string.length(); j++) {
            MyThread myThread = new MyThread();
            myThread.start();
            myThread.join();
        }
    }
}
