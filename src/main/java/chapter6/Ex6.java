package chapter6;

import general.ThreadedWorker;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static general.Uncheck.uncheck;

public class Ex6 {
    public static Map<String, Set<File>> occurrences(Set<File> files) {
        ConcurrentHashMap<String, Set<File>> result = new ConcurrentHashMap<>();
        List<Runnable> tasks = files.stream()
                .map(f -> uncheck(
                        () -> wordsFromFile(f).forEach(word -> addWordToResult(result, word, f))
                )).collect(Collectors.toList());
        ThreadedWorker.runEachInSeperateThread(tasks);
        return result;
    }

    private static Stream<String> wordsFromFile(File f) throws IOException {
        return Files.readAllLines(f.toPath()).stream()
                .flatMap(line -> Arrays.stream(line.split(" ")));
    }

    private static void addWordToResult(ConcurrentHashMap<String, Set<File>> result, String s, File f) {
        //no need to create a set before we know whether we need to add one or modify an existing one
        result.computeIfAbsent(s, x -> new HashSet<>()).add(f);
    }

}
