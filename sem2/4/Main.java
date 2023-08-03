import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        };
        List<Integer> l = new ArrayList<>();
        l.add(3);
        l.add(4);
        l.add(2);
        MySet mySet = new MySet(l, comparator, l.get(0).getClass());
        System.out.println(mySet);
        mySet.add(7);
        mySet.add(1);
        System.out.println(mySet);
        mySet.remove(3);
        System.out.println(mySet);
        mySet.add(5);
        System.out.println(mySet);
        System.out.println("subSet from 1 to 4: " + mySet.subSet(1,4));
        System.out.println("tailSet from 2: " + mySet.tailSet(2));
        System.out.println("headSet to 4: " + mySet.headSet(4));

        System.out.println("-----------------------");

        List<String> s = new ArrayList<>();
        s.add("zada");
        s.add("vdzada");
        s.add("hmnzada");
        s.add("98olkjzada");
        s.add("kszada");
        System.out.println(s);

        Comparator<String> comparator2 = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        };

        MySet strings = new MySet(s,comparator2,s.get(0).getClass());
        System.out.println(strings);
        strings.add("sp");
        System.out.println(strings);
    }
}
