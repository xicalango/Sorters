package com.lsm;

import java.util.Arrays;

public class ListMerger implements ListSortMerger {

    @Override
    public int[] sortMerge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];

        Arrays.sort(right);

        int leftPos = 0;
        int rightPos = 0;

        int insertPos = 0;

        while (leftPos < left.length && rightPos < right.length) {

            int leftVal = left[leftPos];
            int rightVal = right[rightPos];

            if (leftVal == rightVal) {
                result[insertPos] = leftVal;
                result[insertPos + 1] = rightVal;

                leftPos++;
                rightPos++;
                insertPos += 2;
            } else if (leftVal < rightVal) {
                result[insertPos] = leftVal;
                leftPos++;
                insertPos++;
            } else if (rightVal < leftVal) {
                result[insertPos] = rightVal;
                rightPos++;
                insertPos++;
            }
        }

        while (leftPos < left.length) {
            result[insertPos] = left[leftPos];
            insertPos++;
            leftPos++;
        }

        while (rightPos < right.length) {
            result[insertPos] = right[rightPos];
            insertPos++;
            rightPos++;
        }

        return result;
    }

    @Override
    public String getName() {
        return "ListMerger";
    }
}
