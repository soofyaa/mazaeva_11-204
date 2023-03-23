public class Main {
    public static void main(String[] args) {
        Rectangle r1 = new Rectangle(2,2,2,9,4,9,4,2);
        System.out.println(r1);
        System.out.println(r1.square());
        System.out.println(r1.perimeter());
        Rectangle r2 = new Rectangle(2,3,2,9,4,9,4,3);
        System.out.println(r1.haveCommonPoints(r2));
        System.out.println(r1.equals(r2));
    }
}
