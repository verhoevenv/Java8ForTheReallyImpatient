package chapter6;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import static general.Uncheck.uncheck;

public class Ex7 {
    public static String keyWithMaxValue(ConcurrentHashMap<String, Long> map) {
        return map.reduceEntries(1, (e1, e2) -> e1.getValue() > e2.getValue() ? e1 : e2).getKey();
    }

    public static void main(String[] args) throws Exception {
        ConcurrentHashMap<String, Long> map = new ConcurrentHashMap<>();

        List<Runnable> tasks = LongStream.range(1, 200).mapToObj(n -> uncheck(() -> {
            Thread.sleep(n);
            map.put("" + n, n);
        })).collect(Collectors.toList());

        ExecutorService pool = Executors.newFixedThreadPool(tasks.size());
        tasks.forEach(pool::execute);
        pool.shutdown();

        System.out.println(keyWithMaxValue(map));

        Thread.sleep(20);

        System.out.println(keyWithMaxValue(map));

        pool.awaitTermination(10, TimeUnit.SECONDS);

        System.out.println(keyWithMaxValue(map));
    }
}
