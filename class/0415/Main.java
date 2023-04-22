import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String string = scanner.nextLine();
        System.out.println(string.chars()
                .filter(c -> c >= 'A' && c <= 'Z')
                .count());

        List<Integer> list = new ArrayList<>();
        System.out.print("введите длину списка: ");
        int n = scanner.nextInt();
        System.out.println("введите элементы списка: ");
        for (int i = 0; i < n; i++) {
            list.add(scanner.nextInt());
        }
        System.out.println(list.stream()
                .mapToInt(Integer::intValue)
                .sum());

        System.out.println(list.stream()
                .mapToInt(Integer::intValue)
                .average()
                .getAsDouble());


        // прочитать список студентов из файла и вывести список с нумерацией output.txt
        String inputFile = "input.txt";
        String outputFile = "output.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
                int count = 1;
                String line;
                reader.readLine();
                while ((line = reader.readLine()) != null) {
                    String[] tokens = line.split("\\n");
                    String name = tokens[0];
                    writer.write(count++ + ". " + name + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
