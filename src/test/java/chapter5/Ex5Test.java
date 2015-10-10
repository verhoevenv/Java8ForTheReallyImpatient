package chapter5;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.time.LocalDate;

import static chapter5.Ex5.daysBetween;

public class Ex5Test {

    //note how this is the only testable component of the program, other methods depend on the system time
    @Test
    public void testDaysBetween() throws Exception {
        long result = daysBetween(LocalDate.of(1986, 9, 6), LocalDate.of(1986, 9, 8));

        Assertions.assertThat(result).isEqualTo(2);
    }
}