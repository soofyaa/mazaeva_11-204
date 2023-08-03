import org.junit.Before;
import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.*;

public class OddDigitsIntComparatorTest {
    Comparator<Integer> comparator;
    @Before
    public void setUp() {
        comparator = new OddDigitsIntComparator();
    }
    @Test
    public void testCompare() {
        assertTrue(comparator.compare(123, 1234) < 0);
    }
}
