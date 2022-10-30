import java.util.*;

public class Main1 {
    public static void main(String[] args) {
        Lion l1 = new Lion("21", 5, "Африканский", "Коричневый", 178);
        Lion l2 = new Lion("15", 9, "Азиатский", "Белый", 247);
        l1.lionXp();
        l2.lionXp();
        l1.passport();
        l2.passport();
        Lion l3 = Lion.newLion(l1, l2);
        l3.passport();
        l3.lionSound();
        l3.needFood();
        l3.lionGame();
    }
}