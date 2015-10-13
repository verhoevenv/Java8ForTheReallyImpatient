package general;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class ThreadedWorker {
    public static <T> void runInThreads(List<T> allInput, Function<List<T>, Runnable> taskFactory, int numThreads) {
        runInThreads(splitInTasks(allInput, taskFactory, numThreads), numThreads);
    }

    private static <T> List<Runnable> splitInTasks(List<T> allInput, Function<List<T>, Runnable> taskFactory, int numThreads) {
        int inputPerSegment = allInput.size() / numThreads;

        List<Runnable> tasks = new ArrayList<>();
        for (int i = 0; i < numThreads; i++) {
            List<T> sublist = allInput.subList(inputPerSegment * i, inputPerSegment * (i + 1));
            Runnable task = taskFactory.apply(sublist);
            tasks.add(task);
        }
        return tasks;
    }

    public static void runInThreads(List<Runnable> tasks, int numThreads) {
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
