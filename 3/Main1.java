import java.util.Iterator;

public class Main1 {
    public static void main(String[] args) {
        int[] a = {2,3,4,6,4,2,3,7,9,8};
        Iterator<Integer> iterator = new MyIterator(a);
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

    }
    static class MyIterator implements Iterator<Integer> {
        private int current;
        private int[] array;

        public MyIterator(int[] arr) {
            this.array = new int[arr.length];
            for (int i = 0; i < arr.length; i++) {
                array[i] = arr[i];
            }
            this.current = 0;
        }

        @Override
        public boolean hasNext() {
            boolean check = false;
            for (int i = current; i < array.length; i++) {
                if (array[i] % 2 == 1) {
                    check = true;
                    current = i;
                    break;
                }
            }
            return check;
        }

        @Override
        public Integer next() {
            return array[current++];
        }
    }
}
