package chapter5;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Ex4 {
    public static void main(String[] args) {
        YearMonth month;
        if(args.length < 2) {
            month = YearMonth.now();
        } else {
            int monthInt = Integer.parseInt(args[0]);
            int yearInt = Integer.parseInt(args[1]);
            month = YearMonth.of(yearInt, monthInt);
        }

        System.out.println(cal(month));
    }

    public static String cal(YearMonth month) {
        List<Optional<Integer>> daysWithPadding = getDaysWithEmptiesInFirstWeek(month);
        List<String> days = formatDaysAsStrings(daysWithPadding);
        List<List<String>> weeks = splitInWeekSublists(days);
        return formatAsCalendar(weeks);
    }

    private static List<Optional<Integer>> getDaysWithEmptiesInFirstWeek(YearMonth month) {
        List<Optional<Integer>> daysWithPadding = new ArrayList<>();
        int emptyDaysBefore = month.atDay(1).getDayOfWeek().getValue() - 1;
        for(int d=0; d<emptyDaysBefore; d++) {
            daysWithPadding.add(Optional.empty());
        }
        for(int d=1; d<= month.lengthOfMonth(); d++) {
            daysWithPadding.add(Optional.of(d));
        }
        return daysWithPadding;
    }

    private static List<String> formatDaysAsStrings(List<Optional<Integer>> daysWithPadding) {
        return daysWithPadding.stream()
                .map((Optional<Integer> day) -> day
                        .map((Integer i) -> String.format("%2s", i)).orElse("  "))
                .collect(Collectors.toList());
    }

    private static List<List<String>> splitInWeekSublists(List<String> days) {
        List<List<String>> weeks = new ArrayList<>();
        int day = 0;
        while(day < days.size()) {
            weeks.add(days.subList(day, Math.min(day + 7, days.size())));
            day += 7;
        }
        return weeks;
    }


    private static String formatAsCalendar(List<List<String>> weeks) {
        return weeks.stream().map(l -> l.stream().collect(Collectors.joining(" "))).collect(Collectors.joining("\n"));
    }
}
