package chapter1;

import java.util.Arrays;
import java.util.Comparator;

public class Ex1 {
    public static void main(String[] args) {
        String[] arr = new String[] {"ABC", "GEH", "DEF"};

        //The comparator code will be called in the same thread
        Comparator<String> comp = (x, y) -> {
            System.out.println(String.format("Comparator thread: %d", Thread.currentThread().getId()));
            return x.compareTo(y);
        };

        System.out.println(String.format("Main thread: %d", Thread.currentThread().getId()));
        Arrays.sort(arr, comp);
        System.out.println(String.format("Sorting done: %s", Arrays.toString(arr)));
    }
}
