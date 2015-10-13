package chapter6;

import general.ThreadedWorker;
import general.Timing;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

public class Ex3 {
    public static void main(String[] args) throws Exception {
        Timing<Long> timeAtomicLong = Timing.time(Ex3::runWithAtomicLong);
        Timing<Long> timeLongAdder = Timing.time(Ex3::runWithLongAdder);
        System.out.println(String.format("According to atomic long: %d (in %d ms)", timeAtomicLong.getResult(), timeAtomicLong.getDuration().toMillis()));
        System.out.println(String.format("According to long adder: %d (in %d ms)", timeLongAdder.getResult(), timeLongAdder.getDuration().toMillis()));
    }

    private static long runWithLongAdder() {
        LongAdder counter = new LongAdder();
        ThreadedWorker.runNTimesInThreads(
                () -> {
                    for (int i = 0; i < 100_000; i++) {
                        counter.increment();
                    }
                },
                1000
        );
        return counter.sum();
    }

    private static long runWithAtomicLong() {
        AtomicLong counter = new AtomicLong();
        ThreadedWorker.runNTimesInThreads(
                () -> {
                    for (int i = 0; i < 100_000; i++) {
                        counter.incrementAndGet();
                    }
                },
                1000
        );
        return counter.get();
    }
}
