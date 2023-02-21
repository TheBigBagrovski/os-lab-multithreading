package code;

import static code.ShakerSort.shakerSort;

public class ShakeSorter extends Thread {
    private final int[] arrayToSort;

    public ShakeSorter(int[] arrayToSort) {
        this.arrayToSort = arrayToSort;
    }
    @Override
    public void run() {
        shakerSort(arrayToSort);
    }
    public int[] getArrayToSort() {
        return arrayToSort;
    }
}
