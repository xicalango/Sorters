package com.lsm;

import java.util.Arrays;

public interface ListSortMerger {

    static int[] createResultArray(InsertPosition insertPosition, int[] left, int[] right) {
        if (insertPosition == InsertPosition.BACK) {
            int[] result = Arrays.copyOf(left, left.length + right.length);

            System.arraycopy(right, 0, result, left.length, right.length);
            return result;
        }

        if (insertPosition == InsertPosition.FRONT) {
            int[] result = Arrays.copyOf(right, left.length + right.length);
            System.arraycopy(left, 0, result, right.length, left.length);
            return result;
        }

        assert insertPosition == InsertPosition.MIDDLE : insertPosition;

        int[] result = new int[left.length + right.length];

        int splitPos = left.length / 2;

        System.arraycopy(left, 0, result, 0, splitPos);
        System.arraycopy(right, 0, result, splitPos, right.length);
        System.arraycopy(left, splitPos, result, splitPos + right.length, splitPos);

        return result;


    }

    /**
     * Takes two arrays, combines and sorts them
     *
     * @param left  the "left" array
     * @param right the "right" array
     * @return left appended with right, sorted
     */
    int[] sortMerge(int[] left, int[] right);

    String getName();

}
