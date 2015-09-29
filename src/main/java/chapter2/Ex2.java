package chapter2;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;


public class Ex2 {
    public static void main(String[] args) throws Exception {
        Path fileLocation = Paths.get(Ex1.class.getResource("/alice.txt").toURI());
        String contents = new String(Files.readAllBytes(fileLocation), StandardCharsets.UTF_8);
        List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));

        words.stream().filter(w -> {
            System.out.print(w);
            System.out.print(" ");
            return w.length() > 12;
        }).limit(5).forEach(w -> System.out.print("\nLONG WORD FOUND: " + w + "\n"));

        System.out.println("ALL DONE!");
    }
}
