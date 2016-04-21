package com.lsm;

import java.util.Random;
import java.util.function.IntUnaryOperator;
import java.util.stream.Stream;

import static java.util.concurrent.TimeUnit.NANOSECONDS;

public class SortBenchmark {

    private final static int NUM_ITER = 5;

    private final static ListSortMerger[] SORT_MERGERS = {
            new ListMerger(),
            new ArraysSortMerger(InsertPosition.FRONT),
            new ArraysSortMerger(InsertPosition.BACK),
            new ArraysSortMerger(InsertPosition.MIDDLE),
            new BubbleSortMerger(InsertPosition.FRONT),
            new BubbleSortMerger(InsertPosition.BACK),
            new BubbleSortMerger(InsertPosition.MIDDLE),
    };

    private static void printLine(Object... data) {
        String[] strings = Stream.of(data).map(Object::toString).toArray(String[]::new);
        String joined = String.join(", ", (CharSequence[]) strings);
        System.out.println(joined);
    }

    private static void benchmark(ListSortMerger sortMerger, int leftSize, int rightSize) {

        for (int run = 0; run < NUM_ITER; run++) {
            Random r = new Random(42);

            int[] left = createArray(leftSize, i -> i);
            int[] right = createArray(rightSize, i -> r.nextInt());

            long start = System.nanoTime();
            int[] result = sortMerger.sortMerge(left, right);
            long end = System.nanoTime();

            boolean isSorted = checkSorted(result);

            long elapsedTimeMs = NANOSECONDS.toMillis(end - start);

            printLine(sortMerger.getName(), leftSize, rightSize, run, elapsedTimeMs, isSorted);
        }
    }

    private static boolean checkSorted(int[] result) {
        for (int i = 1; i < result.length; i++) {
            if (result[i - 1] > result[i]) {
                return false;
            }
        }

        return true;
    }

    private static int[] createArray(int leftSize, IntUnaryOperator initOp) {
        int[] result = new int[leftSize];
        for (int i = 0; i < result.length; i++) {
            result[i] = initOp.applyAsInt(i);
        }
        return result;
    }

    public static void main(String[] args) {
        printLine("SortMerger", "leftArraySize", "rightArraySize", "run", "execTimeMs", "resultIsSorted");

        for (int leftSize : new int[]{1000, 10_000, 100_000}) {
            for (int rightSize : new int[]{1, 50, 100, 1000}) {
                for (ListSortMerger sortMerger : SORT_MERGERS) {
                    benchmark(sortMerger, leftSize, rightSize);
                }
            }
        }
    }
}
