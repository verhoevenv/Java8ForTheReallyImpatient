package chapter5;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Ex2 {
    public static void main(String[] args) {
        LocalDate date = LocalDate.of(2000, 2, 29);

        show("Starting from:", date);
        show("+1 year:", date.plusYears(1));
        show("+4 years:", date.plusYears(4));
        show("4 times +1 year:", date.plusYears(1).plusYears(1).plusYears(1).plusYears(1));
    }

    private static void show(String label, LocalDate localDate) {
        String formatted = DateTimeFormatter.ISO_LOCAL_DATE.format(localDate);
        System.out.println();
        System.out.println(label);
        System.out.println(formatted);
    }
}
