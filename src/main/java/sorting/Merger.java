package sorting;

import lombok.Getter;

public class Merger extends Thread {

    private final int[] firstArray;
    private final int[] secondArray;
    /**
     * -- GETTER --
     *  Get merged array
     */
    @Getter
    private final int[] resultArray;

    /**
     * Merges two sorted arrays in one sorted array
     */
    public Merger(int[] firstArray, int[] secondArray) {
        this.firstArray = firstArray;
        this.secondArray = secondArray;
        this.resultArray = new int[firstArray.length + secondArray.length];
    }

    @Override
    public void run() {
        merge();
    }

    private void merge() {
        int firstArrayPointer = 0;
        int secondArrayPointer = 0;
        int resultIndex = 0;
        while (firstArrayPointer < firstArray.length && secondArrayPointer < secondArray.length) {
            if (firstArray[firstArrayPointer] <= secondArray[secondArrayPointer]) {
                resultArray[resultIndex++] = firstArray[firstArrayPointer++];
            } else {
                resultArray[resultIndex++] = secondArray[secondArrayPointer++];
            }
        }
        if (firstArrayPointer == firstArray.length) { // переносим оставшиеся элементы
            while (secondArrayPointer < secondArray.length) {
                resultArray[resultIndex++] = secondArray[secondArrayPointer++];
            }
        } else if (secondArrayPointer == secondArray.length) {
            while (firstArrayPointer < firstArray.length) {
                resultArray[resultIndex++] = firstArray[firstArrayPointer++];
            }
        }
    }
}
