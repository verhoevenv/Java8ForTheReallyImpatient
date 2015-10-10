package chapter5;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.time.YearMonth;

import static chapter5.Ex4.cal;

public class Ex4Test {

    @Test
    public void testCal_bookExample() throws Exception {
        YearMonth month = YearMonth.of(2013, 3);

        String result = cal(month);

        Assertions.assertThat(result).isEqualTo(
                "             1  2  3\n" +
                " 4  5  6  7  8  9 10\n" +
                "11 12 13 14 15 16 17\n" +
                "18 19 20 21 22 23 24\n" +
                "25 26 27 28 29 30 31"
        );
    }


    @Test
    public void testCal_lastWeekNotFull() throws Exception {
        YearMonth month = YearMonth.of(2015, 10);

        String result = cal(month);

        Assertions.assertThat(result).isEqualTo(
                "          1  2  3  4\n" +
                " 5  6  7  8  9 10 11\n" +
                "12 13 14 15 16 17 18\n" +
                "19 20 21 22 23 24 25\n" +
                "26 27 28 29 30 31"
        );
    }
}