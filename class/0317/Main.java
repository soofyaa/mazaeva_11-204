import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner file = new Scanner(new File("popular.csv"));
        file.nextLine();
        MyQueue<Channel> channels = new MyQueue<>();
        while (file.hasNextLine()) {
            String s = file.nextLine();
            if (s.isEmpty()) {
                continue;
            }
            String[] strings = s.split(",");
            channels.offer(new Channel(Integer.parseInt(strings[1]), strings[2], strings[3], strings[4], Double.parseDouble(strings[5]),
                    strings[6], strings[7], strings[8]));
        }
        System.out.println(channels);

        MyQueue m = new MyQueue<>();

        MyQueue = new MyQueue<>()
    }
}
