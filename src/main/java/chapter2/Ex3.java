package chapter2;

import general.StatisticalTiming;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static general.StatisticalTiming.timeN;

public class Ex3 {
    public static void main(String[] args) throws Exception {
        Path fileLocation = Paths.get(Ex1.class.getResource("/warandpeace.txt").toURI());
        String contents = new String(Files.readAllBytes(fileLocation), StandardCharsets.UTF_8);
        List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));

        //warmup
        IntStream.range(0, 1000).forEach((x) -> {
            getCount_singlethreaded(words);
            getCount_parallel(words);
        });

        StatisticalTiming<Integer> timingSingle = timeN(() -> getCount_singlethreaded(words), 100);
        StatisticalTiming<Integer> timingParallel = timeN(() -> getCount_parallel(words), 100);

        System.out.println(String.format("SINGLE THREAD: Number of long words: %s", timingSingle.summarize()));
        System.out.println(String.format("PARALLEL: Number of long words: %s", timingParallel.summarize()));
    }

    //for comparison...
    private static int getCount_singlethreaded(List<String> words) {
        return (int) words.stream().filter(w -> w.length() > 12).count();
    }

    private static int getCount_parallel(List<String> words) {
        return (int) words.parallelStream().filter(w -> w.length() > 12).count();
    }
}
