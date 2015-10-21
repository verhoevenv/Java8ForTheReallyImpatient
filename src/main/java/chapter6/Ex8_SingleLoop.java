package chapter6;

import general.Timing;

import java.util.Arrays;
import java.util.stream.DoubleStream;

public class Ex8_SingleLoop {
    //the naive way of doing things - interesting as a discussion starter
    public static void main(String[] args) {
        for (int size = 1; size < 1000; size++) {
            double[] array = DoubleStream.generate(Math::random).limit(size).toArray();
            double[] copiedArray = Arrays.copyOf(array, size);
            long parallelNanos = Timing.time(() -> Arrays.parallelSort(copiedArray)).getDuration().toNanos();
            long singleNanos = Timing.time(() -> Arrays.sort(array)).getDuration().toNanos();

            System.out.println("Size:" + size);
            System.out.println("Single threaded: " + singleNanos);
            System.out.println("Parallel: " + parallelNanos);
            System.out.println();
            if(singleNanos > parallelNanos) {
                return;
            }
        }
    }
}
