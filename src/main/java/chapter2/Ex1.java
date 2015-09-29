package chapter2;

import general.Timing;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static general.Timing.time;

public class Ex1 {
    public static void main(String[] args) throws Exception {
        Path fileLocation = Paths.get(Ex1.class.getResource("/alice.txt").toURI());
        String contents = new String(Files.readAllBytes(fileLocation), StandardCharsets.UTF_8);
        List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));

        Timing<Integer> timing = time(() -> getCount_multithreaded(words));

        //and/or...
        //Timing<Integer> timing = time(() -> getCount_singlethreaded(words));
        //Timing<Integer> timing = time(() -> getCount_singlethreaded_streams(words));
        //Timing<Integer> timing = time(() -> getCount_multithreaded_streams(words));

        System.out.println(String.format("Number of long words: %d (in %d μs)", timing.getResult(), timing.getNanoDuration() / 1000));
    }

    private static int getCount_singlethreaded(List<String> words) {
        int count = 0;
        for (String w : words) {
            if (w.length() > 12) count++;
        }
        return count;
    }

    private static int getCount_multithreaded(List<String> words) throws Exception {
        int cores = Runtime.getRuntime().availableProcessors();
        ExecutorService pool = Executors.newFixedThreadPool(cores);

        int wordsPerSegment = words.size() / cores;

        Set<Future<Integer>> results = new HashSet<>();

        for (int i = 0; i < cores; i++) {
            List<String> sublist = words.subList(wordsPerSegment * i, wordsPerSegment * (i + 1));

            Future<Integer> future = pool.submit(() -> {
                int count = 0;
                for (String w : sublist) {
                    if (w.length() > 12) count++;
                }
                return count;
            });
            results.add(future);
        }

        int result = 0;
        for (Future<Integer> calcResult : results) {
            result += calcResult.get();
        }
        pool.shutdown();
        return result;
    }


    //for comparison...
    private static int getCount_singlethreaded_streams(List<String> words) {
        return (int) words.stream().filter(w -> w.length() > 12).count();
    }

    private static int getCount_multithreaded_streams(List<String> words) {
        return (int) words.parallelStream().filter(w -> w.length() > 12).count();
    }
}


