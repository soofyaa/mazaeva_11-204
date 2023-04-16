import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) throws Exception {
        MyLinkedList<Integer> l = new MyLinkedList<>();
        l.add(1);
        l.add(2);
        l.add(2);
        l.add(3);

        MyLinkedList<Integer> l1 = new MyLinkedList<>();
        l1.add(1);
        l1.add(2);

        l.removeAll(l1);
        System.out.println(l);

        ArrayList<Integer> a = new ArrayList<>();
        a.add(1);
        a.add(2);
        a.add(2);
        a.add(3);

        ArrayList<Integer> a1 = new ArrayList<>();
        a1.add(1);
        a1.add(2);

        a.removeAll(a1);
        System.out.println(a);
    }
}
