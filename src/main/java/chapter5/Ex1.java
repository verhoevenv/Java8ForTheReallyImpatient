package chapter5;

import java.time.LocalDate;
import java.time.Year;

public class Ex1 {
    public static LocalDate programmersDay(int yearInt) {
        return Year.of(yearInt).atDay(256);
    }
}
