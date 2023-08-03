import java.util.ArrayList;
import java.util.Iterator;

public class Main2 {
    public static void main(String[] args) {
        ArrayList<String> l = new ArrayList<>();
        l.add("1");
        l.add("2");
        l.add("3");
        l.add("4");
        l.add("5");

        ModifiableCollection<String> c = new ModifiableCollection<>(String.class, l);
        System.out.println(c);
        System.out.println(c.size());

        c.add("8");
        System.out.println(c);

        c.remove("8");
        System.out.println(c);

        ModifiableCollection<String> c1 = new ModifiableCollection<>();
        c1.add("1");
        c1.add("2");
        c1.add("3");
        System.out.println(c1);

        c.removeAll(c1);
        System.out.println(c);

        c.add("7");
        System.out.println(c);
    }
}
