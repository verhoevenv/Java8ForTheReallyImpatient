package chapter6;

import java.util.Arrays;

public class Ex9 {

    //TODO: timings?

    public static long fibByMatrix(int n) {
        TwoByTwoLongMatrix[] arr = new TwoByTwoLongMatrix[n];
        Arrays.parallelSetAll(arr, (i) -> new TwoByTwoLongMatrix(1, 1, 1, 0));
        Arrays.parallelPrefix(arr, TwoByTwoLongMatrix::multiply);

        return arr[n-1].getE11();
    }
}
