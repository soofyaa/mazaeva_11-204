import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main1 {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(43);
        list.add(-421);
        list.add(32);
        NumberComparator numberComparator = new NumberComparator();
        System.out.println(list);
        list.sort(numberComparator);
        System.out.println(list);

    }

    public static class NumberComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            int digO1 = String.valueOf(Math.abs(o1)).length();
            int digO2 = String.valueOf(Math.abs(o2)).length();
            return Integer.compare(digO1, digO2);
        }
    }
}