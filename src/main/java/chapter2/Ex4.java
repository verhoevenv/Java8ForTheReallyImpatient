package chapter2;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Ex4 {
    public static void main(String[] args) {
        int[] values = { 1, 4, 9, 16 };
        Stream<int[]> s1 = Stream.of(values);
        IntStream s2 = Arrays.stream(values);
    }
}
