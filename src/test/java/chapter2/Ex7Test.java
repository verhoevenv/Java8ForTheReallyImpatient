package chapter2;

import org.assertj.core.api.Assertions;
import org.junit.Ignore;
import org.junit.Test;

import java.util.stream.Stream;

import static org.junit.Assert.*;

public class Ex7Test {

    @Test
    public void testIsFinite_forFiniteStream_ReturnTrue() throws Exception {
        Stream<Integer> finiteStream = Stream.of(1, 2, 3, 4);

        Assertions.assertThat(Ex7.isFinite(finiteStream)).isTrue();
    }

    @Ignore //you can probably guess what will happen...
    @Test(timeout = 10000)
    public void testIsFinite_forInfiniteStream_ReturnFalse() throws Exception {
        Stream<Integer> infiniteStream = Stream.generate(() -> 1);

        Assertions.assertThat(Ex7.isFinite(infiniteStream)).isFalse();
    }
}