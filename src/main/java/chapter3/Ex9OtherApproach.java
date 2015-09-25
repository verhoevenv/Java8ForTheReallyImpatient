package chapter3;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Function;

import static chapter3.Ex9.ComparablePair.pair;

public class Ex9OtherApproach {
    @SuppressWarnings("unchecked")
    public static <T> Comparator<T> lexographicComparator(String... fieldStrings) {
        return (Comparator<T>) Arrays.stream(fieldStrings)
                .map(s -> Comparator.comparing( x -> extractFieldAsComparable(x, s) ))
                .reduce(Comparator::thenComparing)
                .get();
    }

    @SuppressWarnings("unchecked")
    private static <U extends Comparable<? super U>> U extractFieldAsComparable(Object x, String s) {
        try {
            return (U) x.getClass().getField(s).get(x);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }
}
