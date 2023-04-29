import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        MyMap<String, Integer> myMap = new MyMap<>();
        myMap.put("ad", 1);
        myMap.put("b", 2);
        System.out.println(myMap);
        System.out.println(myMap.containsKey("д"));
        myMap.put("d", 71);
        myMap.put("c", 223);
        myMap.put("ch", 223);
        myMap.put("sd", 143);
        myMap.put("2", 25);
        myMap.put("a", 25);
        myMap.put("1", 3);
        System.out.println(myMap + " " + myMap.size());
        myMap.remove("clk");
        System.out.println(myMap + " " + myMap.size());
        myMap.remove("ad");
        System.out.println(myMap.entrySet());
        Map<String,Integer> map = new HashMap<>();
        map.put("r", 12);
        map.put("aa", 212);
        map.put("234", 312);
        System.out.println("-----------");
        System.out.println(myMap);
        myMap.putAll(map);
        System.out.println(myMap);




    }
}
