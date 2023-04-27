import java.util.Map;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        MyMap<String, Integer> myMap = new MyMap<>();
        //Map<String, Integer> myMap = new TreeMap<>();
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
        System.out.println(myMap.containsValue(25));
        System.out.println(myMap.values());
        System.out.println(myMap.entrySet());
        System.out.println(myMap.keySet());


    }
}
