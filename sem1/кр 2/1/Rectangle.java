import java.util.Arrays;

public class Rectangle {
    private double[] a = new double[2];
    private double[] b = new double[2];
    private double[] c = new double[2];
    private double[] d = new double[2];

    public Rectangle(){
    }

    public Rectangle(double x11, double y11, double x12, double y12, double x21, double y21, double x22, double y22){
        this.a[0] = x11;
        this.a[1] = y11;
        this.b[0] = x12;
        this.b[1] = y12;
        this.c[0] = x21;
        this.c[1] = y21;
        this.d[0] = x22;
        this.d[1] = y22;
    }

    public double square() {
        return (this.b[1] - this.a[1]) * (this.d[0] - this.a[0]);
    }

    public double perimeter() {
        return 2 * ((this.b[1] - this.a[1]) + (this.d[0] - this.a[0]));
    }

    public boolean haveCommonPoints(Rectangle r) {
        return Arrays.equals(r.a, this.a) || Arrays.equals(r.b, this.b) || Arrays.equals(r.c, this.c) || Arrays.equals(r.d, this.d);
    }

    public boolean equals(Rectangle r) {
        return Arrays.equals(r.a, this.a) && Arrays.equals(r.b, this.b) && Arrays.equals(r.c, this.c) && Arrays.equals(r.d, this.d);
    }

    @Override
    public String toString() {
        return "Rectangle{a = " + Arrays.toString(this.a) + ", b = " + Arrays.toString(this.b) + ",c = " + Arrays.toString(this.c) + ", d = " + Arrays.toString(this.d) + "}";
    }

}
