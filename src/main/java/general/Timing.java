package general;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.Callable;

public class Timing<T> {
    private final T result;
    private final Duration duration;

    public static <T> Timing<T> time(Callable<T> call) throws Exception {
        long startTime = System.nanoTime();
        T result = call.call();
        long endTime = System.nanoTime();

        long nanosElapsed = (endTime - startTime);
        Duration duration = Duration.of(nanosElapsed, ChronoUnit.NANOS);
        return new Timing<>(result, duration);
    }

    public static Timing<Void> time(Runnable call) {
        long startTime = System.nanoTime();
        call.run();
        long endTime = System.nanoTime();

        long nanosElapsed = (endTime - startTime);
        Duration duration = Duration.of(nanosElapsed, ChronoUnit.NANOS);
        return new Timing<>(null, duration);
    }

    private Timing(T result, Duration duration) {
        this.result = result;
        this.duration = duration;
    }

    public T getResult() {
        return result;
    }

    public Duration getDuration() {
        return duration;
    }
}
