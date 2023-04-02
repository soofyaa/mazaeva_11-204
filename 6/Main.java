import java.util.*;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {
    /*
+Создать Map, где ключом будет фамилия, а значением - номер телефона.
Написать метод, принимающий на вход Map и выводящий на экран все значения (телефоны).

+Проверить с помощью регулярки все значения что они соответствуют
формату ‘+7(ххх) xxx-xx-xx’ где x - числа от 0 до 9

+Реализуйте метод, который принимает на вход Map и возвращает новую Map,
где ключи и значения поменяны местами.

+Написать метод, которая принимает на вход Map,
содержащий числа, и выводит на экран сумму всех значений в Map.

+Написать метод, который удаляет из Map все элементы,
которые больше заданного значения. (Мапа <Стринг,Инт>)

+Написать метод, которая принимает на вход Map, содержащий строки,
и выводит на экран наиболее часто встречающееся значение строки. (Ключ может быть любым)
     */

    public static void main(String[] args) {
        Map<String, String> users = new HashMap<>();
        users.put("Иванов", "+7 (999) 123-45-67");
        users.put("Петров", "+7 (999) 234-56-78");
        users.put("Сидоров", "+7 (999) 345-67-89");
        users.put("Кузнецов", "+7 (999) 456-78-90");
        users.put("Смирнов", "+7 (999) 567-89-01");
        users.put("Андреев", "+7 (999) 678-90-12");
        users.put("Козлов", "+7 (999) 789-01-23");
        users.put("Новиков", "+7 (999) 890-12-34");
        users.put("Морозов", "+7 (999) 901-23-45");
        users.put("Волков", "+7 (999) 012-34-56");
        printNumbers(users);
        System.out.println(replaceValueKey(users));
        System.out.println(isValid(users));

        Map<String, Integer> map1 = new HashMap<>();
        map1.put("543",2);
        map1.put("45",3);
        map1.put("345",4);
        map1.put("23",5);
        map1.put("234",6);
        System.out.println(map1);
        removeAllElementsMoreN(map1, 3);
        System.out.println(map1);

        Map<String, String> map2 = new HashMap<>();
        map2.put("we", "qwe");
        map2.put("ewe", "qwe");
        map2.put("cwe", "rwe");
        map2.put("vwe", "qwe");
        map2.put("twe", "twe");
        map2.put("wye", "ywe");
        map2.put("uwe", "cwe");
        printTop(map2);

    }

    public static void printNumbers(Map<String, String> map) {
        map.values().forEach(System.out::println);
    }

    public static <K, V> Map<V, K> replaceValueKey(Map<K,V> map) {
        return map.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));
    }

    public static <K> boolean isValid(Map<K, String> map) {
        Pattern pattern = Pattern.compile("^\\+7\\s\\(\\d{3}\\)\\s\\d{3}-\\d{2}-\\d{2}$");
        return map.entrySet().stream()
                .filter(v -> pattern.matcher(v.getValue()).matches())
                .count() == map.size();
    }

    public static <K> double sumAllValues(Map<K, Double> map) {
        if (map.containsValue(null)) {
            throw new RuntimeException("null is value");
        }
        return map.values().stream()
                .mapToDouble(Double::doubleValue)
                .sum();
    }

    public static void removeAllElementsMoreN(Map<String, Integer> map, int n) {
        map.entrySet().removeIf(v -> v.getValue() > n);
    }

    public static <K> void printTop(Map<K, String> map) {
        System.out.println("top: " + map.values().stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .get()
        );
    }
}
