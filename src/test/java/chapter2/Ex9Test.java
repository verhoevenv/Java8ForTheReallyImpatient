package chapter2;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

public class Ex9Test {

    @Test
    public void testJoin1() {
        ArrayList<String> l1 = new ArrayList<>(asList("a", "b"));
        ArrayList<String> l2 = new ArrayList<>(asList("c", "d"));
        Stream<ArrayList<String>> stream = Stream.of(l1, l2);
        ArrayList<String> joined = Ex9.join_r1(stream);

        Assertions.assertThat(joined).containsExactly("a", "b", "c", "d");
    }

    @Test
    public void testJoin1_emptyStream() {
        ArrayList<String> joined = Ex9.join_r1(Stream.empty());

        Assertions.assertThat(joined).isEmpty();
    }


    @Test
    public void testJoin2() {
        ArrayList<String> l1 = new ArrayList<>(asList("a", "b"));
        ArrayList<String> l2 = new ArrayList<>(asList("c", "d"));
        Stream<ArrayList<String>> stream = Stream.of(l1, l2);
        ArrayList<String> joined = Ex9.join_r2(stream);

        Assertions.assertThat(joined).containsExactly("a", "b", "c", "d");
    }

    @Test
    public void testJoin2_emptyStream() {
        ArrayList<String> joined = Ex9.join_r2(Stream.empty());

        Assertions.assertThat(joined).isEmpty();
    }

    @Test
    public void testJoin3() {
        ArrayList<String> l1 = new ArrayList<>(asList("a", "b"));
        ArrayList<String> l2 = new ArrayList<>(asList("c", "d"));
        Stream<ArrayList<String>> stream = Stream.of(l1, l2);
        ArrayList<String> joined = Ex9.join_r3(stream);

        Assertions.assertThat(joined).containsExactly("a", "b", "c", "d");
    }

    @Test
    public void testJoin3_emptyStream() {
        ArrayList<String> joined = Ex9.join_r3(Stream.empty());

        Assertions.assertThat(joined).isEmpty();
    }
}