import com.oracle.webservices.internal.api.databinding.DatabindingMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {

        try {
            List<Student> students = new ArrayList<>();
            //InputStream inputStream = new URL("http://10.17.50.59:8080").openStream();
            //StandardCharsets.UTF_8

            FileInputStream file = new FileInputStream("1111.csv");
            byte[] bytes = new byte[file.available()];
            file.read(bytes);
            String[] lines = new String(bytes).split("\\r\\r\\n");
            List<String> strings = new ArrayList<>(Arrays.asList(lines));
            strings.remove(0);
            strings.remove(strings.size()-1);
            strings.remove(strings.size()-1);
            for (String s : strings) {
                String[] data = s.split(";");
                students.add(Student.builder()
                        .name(data[0])
                        .kr1(data[9].isEmpty() ? 0 : Double.parseDouble(data[9].replace(',', '.')))
                        .kr2(data[20].isEmpty() ? 0 : Double.parseDouble(data[20].replace(',', '.')))
                        .build());
            }
            System.out.println(students);

            FileChannel channel = FileChannel.open(Paths.get("output.txt"), StandardOpenOption.CREATE, StandardOpenOption.WRITE);
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            students.stream()
                    .collect(Collectors.groupingBy(s -> s.name, Collectors.summingDouble(s -> s.kr1 + s.kr2)))
                    .entrySet()
                    .stream()
                    .sorted((x,y) -> y.getValue().compareTo(x.getValue()))
                    .limit(5)
                    .forEachOrdered(entry -> {
                        String result = entry.getKey() + ": " + entry.getValue();
                        System.out.println(result);
                        buffer.put((result+"\n").getBytes());
                    });
            buffer.flip();
            channel.write(buffer);
            channel.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Student {
        private String name;
        private Double kr1;
        private Double kr2;

        @Override
        public String toString() {
            return  "{" + name + ": " + kr1 + ", " + kr2 + "}";
        }
    }
}
