//import org.junit.Test;
//
//import java.io.InputStreamReader;
//import java.io.OutputStreamWriter;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.util.ArrayList;
//import java.util.Comparator;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import static org.junit.Assert.assertEquals;
//
//public class Main3 {
//    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException, ClassNotFoundException {
//        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
//
//        int m = 4;
//        int n = array.length;
//        int[] results = new int[m];
//        List<Integer> list = new ArrayList<>();
//        list.add(23);
//        list.add(123);
//        list.add(1);
//        Comparator<Integer> comparator = new Comparator() {
//            @Override
//            public int compare(Object o1, Object o2) {
//                int s1 = String.valueOf(o1).length();
//                int s2 = String.valueOf(o2).length();
//                return Integer.compare(s1,s2);
//            }
//        };
//        list.sort(comparator);
//        System.out.println(list);
//
//
//    }
//
//    public class MyThread extends Thread {
//        public void run() {
//        }
//    }
//
//    public class MyRunnable implements Runnable {
//        public void run() {
//        }
//    }
//
//    public class ListTest {
//
//        @Test
//        public void testAdd() {
//            List<String> list = new LinkedList<>();
//            list.add("apple");
//            assertEquals("apple", list.get(0));
//        }
//    }
//
//    FileInputStream in = new FileInputStream("input.txt");
//    FileOutputStream out = new FileOutputStream("output.txt");
//
//    InputStreamReader isr = new InputStreamReader(in, "UTF-8");
//    OutputStreamWriter osw = new OutputStreamWriter(out, "UTF-8");
//
//    public void method1() {
//        synchronized (lock1) {
//            synchronized (lock2) {
//                System.out.println("method1");
//            }
//        }
//    }
//
//    public void method2() {
//        synchronized (lock2) {
//            synchronized (lock1) {
//                System.out.println("method2");
//            }
//        }
//    }
//
//    public class MyClass<T extends Number> {
//        // ...
//    }
//
//    public void myMethod(List<? extends Number> myList) {
//        // ...
//    }
//
//    public class NumberCollections {
//        public static void copy(List<? super Number> dest,
//                                List<? extends Number> src) {
//            for (int i = 0; i < src.size(); i++) {
//                dest.set(i, src.get(i));
//            }
//        }
//    }
//}
