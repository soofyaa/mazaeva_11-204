import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main3 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner file = new Scanner(new File("neww.csv"));
        file.nextLine();
        List<Billionaire> b = new ArrayList<>();

        while (file.hasNextLine()) {
            String s = file.nextLine();
            if (s.isEmpty()) {
                continue;
            }
            String[] strings = s.split(";");
            int age = (strings[2].equals("")) ? -1 : (int) Double.parseDouble(strings[2]);
            b.add(new Billionaire(Integer.parseInt(strings[0]), strings[1],
                    age, Integer.parseInt(strings[3]), strings[4],
                    strings[5], strings[6], strings[7], strings[8], strings[9], strings[10],
                    strings[11], strings[12], strings[13], (int) Double.parseDouble(strings[14])));
        }

        ModifiableCollection<Billionaire> billionaires = new ModifiableCollection<>(Billionaire.class, b);
        topFiveOrganizations(billionaires);
    }

    public static void topFiveOrganizations(ModifiableCollection<Billionaire> collection) {
        Map<String, Integer> map = new HashMap<>();
        for (Billionaire billionaire : collection) {
            if (map.containsKey(billionaire.getOrganization())) {
                map.put(billionaire.getOrganization(), map.get(billionaire.getOrganization()) + 1);
            } else {
                map.put(billionaire.getOrganization(), 1);
            }
        }

        List<Map.Entry<String, Integer>> list = new LinkedList<>(map.entrySet());

        list.sort(new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        int cnt = 0;
        for (Map.Entry<String, Integer> current : list) {
            System.out.println(current.getKey() + " " + current.getValue());
            cnt++;
            if (cnt == 5) {
                break;
            }
        }
    }
}
