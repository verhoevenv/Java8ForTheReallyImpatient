package chapter5;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Ex6 {

    public static void main(String[] args) {
        allFriday13th().forEach(System.out::println);
    }

    public static List<LocalDate> allFriday13th() {
        List<LocalDate> result = new ArrayList<>();
        for (YearMonth m = YearMonth.of(1900, 1); m.isBefore(YearMonth.of(2001, 1)); m = m.plusMonths(1)) {
            LocalDate thirteenth = m.atDay(13);
            if(thirteenth.getDayOfWeek().equals(DayOfWeek.FRIDAY)) {
                result.add(thirteenth);
            }
        }
        return result;
    }

}
