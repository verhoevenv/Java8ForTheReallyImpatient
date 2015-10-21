package chapter6;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import static org.junit.Assert.*;

public class TwoByTwoLongMatrixTest {

    @Test
    public void testMultiply() throws Exception {
        TwoByTwoLongMatrix one = new TwoByTwoLongMatrix(1, 2, 3, 4);
        TwoByTwoLongMatrix other = new TwoByTwoLongMatrix(4, 3, 2, 1);

        TwoByTwoLongMatrix result = one.multiply(other);

        Assertions.assertThat(result.getE11()).isEqualTo(8);
        Assertions.assertThat(result.getE12()).isEqualTo(5);
        Assertions.assertThat(result.getE21()).isEqualTo(20);
        Assertions.assertThat(result.getE22()).isEqualTo(13);
    }
}