package chapter6;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.stream.LongStream;


public class Ex4Test {

    @Test
    public void testMax() throws Exception {
        long[] longs = LongStream.rangeClosed(12345678901L, 12354678901L).toArray();

        long max = Ex4.calculatemax(longs);

        Assertions.assertThat(max).isEqualTo(12354678901L);
    }

    @Test
    public void testMin() throws Exception {
        long[] longs = LongStream.rangeClosed(12345678901L, 12354678901L).toArray();

        long max = Ex4.calculatemin(longs);

        Assertions.assertThat(max).isEqualTo(12345678901L);
    }
}