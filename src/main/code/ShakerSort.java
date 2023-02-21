package code;

import java.util.Arrays;
import java.util.Random;

public class ShakerSort {

    public static void main(String[] args) {
        // объявление и инициализация тестового массива
        int arraySize = 10;  // размер массива
        int[] array = new int[arraySize];
        Random newRandom = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = newRandom.nextInt(10) + 1;
        }
        System.out.println("Массив до сортировки: " + Arrays.toString(array));
        shakerSort(array);
        // вывод отсортированного массива на экран
        System.out.println("Массив после сортировки: " + Arrays.toString(array));
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
