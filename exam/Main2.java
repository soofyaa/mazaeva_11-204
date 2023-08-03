import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Main2 {
    public static void main(String[] args) {

        Comparator comparator = new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                int n = Math.min(s1.length(), s2.length());
                for (int i = 0; i < n; i++) {
                    if (s1.charAt(i) != s2.charAt(i)) {
                        return s1.compareTo(s2);
                    }
                }
                return 0;
            }
        };



        Set<String> set1 = new TreeSet<>(comparator);
        Set<String> set2 = new TreeSet<>(comparator);
        try (BufferedReader br = new BufferedReader(new FileReader("file1.txt")) ){
            String line;
            while ((line = br.readLine()) != null) {
                set1.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (BufferedReader br = new BufferedReader(new FileReader("file2.txt")) ){
            String line;
            while ((line = br.readLine()) != null) {
                set2.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Set<String> set = new TreeSet<>(comparator);
        set.addAll(set1);
        set.addAll(set2);

        Iterator<String> iterator = set.iterator();

        try (FileWriter writer = new FileWriter("out.txt")){
            while (iterator.hasNext()) {
                writer.write(iterator.next());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
