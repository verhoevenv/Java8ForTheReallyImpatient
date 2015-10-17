package general;

import org.apache.commons.math3.distribution.TDistribution;

import java.util.Arrays;
import java.util.concurrent.Callable;

import static general.Timing.time;

public class StatisticalTiming<T> {
    private Timing[] results;

    public StatisticalTiming(Timing[] results) {
        this.results = results;
    }

    public static <T> StatisticalTiming<T> timeN(Callable<T> call, int times) throws Exception {
        //warmup
        for(int i = 0; i<500; i++) {
            call.call();
        }

        Timing[] results = new Timing[times];
        for(int i = 0; i<times; i++) {
            results[i] = time(call);
        }
        return new StatisticalTiming<>(results);
    }

    public double avgNanoDuration() {
        return Arrays.stream(results).mapToLong(timing -> timing.getDuration().toNanos()).average().getAsDouble();
    }

    public double stddevDuration() {
        double avg = avgNanoDuration();
        double sumSq = 0;
        for (Timing result : results) {
            sumSq += Math.pow(result.getDuration().toNanos() - avg, 2);
        }
        return Math.sqrt(sumSq / (results.length - 1));
    }

    public long medianNanoDuration() {
        long[] nanos = Arrays.stream(results).mapToLong(timing -> timing.getDuration().toNanos()).toArray();
        if(nanos.length % 2 == 0) {
            return (nanos[nanos.length/2] + nanos[nanos.length/2]) / 2; //small rounding error shoulnd't matter on nano accuracy
        } else {
            return nanos[(nanos.length + 1)/2];
        }
    }

    public void assertAllSameResults() {
        Object firstResult = results[0].getResult();
        if(firstResult == null) return;

        for (Timing result : results) {
            if(!result.getResult().equals(firstResult)) {
                throw new RuntimeException(String.format("Different results: %s, %s", firstResult, result.getResult()));
            }
        }
    }

    //I think this is statistically okay, but yeah, statistics...
    public String summarize() {
        double avg = avgNanoDuration();
        double stderr = stddevDuration() / Math.sqrt(results.length);

        double confidence = 0.95;

        double tVal = new TDistribution(results.length - 1).inverseCumulativeProbability(1.0 - (1 - confidence) / 2);
        double errOnMean = tVal * stderr;

        double lowerBound = avg - errOnMean;
        double upperBound = avg + errOnMean;

        return String.format("%s (in [%.2f - %.2f] ms)", getResult(), lowerBound / 1000000, upperBound / 1000000);
    }

    @SuppressWarnings("unchecked")
    public T getResult() {
        assertAllSameResults();
        return (T) results[0].getResult();
    }
}
