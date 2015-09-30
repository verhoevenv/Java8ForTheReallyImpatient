package chapter2;

import java.util.stream.LongStream;

public class Ex5 {
    public static LongStream lcg(long a, long c, long m, long seed) {
        return LongStream.iterate(seed, xn -> (a * xn + c) % m);
    }
}
