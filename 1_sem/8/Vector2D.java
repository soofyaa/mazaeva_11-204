public class Vector2D {
    private double x;
    private double y;

    public Vector2D(){
    }

    public Vector2D(double x, double y){
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return "{" + this.x + "; " + this.y + "}";
    }

    public Vector2D add(Vector2D vector) {
        Vector2D newVector = new Vector2D();
        newVector.x = this.x + vector.x;
        newVector.y = this.y + vector.y;
        return newVector;
    }

    public void add2(Vector2D vector) {
        this.x += vector.x;
        this.y += vector.y;
    }

    public Vector2D sub(Vector2D vector) {
        Vector2D newVector = new Vector2D();
        newVector.x = this.x - vector.x;
        newVector.y = this.y - vector.y;
        return newVector;
    }

    public void sub2(Vector2D vector) {
        this.x -= vector.x;
        this.y -= vector.y;
    }

    public Vector2D mult(double k) {
        Vector2D newVector = new Vector2D();
        newVector.x = this.x * k;
        newVector.y = this.y * k;
        return newVector;
    }

    public void mult2(double k) {
        this.x *= k;
        this.y *= k;
    }

    public double length() {
        return Math.abs(Math.pow(x*x + y*y, 0.5));
    }

    public double scalarProduct(Vector2D vector) {

        return this.x * vector.x + this.y * vector.y;
    }

    public double cos(Vector2D vector) {
        if (vector.length() == 0 || this.length() == 0)
        {
            throw new RuntimeException("vector can not be with zero length");
        }
        return this.scalarProduct(vector) / (this.length() * vector.length());
    }

    public boolean equals(Vector2D vector) {

        return this.x == vector.x && this.y == vector.y;
    }
}
