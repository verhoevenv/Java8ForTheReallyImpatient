package chapter5;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Ex3 {

    public static TemporalAdjuster next(Predicate<LocalDate> p) {
        return TemporalAdjusters.ofDateAdjuster(
                (d) -> Stream.iterate(nextDay(d), Ex3::nextDay)
                        .filter(p)
                        .findFirst()
                        .get());
    }

    private static LocalDate nextDay(LocalDate d) {
        return d.plusDays(1);
    }

    //Alternatively, if you don't like infinite streams...
    public static TemporalAdjuster next_oldschool(Predicate<LocalDate> p) {
        return TemporalAdjusters.ofDateAdjuster((d) -> {
            LocalDate currentToTest = nextDay(d);
            while(!p.test(currentToTest)) {
                currentToTest = nextDay(currentToTest);
            }
            return currentToTest;
        });
    }
}
