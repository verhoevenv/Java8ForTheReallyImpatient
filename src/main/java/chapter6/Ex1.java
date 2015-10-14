package chapter6;

import general.Segmenter;
import general.StatisticalTiming;
import general.ThreadedWorker;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class Ex1 {

    public static void main(String[] args) throws Exception {
        Path fileLocation = Paths.get(Ex1.class.getResource("/alice.txt").toURI());
        String contents = new String(Files.readAllBytes(fileLocation), StandardCharsets.UTF_8);
        List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));

        StatisticalTiming<String> result = StatisticalTiming.timeN(() -> calculateLongest(words), 100);
        //StatisticalTiming<String> result = StatisticalTiming.timeN(() -> calculateLongest_manyReferenceAccessesPerThread(words), 100);

        System.out.println("The longest word is: " + result.summarize());
    }

    private static String calculateLongest(List<String> allWords) throws Exception {
        AtomicReference<String> longest = new AtomicReference<>();

        List<Runnable> tasks = Segmenter.splitInTasks(
                allWords,
                (sublist) -> () -> {
                    String largestForMe = sublist.stream().max(Comparator.comparing(String::length)).get();
                    longest.accumulateAndGet(largestForMe, (w1, w2) -> (w1 != null && w1.length() > w2.length()) ? w1 : w2);
                }
        );

        ThreadedWorker.runEachInSeperateThread(tasks);

        return longest.get();
    }

    private static String calculateLongest_manyReferenceAccessesPerThread(List<String> allWords) throws Exception {
        AtomicReference<String> longest = new AtomicReference<>();

        List<Runnable> tasks = Segmenter.splitInTasks(
                allWords,
                (sublist) -> () -> {
                    for (String s : sublist) {
                        longest.accumulateAndGet(s, (w1, w2) -> (w1 != null && w1.length() > w2.length()) ? w1 : w2);
                    }
                }
        );

        ThreadedWorker.runEachInSeperateThread(tasks);

        return longest.get();
    }



}
