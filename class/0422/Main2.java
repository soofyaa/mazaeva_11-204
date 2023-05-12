public class Main2 {
    static int N = 0; //volatile больше операций, обращаемся к оперативе, а не к кэшу
    static final Object o = new Object(); // объект который помогает синхронизировать
    public static void main(String[] args) throws InterruptedException {
        //100 потоков, которые буду прибавлять одну глобальную переменную до 10000
        int n = 5;
        for (int i = 0; i < n; i++) {
            Thread thread = new Thread(() -> {
                int local = 0;
                synchronized (o) {
                    while (N != 10000) {
                        local++;
                        N++;
                    }
                }

                System.out.println(Thread.currentThread() + " " + local);
            });
            thread.start();
            //join
        }
        System.out.println(Thread.currentThread() + " " + N);
    }
}
