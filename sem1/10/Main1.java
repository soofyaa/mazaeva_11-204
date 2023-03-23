public class Main1 {
    public static void main(String[] args) {
        ComplexNumber cn1 = new ComplexNumber(-3, 5);
        ComplexNumber cn2 = new ComplexNumber(4, -8);
        System.out.println(cn1);
        System.out.println(cn2);
        ComplexNumber cn3 = cn1.add(cn2);
        System.out.println("add: " + cn3);
        cn1.add2(cn2);
        System.out.println("add2: " + cn1);
        System.out.println(cn1);
        System.out.println(cn2);
        ComplexNumber cn4 = cn1.sub(cn2);
        System.out.println("sub: " + cn4);
        cn1.sub2(cn2);
        System.out.println("sub2: " + cn1);

        double d = 2;
        ComplexNumber cn5 = cn1.multNumber(d);
        System.out.println("multNumber: " + cn5);
        cn1.multNumber2(d);
        System.out.println("multNumber2: " + cn1);

        System.out.println(cn1);
        System.out.println(cn2);

        ComplexNumber cn6 = cn1.mult(cn2);
        System.out.println("mult: " + cn6);
        cn1.mult2(cn2);
        System.out.println("mult2: " + cn1);

        System.out.println(cn1);
        System.out.println(cn2);

        ComplexNumber cn7 = cn1.div(cn2);
        System.out.println("div: " + cn7);
        cn1.div2(cn2);
        System.out.println("div2: " + cn1);

        System.out.println(cn1.length());

        ComplexNumber cn10 = new ComplexNumber(1, 2);
        ComplexNumber cn11 = cn10.pow(4);
        System.out.println(cn11);

        ComplexNumber cn20 = new ComplexNumber(-1, 2);
        ComplexNumber cn21 = new ComplexNumber(1, 2);
        System.out.println(cn20.equals(cn21));
    }
}