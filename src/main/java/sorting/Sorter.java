package sorting;

import lombok.Getter;

@Getter
public class Sorter extends Thread {

    private final int[] arrayToSort;

    /**
     * Separate thread, that sorts given array in-place using shaker sorting
     */
    public Sorter(int[] arrayToSort) {
        this.arrayToSort = arrayToSort;
    }

    @Override
    public void run() {
        shakerSort(arrayToSort);
    }

    public static void shakerSort(int[] array) {
        int temp; // вспомогательная переменная, в которую будем заносить значение
        // сортировка массива шейкером
        int leftSide = 0;
        int rightSide = array.length - 1;
        do {
            for (int i = leftSide; i < rightSide; i++) { // идем вправо, ищем макс. значение
                if (array[i] > array[i + 1]) {
                    temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                }
            }
            rightSide--; // уменьшаем количество проходов
            for (int i = rightSide; i > leftSide; i--) { // теперь идём в обратную сторону ищем мин. значение
                if (array[i] < array[i - 1]) {
                    temp = array[i];
                    array[i] = array[i - 1];
                    array[i - 1] = temp;
                }
            }
            leftSide++; // уменьшаем количество проходов
        } while (leftSide < rightSide);
    }

}
