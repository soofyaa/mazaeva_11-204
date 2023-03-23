public class RationalFraction {
    private int n;
    private int d;

    public RationalFraction() {
        this.n = 0;
        this.d = 1;
    }

    public RationalFraction(int x, int y) {
        this.n = (x < 0 && y < 0) || (x > 0 && y > 0) ? Math.abs(x) : (-1)*Math.abs(x);
        this.d = Math.abs(y);
    }

    public String toString() {
        return n +"/"+ d;
    }

    public double value() {
        return (double) n / d;
    }

    public int numberPart() {
        return n / d;
    }

    public boolean equals(RationalFraction rf) {
        return this.value() == rf.value();
    }

    public void reduce() {
        int k = nod(Math.abs(this.n), Math.abs(this.d));
        this.n /= k;
        this.d /= k;
    }

    public RationalFraction add(RationalFraction rf) {
        RationalFraction newRF = new RationalFraction();
        newRF.n = this.n*rf.d + rf.n*this.d;
        newRF.d = this.d*rf.d;
        newRF.reduce();
        return newRF;
    }

    public void add2(RationalFraction rf) {
        this.n = this.n*rf.d + rf.n*this.d;
        this.d = this.d * rf.d;
        this.reduce();
    }

    public RationalFraction sub(RationalFraction rf) {
        RationalFraction newRF = new RationalFraction();
        newRF.n = this.n*rf.d - rf.n*this.d;
        newRF.d = this.d * rf.d;
        newRF.reduce();
        return newRF;
    }

    public void sub2(RationalFraction rf) {
        this.n = this.n*rf.d - rf.n*this.d;
        this.d = this.d * rf.d;
        this.reduce();
    }

    public RationalFraction mult(RationalFraction rf) {
        RationalFraction newRF = new RationalFraction();
        newRF.n = this.n * rf.n;
        newRF.d = this.d * rf.d;
        newRF.reduce();
        return newRF;
    }

    public void mult2(RationalFraction rf) {
        this.n = this.n * rf.n;
        this.d = this.d * rf.d;
        this.reduce();
    }

    public RationalFraction div(RationalFraction rf) {
        RationalFraction newRF = new RationalFraction();
        newRF.n = this.n * rf.d;
        newRF.d = this.d * rf.n;
        newRF.reduce();
        return newRF;
    }

    public void div2(RationalFraction rf) {
        this.n = this.n * rf.d;
        this.d = this.d * rf.n;
        this.reduce();
    }

    private static int nod(int a, int b) {
        if (a != 0) {
            while (a != b) {
                if (a > b) {
                    a -= b;
                } else {
                    b -= a;
                }
            }
            return a;
        } else {
            return 1;
        }
    }

    private static int nok(int a, int b) {
        return (a*b) / nod(a,b);
    }
}
