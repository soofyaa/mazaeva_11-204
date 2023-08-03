import org.junit.Before;
import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.*;

public class LexicographicIntComparatorTest {

    private Comparator<Integer> comparator;

    @Before
    public void setUp() {
        comparator = new LexicographicIntComparator();
    }

    @Test
    public void testCompare() {
        assertTrue(comparator.compare(1234, 123) > 0);
    }
}