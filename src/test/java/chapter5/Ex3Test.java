package chapter5;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.function.Predicate;

import static chapter5.Ex3.next_oldschool;
import static chapter5.Ex3.next;

public class Ex3Test {

    @Test
    public void testNext_bookExample() throws Exception {
        LocalDate aDate = LocalDate.of(2001, 9, 11);

        LocalDate nextWorkDay = aDate.with(next(w -> w.getDayOfWeek().getValue() < 6));

        Assertions.assertThat(nextWorkDay).isEqualTo(LocalDate.of(2001, 9, 12));
    }

    @Test
    public void testNext_palindromeDay() throws Exception {
        LocalDate aDate = LocalDate.of(2015, 10, 10);

        Predicate<LocalDate> isPalindrome = (d) -> {
            String formatted = DateTimeFormatter.BASIC_ISO_DATE.format(d);
            return formatted.equals(new StringBuilder(formatted).reverse().toString());
        };

        LocalDate nextPalindrome = aDate.with(next(isPalindrome));

        Assertions.assertThat(nextPalindrome).isEqualTo(LocalDate.of(2020, 2, 2));
    }

    @Test
    public void testNext_oldSchool_bookExample() throws Exception {
        LocalDate aDate = LocalDate.of(2001, 9, 11);

        LocalDate nextWorkDay = aDate.with(next_oldschool(w -> w.getDayOfWeek().getValue() < 6));

        Assertions.assertThat(nextWorkDay).isEqualTo(LocalDate.of(2001, 9, 12));
    }

    @Test
    public void testNext_oldSchool_palindromeDay() throws Exception {
        LocalDate aDate = LocalDate.of(2015, 10, 10);

        Predicate<LocalDate> isPalindrome = (d) -> {
            String formatted = DateTimeFormatter.BASIC_ISO_DATE.format(d);
            return formatted.equals(new StringBuilder(formatted).reverse().toString());
        };

        LocalDate nextPalindrome = aDate.with(next_oldschool(isPalindrome));

        Assertions.assertThat(nextPalindrome).isEqualTo(LocalDate.of(2020, 2, 2));
    }
}