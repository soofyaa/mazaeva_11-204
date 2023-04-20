import lombok.*;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        try {
            FileInputStream file = new FileInputStream("laptops.csv");
            List<Laptop> laptops = new ArrayList<>();
            byte[] bytes = new byte[file.available()];
            file.read(bytes);
            String[] s = new String(bytes).split("\\r?\\n");
            List<String> strings = new ArrayList<>(Arrays.asList(s));
            strings.remove(0);
            for (String str : strings) {
                String[] data = str.split(",");
                laptops.add(Laptop.builder()
                        .company(data[1])
                        .typeName(data[2])
                        .cpuName(data[10])
                        .gpuBrand(data[18])
                        .gpuName(data[19])
                        .price(Double.parseDouble(data[22]))
                        .build());
            }

            System.out.println(laptops.stream()
                            .collect(Collectors.groupingBy(l -> l.cpuName))
                            .values()
                            .stream()
                            .max(Comparator.comparing(List::size))
                            .get()
                            .get(0).cpuName
                            );

            laptops.stream()
                            .filter(l -> l.gpuBrand.equals("Nvidia") ||
                                    (l.gpuBrand.equals("AMD") && l.gpuName.split(" ").length > 2))
                            .collect(Collectors.groupingBy(l -> l.price))
                            .keySet()

                      /*
                    .max(p -> p.price)
                    .get()
                    .getPrice()

                       */





        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
class Laptop {
    public String company;
    public String typeName;
    public String cpuName;
    public String gpuBrand;
    public String gpuName;
    public double price;
}