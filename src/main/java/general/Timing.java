package general;

import chapter2.Ex1;

import java.util.concurrent.Callable;

public class Timing<T> {
    private final T result;
    private final long duration;

    public static <T> Timing<T> time(Callable<T> call) throws Exception {
        long startTime = System.nanoTime();
        T result = call.call();
        long endTime = System.nanoTime();

        long duration = (endTime - startTime);
        return new Timing<>(result, duration);
    }

    private Timing(T result, long duration) {
        this.result = result;
        this.duration = duration;
    }

    public T getResult() {
        return result;
    }

    public long getNanoDuration() {
        return duration;
    }
}
