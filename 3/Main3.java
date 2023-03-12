import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

        //System.out.println(b);

        ModifiableCollection<Billionaire> billionaires = new ModifiableCollection<>(Billionaire.class, b);
        System.out.println(billionaires);
    }
}
