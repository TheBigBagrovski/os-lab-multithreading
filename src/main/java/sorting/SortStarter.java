package sorting;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class SortStarter {

    /**
     * Creates test array of given size with random elements
     */
    public static int[] createTestArray(int size) { // создание тестового массива нужного размера
        int[] res = new int[size];
        for (int i = 0; i < size; i++) {
            res[i] = ThreadLocalRandom.current().nextInt(size);
        }
        return res;
    }

    /**
     * Sorts given array using given number of parallel threads
     * @param threadsNum number of parallel threads
     * @param array array to sort
     * @return sorted array
     */
    public static int[] multithreadSort(int threadsNum, int[] array) throws InterruptedException {
        int size = array.length;
        int subSize = size / threadsNum; // число элементов для одного потока
        int[][] subArr = new int[threadsNum - 1][subSize];
        int[] lastSubArr = new int[size - (threadsNum - 1) * subSize];
        Sorter[] sortingThreads = new Sorter[threadsNum]; // сортирующие потоки
        // сортировка
        // перебираем подмассивы и для каждого выполняем сортировку в отдельном потоке
        for (int subArrIndex = 0; subArrIndex < threadsNum - 1; subArrIndex++) {
            System.arraycopy( //копируем числа из нужного участка testArray в subArr[текущий индекс подмассива]
                    array,
                    subSize * subArrIndex,
                    subArr[subArrIndex],
                    0,
                    subSize
            );
            sortingThreads[subArrIndex] = new Sorter(subArr[subArrIndex]);
            sortingThreads[subArrIndex].start();
        }
        System.arraycopy( //копируем оставшиеся данные в последний подмассив
                array,
                subSize * (threadsNum - 1),
                lastSubArr,
                0,
                size - (threadsNum - 1) * subSize
        );
        sortingThreads[threadsNum - 1] = new Sorter(lastSubArr);
        sortingThreads[threadsNum - 1].start();
        // даем каждому потоку время для завершения сортировки
        for (int i = 0; i < threadsNum; i++) {
            sortingThreads[i].join();
        }
        // возвращаем отсортированные массивы в subArr
        for (int i = 0; i < threadsNum - 1; i++) {
            System.arraycopy(
                    sortingThreads[i].getArrayToSort(),
                    0,
                    subArr[i],
                    0,
                    subSize
            );
        }
        System.arraycopy(
                sortingThreads[threadsNum - 1].getArrayToSort(),
                0,
                lastSubArr,
                0,
                size - (threadsNum - 1) * subSize
        );
        // слияние
        int lastSize = subSize;
        int numberOfArraysForLastStage = threadsNum;
        while (numberOfArraysForLastStage > 1) {
            int numberOfArraysForCurrentStage = numberOfArraysForLastStage / 2;
            Merger[] mergers = new Merger[numberOfArraysForLastStage / 2];
            for (int i = 0; i < numberOfArraysForCurrentStage - 1; i++) {
                mergers[i] = new Merger(subArr[2 * i], subArr[2 * i + 1]);
                mergers[i].start();
            }
            // отдельное слияние для последнего массива
            mergers[numberOfArraysForCurrentStage - 1] =
                    new Merger(subArr[(numberOfArraysForCurrentStage - 1) * 2], lastSubArr);
            mergers[numberOfArraysForCurrentStage - 1].start();
            // даем время для завершения процессов
            for (int i = 0; i < numberOfArraysForCurrentStage; i++) {
                mergers[i].join();
            }
            // уменьшаем массив потоков в двое
            int currentSubSize = 2 * lastSize;
            subArr = Arrays.copyOf(subArr, numberOfArraysForCurrentStage);
            for (int i = 0; i < numberOfArraysForCurrentStage - 1; i++) {
                subArr[i] = Arrays.copyOf(subArr[i], currentSubSize);
            }
            lastSubArr = Arrays.copyOf(lastSubArr, size - currentSubSize * (numberOfArraysForCurrentStage - 1));
            // слияние массивов во вдвое меньшее число массивов
            for (int i = 0; i < numberOfArraysForCurrentStage - 1; i++) {
                System.arraycopy(
                        mergers[i].getResultArray(),
                        0,
                        subArr[i],
                        0,
                        currentSubSize);
            }
            // отдельная обработка последнего массива
            System.arraycopy(
                    mergers[numberOfArraysForCurrentStage - 1].getResultArray(),
                    0,
                    lastSubArr,
                    0,
                    size - currentSubSize * (numberOfArraysForCurrentStage - 1));
            lastSize = currentSubSize;
            numberOfArraysForLastStage = numberOfArraysForCurrentStage;
        }
        return lastSubArr;
    }

}
