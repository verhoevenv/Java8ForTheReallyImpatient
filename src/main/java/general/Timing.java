package general;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
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
