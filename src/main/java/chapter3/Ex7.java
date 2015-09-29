package chapter3;

import java.util.Comparator;
import java.util.function.Function;

public class Ex7 {

    public enum Direction {
        NORMAL, REVERSED
    }

    public enum Casing {
        SENSITIVE, INSENSITIVE
    }

    public enum Spacing {
        SENSITIVE, INSENSITIVE
    }

    public static Comparator<String> createComparator(Direction direction, Casing casing, Spacing spacing) {
        Function<String, String> cf = casing == Casing.SENSITIVE ? Function.identity() : String::toLowerCase;
        Function<String, String> sf = spacing == Spacing.SENSITIVE ? Function.identity() : s -> s.replaceAll("\\s", "");
        Function<String, String> normalize = sf.andThen(cf);

        return (x, y) -> {
            String xForComp = normalize.apply(x);
            String yForComp = normalize.apply(y);

            if(direction == Direction.REVERSED) {
                return yForComp.compareTo(xForComp);
            } else {
                return xForComp.compareTo(yForComp);
            }
        };
    }
}
