public class Main1 {
    public static void main(String[] args) throws LionException {
        Lion l1 = new Lion("21", 8, "Африканский", "Коричневый", 200);
        Lion l2 = new Lion("15", 9, "Азиатский", "Белый", 247);
        l1.lionXp();
        l2.lionXp();
        l1.passport();
        l2.passport();
        l2.lionSound();
        l2.needFood();
        l2.lionGame();

        try {
            Lion l3 = Lion.newLion(l1, l2);
            l3.passport();
        } catch (LionException e) {
            e.printStackTrace();
        }

        System.out.println(Lion.cntSomething(l1));
    }
}