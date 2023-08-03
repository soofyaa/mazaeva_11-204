import java.util.*;
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

    public static <K> void printTopValue(Map<K, String> map) {
        System.out.println("top value: " +
                map.values().stream()
                .max(Comparator.comparing(v -> Collections.frequency(map.values(), v)))
                .orElse(null)
        );
    }
}