package chapter2;

import java.util.stream.Stream;

public class Ex6 {
    public static Stream<Character> characterStream(String s) {
        return s.chars().mapToObj(i -> (char) i);
    }
}
