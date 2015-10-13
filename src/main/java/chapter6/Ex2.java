package chapter6;

import general.ThreadedWorker;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Ex2 {
    static LongAdder idProvider = new LongAdder();

    //run this a few times (might take 10 or so tries depending on load) to notice that duplicate ids are generated
    public static void main(String[] args) throws Exception {
        List<Integer> sourceList = listOfSize(1000);
        Set<Long> concurrentIntSet = ConcurrentHashMap.newKeySet();
        ThreadedWorker.runInThreads(
                sourceList,
                (x) -> () -> concurrentIntSet.add(getNextId()),
                sourceList.size()
        );

        System.out.println("Expecting 1000 different ids to be generated, actual number was: " + concurrentIntSet.size());
    }

    //my feelings in haiku form:
    //
    //code not atomic
    //also has bad performance
    //don't do this at work
    public static long getNextId() {
        idProvider.increment();
        return idProvider.sum();
    }

    private static List<Integer> listOfSize(int numThreads) {
        return IntStream.range(0, numThreads).boxed().collect(Collectors.toList());
    }
}
