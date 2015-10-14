package general;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ThreadedWorker {
    public static void runNTimesInThreads(Runnable task, int numThreads) {
        List<Runnable> tasks = Stream.generate(() -> task).limit(numThreads).collect(Collectors.toList());
        runEachInSeperateThread(tasks);
    }

    public static void runEachInSeperateThread(List<Runnable> tasks) {
        runInThreads(tasks, tasks.size());
    }

    private static void runInThreads(List<Runnable> tasks, int numThreads) {
        ExecutorService pool = Executors.newFixedThreadPool(numThreads);
        tasks.forEach(pool::execute);

        pool.shutdown();

        try {
            if(!pool.awaitTermination(10, TimeUnit.SECONDS)) {
                throw new RuntimeException("I wasn't done yet :(");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException("Dammit checked exceptions", e);
        }
    }
}
