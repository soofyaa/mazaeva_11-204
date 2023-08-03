import java.util.Comparator;

public class OddDigitsIntComparator implements Comparator<Integer> {
    @Override
    public int compare(Integer o1, Integer o2) {
        int oddDigits1 = countOddDigits(o1);
        int oddDigits2 = countOddDigits(o2);
        return oddDigits1 - oddDigits2;
    }

    private int countOddDigits(int n) {
        int count = 0;
        while (n != 0) {
            int digit = n % 10;
            if (digit % 2 != 0) {
                count++;
            }
            n /= 10;
        }
        return count;
    }
}