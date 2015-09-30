package chapter2;

import java.util.Iterator;
import java.util.Random;
import java.util.stream.Stream;

public class Ex7 {
    private static final long INFINITY = 640_000; //Should be enough for anyone?

    public static <T> boolean isFinite(Stream<T> stream) {
        return stream.count() < INFINITY;
    }
}
