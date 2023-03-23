public class Main2 {
    public static void main(String[] args) {
        RationalFraction rf1 = new RationalFraction(2, -4);
        RationalFraction rf2 = new RationalFraction(1, 2);
        System.out.println("w/o reduce: " + rf1);
        rf1.reduce();
        System.out.println("w/ reduce: " + rf1);

        RationalFraction rf3 = rf1.add(rf2);
        System.out.println("add: " + rf3);

        rf1.add2(rf2);
        System.out.println("add2: " + rf1);

        RationalFraction rf4 = rf1.sub(rf2);
        System.out.println("sub: " + rf4);

        rf1.sub2(rf2);
        System.out.println("sub2: " + rf1);

        RationalFraction rf5 = rf1.mult(rf2);
        System.out.println("mult: " + rf5);

        rf1.mult2(rf2);
        System.out.println("mult2: " + rf1);

        RationalFraction rf6 = rf1.div(rf2);
        System.out.println("div: " + rf6);

        rf1.div2(rf2);
        System.out.println("div2: " + rf1);

        System.out.println(rf1 + " = " + rf1.value());

        RationalFraction rf7 = new RationalFraction(1,2);
        RationalFraction rf8 = new RationalFraction(-25,4);
        System.out.println(rf7 + " == " + rf8 + " ? " + rf7.equals(rf8));

        System.out.println("integer part of " + rf8 + " is " + rf8.numberPart());
    }
}
