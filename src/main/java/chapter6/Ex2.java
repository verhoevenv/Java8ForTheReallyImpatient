package chapter6;

import general.ThreadedWorker;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.LongAdder;

public class Ex2 {
    static LongAdder idProvider = new LongAdder();

    //run this a few times (might take 10 or so tries depending on load) to notice that duplicate ids are generated
    public static void main(String[] args) throws Exception {
        Set<Long> concurrentIntSet = ConcurrentHashMap.newKeySet();
        ThreadedWorker.runNTimesInThreads(
                () -> concurrentIntSet.add(getNextId()),
                1000
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
}
