public class ComplexNumber {
    private double a;
    private double b;

    public ComplexNumber() {
    }

    public ComplexNumber(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public String toString() {
        return (b < 0) ? a + " - " + Math.abs(b) + " * i" : a + " + " + Math.abs(b) + " * i";
    }

    public ComplexNumber add(ComplexNumber cn) {
        ComplexNumber newCn = new ComplexNumber();
        newCn.a = this.a + cn.a;
        newCn.b = this.b + cn.b;
        return newCn;
    }

    public void add2(ComplexNumber cn) {
        this.a = this.a + cn.a;
        this.b = this.b + cn.b;
    }

    public ComplexNumber sub(ComplexNumber cn) {
        ComplexNumber newCn = new ComplexNumber();
        newCn.a = this.a - cn.a;
        newCn.b = this.b - cn.b;
        return newCn;
    }

    public void sub2(ComplexNumber cn) {
        this.a = this.a - cn.a;
        this.b = this.b - cn.b;
    }

    public ComplexNumber multNumber(double d) {
        ComplexNumber newCn = new ComplexNumber();
        newCn.a = this.a * d;
        newCn.b = this.b * d;
        return newCn;
    }

    public void multNumber2(double d) {
        this.a = this.a * d;
        this.b = this.b * d;
    }

    public ComplexNumber mult(ComplexNumber cn) {
        ComplexNumber newCn = new ComplexNumber();
        newCn.a = this.a * cn.a - this.b * cn.b;
        newCn.b = this.a * cn.b + cn.a * this.b;
        return newCn;
    }

    public void mult2(ComplexNumber cn) {
        double x = this.a * cn.a - this.b * cn.b;
        double y = this.a * cn.b + cn.a * this.b;
        this.a = x;
        this.b = y;
    }

    public ComplexNumber div(ComplexNumber cn) {
        ComplexNumber newCn = new ComplexNumber();
        newCn.a = (this.a * cn.a + this.b * cn.b) / (cn.a*cn.a + cn.b*cn.b);
        newCn.b = (cn.a * this.b - this.a * cn.b) / (cn.a*cn.a + cn.b*cn.b);
        return newCn;
    }

    public void div2(ComplexNumber cn) {
        double x = (this.a * cn.a + this.b * cn.b) / (cn.a*cn.a + cn.b*cn.b);
        double y = (cn.a * this.b - this.a * cn.b) / (cn.a*cn.a + cn.b*cn.b);
        this.a = x;
        this.b = y;
    }

    public double length() {
        return Math.pow(this.a*this.a + this.b*this.b, 0.5);
    }

    public double arg() {
        if (this.a > 0) {
            return Math.atan(this.b/this.a);
        } else if (this.a < 0 && this.b >= 0) {
            return Math.PI + Math.atan(this.b/this.a);
        } else if (this.a < 0 && this.b < 0) {
            return - Math.PI + Math.atan(this.b/this.a);
        } else if (this.a == 0 && this.b > 0) {
            return Math.PI/2;
        } else {
            return - Math.PI/2;
        }
    }

    public ComplexNumber pow(double n) {
        ComplexNumber newCn = new ComplexNumber();
        newCn.a = Math.pow(this.length(), n) * Math.cos(n * this.arg());
        newCn.b = Math.pow(this.length(), n) * Math.sin(n * this.arg());
        return newCn;
    }

    public boolean equals(ComplexNumber cn) {
        return this.a == cn.a && this.b == cn.b;
    }
}