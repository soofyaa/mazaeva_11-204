import java.util.Comparator;

public class LexicographicIntComparator implements Comparator<Integer> {
    @Override
    public int compare(Integer o1, Integer o2) {
        String s1 = Integer.toString(o1);
        String s2 = Integer.toString(o2);
        int len1 = s1.length();
        int len2 = s2.length();
        int minLen = Math.min(len1, len2);
        for (int i = 0; i < minLen; i++) {
            char c1 = s1.charAt(i);
            char c2 = s2.charAt(i);
            if (c1 != c2) {
                return c1 - c2;
            }
        }
        return len1 - len2;
    }
}