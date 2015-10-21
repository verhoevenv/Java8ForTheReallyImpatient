package chapter6;

public class TwoByTwoLongMatrix {
    private final long e11;
    private final long e12;
    private final long e21;
    private final long e22;

    public TwoByTwoLongMatrix(long e11, long e12, long e21, long e22) {
        this.e11 = e11;
        this.e12 = e12;
        this.e21 = e21;
        this.e22 = e22;
    }

    public TwoByTwoLongMatrix multiply(TwoByTwoLongMatrix other) {
        final long e11 = this.e11 * other.e11 + this.e12 * other.e21;
        final long e12 = this.e11 * other.e12 + this.e12 * other.e22;
        final long e21 = this.e21 * other.e11 + this.e22 * other.e21;
        final long e22 = this.e21 * other.e12 + this.e22 * other.e22;
        return new TwoByTwoLongMatrix(e11, e12, e21, e22);
    }

    public long getE11() {
        return e11;
    }

    public long getE12() {
        return e12;
    }

    public long getE21() {
        return e21;
    }

    public long getE22() {
        return e22;
    }
}
