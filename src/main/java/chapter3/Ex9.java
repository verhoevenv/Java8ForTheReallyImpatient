package chapter3;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Function;

import static chapter3.Ex9.ComparablePair.pair;

public class Ex9 {
    public static <T> Comparator<T> lexographicComparator(String... fieldStrings) {
        return (x, y) -> {
            return Arrays.stream(fieldStrings)
                    .map(uncheck(x.getClass()::getField))
                    .map(uncheck(f -> pair(f.get(x), f.get(y))))
                    .reduce(0,
                            (i, p) -> (i == 0) ? p.getFirst().compareTo(p.getSecond()) : i,
                            (i, j) -> (i == 0) ? j : i);
        };
    }

    public static class ComparablePair<T extends Comparable<T>> {
        private T first;
        private T second;

        private ComparablePair(T first, T second) {
            this.first = first;
            this.second = second;
        }

        @SuppressWarnings("unchecked")
        public static <T extends Comparable<T>> ComparablePair<T> pair(Object first, Object second){
            return new ComparablePair<T>((T)first, (T)second);
        }

        public T getFirst() {
            return first;
        }

        public T getSecond() {
            return second;
        }
    }

    //see chapter 1 ex 6, reflection is full of this shit
    private static <T, R> Function<T, R> uncheck(FunctionEx<T, R> wrapped) {
        return (t) -> {
            try {
                return wrapped.apply(t);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    @FunctionalInterface
    public interface FunctionEx<T, R> {
        R apply(T t) throws Exception;
    }
}
