package chapter5;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class Ex5 {

    public static void main(String[] args) {
        System.out.println("What is your birthday? (DD/MM/YYYY)");
        String birthdayString = new Scanner(System.in).nextLine();
        LocalDate birthday = LocalDate.parse(birthdayString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        long daysAlive = daysAlive(birthday);
        System.out.println(String.format("You have been alive for %d %s", daysAlive, daysAlive == 1 ? "day" : "days"));
    }

    public static long daysAlive(LocalDate birthday) {
        LocalDate now = LocalDate.now();
        return daysBetween(birthday, now);
    }

    public static long daysBetween(LocalDate date1, LocalDate date2) {
        return date1.until(date2, ChronoUnit.DAYS);
    }
}
