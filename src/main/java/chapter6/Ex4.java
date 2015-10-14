package chapter6;

import general.Segmenter;
import general.ThreadedWorker;

import java.util.concurrent.atomic.LongAccumulator;

public class Ex4 {

    public static long calculatemax(long[] elements) throws Exception {
        LongAccumulator accumulator = new LongAccumulator(Long::max, Long.MIN_VALUE);

        return accumulate(elements, accumulator);
    }

    public static long calculatemin(long[] elements) throws Exception {
        LongAccumulator accumulator = new LongAccumulator(Long::min, Long.MAX_VALUE);

        return accumulate(elements, accumulator);
    }

    private static long accumulate(long[] elements, LongAccumulator accumulator) {
        ThreadedWorker.runEachInSeperateThread(
                Segmenter.splitInTasks(
                        elements,
                        (sublist) -> () -> {
                            for (long l : sublist) {
                                accumulator.accumulate(l);
                            }
                        }
                )
        );

        return accumulator.get();
    }
}
