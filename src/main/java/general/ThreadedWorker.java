package general;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class ThreadedWorker {
    public static <T> void runInThreads(List<T> allInput, Function<List<T>, Runnable> taskFactory, int numThreads) throws Exception {
        ExecutorService pool = Executors.newFixedThreadPool(numThreads);

        int inputPerSegment = allInput.size() / numThreads;

        for (int i = 0; i < numThreads; i++) {
            List<T> sublist = allInput.subList(inputPerSegment * i, inputPerSegment * (i + 1));
            pool.execute(taskFactory.apply(sublist));
        }
        pool.shutdown();

        if(!pool.awaitTermination(10, TimeUnit.SECONDS)) {
            throw new RuntimeException("I wasn't done yet :(");
        }

    }
}
