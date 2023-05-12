import java.util.Arrays;

public class Main {
    /*
        Написать свою аннотацию @Repeat(), которая принимает количество раз,
        которое нужно исполнить Runnable.
        Создать  свой CustomThreadPoolExecutor,
        который наследуется от ThreadPoolExecutor и
        переопределяет его метод execute(Runnable runnable).
        CustomThreadPoolExecutor в отличии от ThreadPoolExecutor
        должен при выполнении execute() выполнять команду не 1 раз,
        а столько, сколько указано в аннотации @Repeat
     */
    public static void main(String[] strings) {
        CustomThreadPoolExecutor customThreadPoolExecutor =
                new CustomThreadPoolExecutor(10);
        customThreadPoolExecutor.execute(new MyRunnable());
        customThreadPoolExecutor.shutdown();
    }
}
