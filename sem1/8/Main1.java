public class Main1 {
    public static void main(String[] args) {
        Vector2D v1 = new Vector2D(2, 3);
        Vector2D v2 = new Vector2D(-48, 79);
        System.out.println("vector 1: " + v1);
        System.out.println("vector 2: " + v2);

        Vector2D v3 = v1.add(v2);
        System.out.println("add: " + v3);

        v1.add2(v2);
        System.out.println("add2: " + v1);

        Vector2D v4 = v1.sub(v2);
        System.out.println("sub: " + v4);

        v1.sub2(v2);
        System.out.println("sub2: " + v1);

        Vector2D v5 = v1.mult(5.6);
        System.out.println("mult: " + v5);

        v1.mult2(5.6);
        System.out.println("mult2: " + v1);

        System.out.println("length vector1: " + v2.length());

        System.out.println("scalar: " + v1.scalarProduct(v2));

        System.out.println("cos: " + v1.cos(v2));

        System.out.println("vector " + v1 +  " == " + "vector " + v2 + " ? " + v1.equals(v2));
    }
}
