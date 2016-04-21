package com.lsm;

import java.util.Arrays;

public class ArraysSortMerger implements ListSortMerger {

    private final InsertPosition insertPosition;

    public ArraysSortMerger(InsertPosition insertPosition) {
        this.insertPosition = insertPosition;
    }

    @Override
    public int[] sortMerge(int[] left, int[] right) {
        int[] result = ListSortMerger.createResultArray(insertPosition, left, right);
        Arrays.sort(result);
        return result;
    }

    @Override
    public String getName() {
        return "Arrays.sort-" + insertPosition;
    }
}
