package chapter2;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.stream.Stream;

import static chapter2.Ex8.zip;
import static org.junit.Assert.*;

public class Ex8Test {

    @Test
    public void testZip() throws Exception {
        Stream<String> s1 = Stream.of("A", "B", "C");
        Stream<String> s2 = Stream.of("Z", "X");

        Stream<String> zipped = zip(s1, s2);

        Assertions.assertThat(zipped.toArray()).containsExactly("A", "Z", "B", "X");
    }

    @Test
    public void testZip_infiniteStreams() throws Exception {
        Stream<String> s1 = Stream.generate(() -> "A");
        Stream<String> s2 = Stream.generate(() -> "Z");

        Stream<String> zipped = zip(s1, s2);

        Assertions.assertThat(zipped.limit(6).toArray()).containsExactly("A", "Z", "A", "Z", "A", "Z");
    }
}