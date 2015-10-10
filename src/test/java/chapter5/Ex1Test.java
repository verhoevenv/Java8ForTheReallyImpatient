package chapter5;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class Ex1Test {

    @Test
    public void testProgrammersDay_nonLeapYear() throws Exception {
        LocalDate usingPlusDays = LocalDate.of(2014, 1, 1).plusDays(255);

        LocalDate result = Ex1.programmersDay(2014);

        Assertions.assertThat(result).isEqualTo(usingPlusDays);
    }

    @Test
    public void testProgrammersDay_leapYear() throws Exception {
        LocalDate usingPlusDays = LocalDate.of(2012, 1, 1).plusDays(255);

        LocalDate result = Ex1.programmersDay(2012);

        Assertions.assertThat(result).isEqualTo(usingPlusDays);
    }
}