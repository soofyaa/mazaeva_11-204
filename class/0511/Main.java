import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class Main {

    static List<String> list = new ArrayList<>();
    static long start;
    static long end;

    public static void main(String[] args) {
        start = System.currentTimeMillis();
        List<MyThread> myThreads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            myThreads.add(new MyThread());
        }

        for (int i = 0; i < 10; i++) {
            myThreads.get(i).start();
        }

//        boolean alive = true;
//        while (alive) {
//            alive = myThreads.stream().anyMatch(t -> t.isAlive());
//        }
    }

    public static class MyThread extends Thread {
        public void run() {
            ApiCatFactGetter apiCatFactGetter = new ApiCatFactGetter();
            try {
                list.add(apiCatFactGetter.getCatFactsFromApi("https://cat-fact.herokuapp.com/facts"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (list.size() == 10) {
                end = System.currentTimeMillis();
                long time = end - start;
                System.out.println(Thread.currentThread().getName() + "time: " + time);
            }
        }
    }

    public static class ApiCatFactGetter {
        private String url;

        public String getCatFactsFromApi(String url) throws IOException {
            try {
                InputStream inputStream = new URL(url).openStream();
                byte[] bytes = new byte[inputStream.available()];
                inputStream.read(bytes);
                String json = new String(bytes);
                return json;
            } catch (Exception e) {
                return null;
            }
        }
    }
}