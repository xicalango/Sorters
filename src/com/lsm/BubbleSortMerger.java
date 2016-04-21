package com.lsm;

public class BubbleSortMerger implements ListSortMerger {

    private final InsertPosition insertPosition;

    public BubbleSortMerger(InsertPosition insertPosition) {
        this.insertPosition = insertPosition;
    }

    /**
     * Taken from https://en.wikipedia.org/wiki/Bubble_sort#Optimizing_bubble_sort
     *
     * @param array array to sort in-place
     */
    private static void bubbleSort(int[] array) {

        int n = array.length;

        do {
            int newN = 0;

            for (int i = 1; i < n; i++) {
                if (array[i - 1] > array[i]) {
                    swap(array, i - 1, i);
                    newN = i;
                }
            }

            n = newN;
        } while (n != 0);

    }

    private static void swap(int[] array, int i1, int i2) {
        int tmp = array[i1];
        array[i1] = array[i2];
        array[i2] = tmp;
    }

    @Override
    public int[] sortMerge(int[] left, int[] right) {
        int[] result = ListSortMerger.createResultArray(insertPosition, left, right);
        bubbleSort(result);
        return result;
    }

    @Override
    public String getName() {
        return "BubbleSort-" + insertPosition;
    }

}
