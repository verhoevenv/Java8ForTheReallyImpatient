package general;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class Segmenter {

    public static <T> List<Runnable> splitInTasks(List<T> allInput, Function<List<T>, Runnable> taskFactory) {
        return splitInTasks(allInput, taskFactory, Runtime.getRuntime().availableProcessors());
    }

    public static <T> List<Runnable> splitInTasks(List<T> allInput, Function<List<T>, Runnable> taskFactory, int numSegments) {
        int inputPerSegment = allInput.size() / numSegments;

        List<Runnable> tasks = new ArrayList<>();
        for (int i = 0; i < numSegments - 1; i++) {
            List<T> sublist = allInput.subList(inputPerSegment * i, inputPerSegment * (i + 1));
            Runnable task = taskFactory.apply(sublist);
            tasks.add(task);
        }
        List<T> sublist = allInput.subList(inputPerSegment * (numSegments - 1), allInput.size());
        Runnable task = taskFactory.apply(sublist);
        tasks.add(task);
        return tasks;
    }

    public static <T> List<Runnable> splitInTasks(long[] allInput, Function<long[], Runnable> taskFactory) {
        return splitInTasks(allInput, taskFactory, Runtime.getRuntime().availableProcessors());
    }

    public static List<Runnable> splitInTasks(long[] allInput, Function<long[], Runnable> taskFactory, int numSegments) {
        int inputPerSegment = allInput.length / numSegments;

        List<Runnable> tasks = new ArrayList<>();
        for (int i = 0; i < numSegments - 1; i++) {
            long[] sublist = Arrays.copyOfRange(allInput, inputPerSegment * i, inputPerSegment * (i + 1));
            Runnable task = taskFactory.apply(sublist);
            tasks.add(task);
        }
        long[] sublist = Arrays.copyOfRange(allInput, inputPerSegment * (numSegments - 1), allInput.length);
        Runnable task = taskFactory.apply(sublist);
        tasks.add(task);
        return tasks;
    }
}
