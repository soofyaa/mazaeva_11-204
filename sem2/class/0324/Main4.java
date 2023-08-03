import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class Main4 {
    public static void main(String[] args) {

    }

    public static class StringIterator implements Iterator<Character> {
        private String string;
        int current;

        public StringIterator(String string) {
            this.string = string;
            this.current = 0;
        }

        @Override
        public boolean hasNext() {
            return current < string.length();
        }

        @Override
        public Character next() {
            return string.charAt(current++);
        }
    }

    @Before
    public void setUp() {
        List<String> list = new LinkedList<>();
        list.add("apple");
    }

    @Test
    public void testAdd() {
        assertEquals("apple", list.get(0));
    }
}
