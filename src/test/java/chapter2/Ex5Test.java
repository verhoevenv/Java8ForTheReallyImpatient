package chapter2;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import static org.junit.Assert.*;

public class Ex5Test {

    @Test
    public void testLcg() throws Exception {
        long seed = System.currentTimeMillis();
        //GCC values
        LongStream randGenerator = Ex5.lcg(1103515245, 1103515245, 1 << 31, seed);

        long[] randNums = randGenerator.limit(100).toArray();

        //Note that for given parameters the next value of the LCG only depends on the previous value
        //A duplicate value would mean the period of the numbers is less than 100 (aka you couldn't generate more than 100 numbers)
        //That would be sad, and you'd need other values for the parameters
        Assertions.assertThat(randNums).doesNotHaveDuplicates();

        //... your favorite random number test here?

        System.out.println(Arrays.toString(randNums));
    }

    @Test
    public void testLcg_numbersFromBook() throws Exception {
        long seed = System.currentTimeMillis();
        //GCC values
        LongStream randGenerator = Ex5.lcg(25214903917L, 11, 1L << 48, seed);

        long[] randNums = randGenerator.limit(100).toArray();

        Assertions.assertThat(randNums).doesNotHaveDuplicates();

        System.out.println(Arrays.toString(randNums));
    }
}