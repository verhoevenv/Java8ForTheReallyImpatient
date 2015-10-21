package chapter6;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Ex9Test {

    @Test
    public void testFibByMatrix() throws Exception {
        Assertions.assertThat(Ex9.fibByMatrix(10)).isEqualTo(testfib(10));
        Assertions.assertThat(Ex9.fibByMatrix(15)).isEqualTo(testfib(15));
    }

    //don't use for n too large
    private long testfib(int n) {
        if(n == 0) return 1;
        if(n == 1) return 1;
        return testfib(n - 1) + testfib(n - 2);
    }
}