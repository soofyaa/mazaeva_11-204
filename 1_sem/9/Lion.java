import java.util.Random;

public class Lion {

    private String number;
    private int xp;
    private int age;
    private double weight;
    private String color;
    private String poroda;

    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }

    public int lionXp() {
        xp = (age > 7 || weight < 80)? 25 : (age > 5 || weight < 100)? 50 : 100;
        return xp;
    }

    public Lion(String number, int age, String poroda, String color, double weight) {
        this.number = number;
        this.age = age;
        this.poroda = poroda;
        this.color = color;
        this.weight = weight;
    }
    public Lion() {
    }

    public static Lion newLion(Lion l1, Lion l2) throws LionException {
        if (l1.age < 4 || l2.age < 4) {
            throw new LionException("one of the lions is too young for children!");
        }

        Random random = new Random();

        Lion l3 = new Lion("",0, l1.poroda, l1.color, random.nextInt(10));
        l3.number = l1.number + "+" + l2.number;
        if (l1.weight < l2.weight) {
            l3.poroda = l2.poroda;
            l3.color = l2.color;
        }

        l3.xp = (l1.lionXp() == 25 || l2.lionXp() == 25)? 10 : (l1.lionXp() == 50 || l2.lionXp() == 50)? 30 : 80;
        return l3;
    }

    public void passport() {
        System.out.println();
        System.out.println("Лев №" + number);
        System.out.println("Возраст: " + age);
        System.out.println("Вес: " + weight);
        System.out.println("Порода: " + poroda);
        System.out.println("Цвет: " + color);
        System.out.println("Здоровье: " + xp);
    }

    public void lionSound() {
        System.out.println();
        System.out.println("ррррррррррр мяу");
    }

    public void needFood() {
        System.out.println();
        if (xp <= 50) {
            System.out.println("Надо кушать");
        } else {
            System.out.println("Не надо кушать");
        }
    }

    public void lionGame() {
        System.out.println();
        if (xp > 50) {
            System.out.println("Лев может поиграть");
        } else {
            System.out.println("Лев устал и вас укусит");
        }
    }

    public static int cntSomething(Lion lion) throws LionException {
        try {
            return 1000 / (int) (200 - lion.weight);
        } catch (ArithmeticException e) {
            throw new LionException("Lion can't have something", e);
        }
    }
}
