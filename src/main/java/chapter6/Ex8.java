package chapter6;

import general.StatisticalTiming;
import general.Timing;

import java.nio.file.*;
import java.util.*;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

public class Ex8 {

    public static final int WARM_UP_RUNS = 2000;
    public static final int RUNS_PER_SIZE = 9;

    public static final int SIZE_FROM = 100;
    public static final int SIZE_TO = 50_000;
    public static final int SIZE_STEP = 50;

    public static void main(String[] args) throws Exception {
        int[] sizes = generateSizes(SIZE_FROM, SIZE_TO, SIZE_STEP);
        SortedMap<Integer, Sample> result = new TreeMap<>();

        for (int i = 0; i < WARM_UP_RUNS; i++) {
            timeSortingOfRandomArrayOfSize(200);
        }

        for (int size : sizes) {
            result.put(size, timeSortingOfRandomArrayOfSize(size));
            System.out.println(size);
        }

        writeToCsvFile(result, FileSystems.getDefault().getPath("out", "sorting.csv"));
        //scatterplot can be made with src/main/r/chapter6/ex6.r
        //example output, see out/sorting.png
    }

    private static Sample timeSortingOfRandomArrayOfSize(int arraySize) {
        List<Timing<Void>> singles = new ArrayList<>(RUNS_PER_SIZE);
        List<Timing<Void>> parallels = new ArrayList<>(RUNS_PER_SIZE);

        for (int i = 0; i < RUNS_PER_SIZE; i++) {
            double[] array = DoubleStream.generate(Math::random).limit(arraySize).toArray();
            double[] copiedArray = Arrays.copyOf(array, arraySize);

            singles.add(Timing.time(() -> Arrays.sort(array)));
            parallels.add(Timing.time(() -> Arrays.parallelSort(copiedArray)));
        }

        return new Sample(singles, parallels);
    }

    private static class Sample {
        public final List<Timing<Void>> singles;
        public final List<Timing<Void>> parallels;

        public Sample(List<Timing<Void>> singles, List<Timing<Void>> parallels) {
            this.singles = singles;
            this.parallels = parallels;
        }

        public StatisticalTiming getSingles() {
            return new StatisticalTiming(singles.toArray(new Timing[singles.size()]));
        }

        public StatisticalTiming getParallels() {
            return new StatisticalTiming(parallels.toArray(new Timing[parallels.size()]));
        }
    }

    private static void writeToCsvFile(SortedMap<Integer, Sample> result, Path data) throws Exception {
        List<String> lines = new ArrayList<>();
        lines.add("arrsize\tsorttype\tduration");
        for (Map.Entry<Integer, Sample> e : result.entrySet()) {
            lines.add(String.format("%d\t%s\t%d",e.getKey(), "SINGLE", e.getValue().getSingles().medianNanoDuration()));
            lines.add(String.format("%d\t%s\t%d",e.getKey(), "PARALLEL", e.getValue().getParallels().medianNanoDuration()));
        }

        Files.write(data, lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    private static int[] generateSizes(int sizeFrom, int sizeTo, int sizeStep) {
        int numelements = (sizeTo - sizeFrom) / sizeStep;
        return IntStream.iterate(sizeFrom, x -> x + sizeStep).limit(numelements).toArray();
    }

}
