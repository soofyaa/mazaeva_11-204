import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class Test {
    public static void main(String[] args) {
        Comparator<String> comparator = new Comparator<>() {
            @Override
            public int compare(String s1, String s2) {
                return Integer.compare(s1.length(), s2.length());
            }
        };

        Collections.sort();

    }
}
