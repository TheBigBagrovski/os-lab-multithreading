package code;

import java.util.Arrays;
import java.util.Random;

public class Main {

    // размеры используемых массивов
    private static final int[] TESTING_ARRAYS_SIZES = {100, 1000, 10_000, 100_000};

    private static int[] createTestArray(int size) { // создание тестового массива нужного размера
        int[] res = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            res[i] = random.nextInt(size);
        }
        return res;
    }

    public static void main(String[] args) throws InterruptedException {
        for (int size : TESTING_ARRAYS_SIZES) {
            System.out.println("\nРазмер массива: " + size);
            int[] testArray = createTestArray(size); // заполнение массива заданной длины случайными значениями
            long startTime; // начало отсчета
            long elapsedTime; // затраченное время
            // сортировка в одном потоке
            startTime = System.currentTimeMillis();
            ShakeSorter oneThreadSort = new ShakeSorter(testArray);
            oneThreadSort.start();
            oneThreadSort.join();
            elapsedTime = System.currentTimeMillis() - startTime;
            System.out.println("Время сортировки " + size +
                    " элементов, используя " + 1 + " поток: " + (double) elapsedTime / 1000);
            // поочередная сортировка в 2, 4, 8, 16 потоках
            for (int threadPoolSize = 2; threadPoolSize <= 16; threadPoolSize *= 2) {
                startTime = System.currentTimeMillis(); // начало отсчёта времени
                // разделение
                int subSize = size / threadPoolSize; // число элементов для одного потока
                int[][] subArr = new int[threadPoolSize - 1][subSize];
                int[] lastSubArr = new int[size - (threadPoolSize - 1) * subSize];
                ShakeSorter[] sortingThreads = new ShakeSorter[threadPoolSize]; // сортирующие потоки
                // сортировка
                // перебираем подмассивы и для каждого выполняем сортировку в отдельном потоке
                for (int subArrIndex = 0; subArrIndex < threadPoolSize - 1; subArrIndex++) {
                    System.arraycopy( //копируем числа из нужного участка testArray в subArr[текущий индекс подмассива]
                            testArray,
                            subSize * subArrIndex,
                            subArr[subArrIndex],
                            0,
                            subSize
                    );
                    sortingThreads[subArrIndex] = new ShakeSorter(subArr[subArrIndex]);
                    sortingThreads[subArrIndex].start();
                }
                System.arraycopy( //копируем оставшиеся данные в последний подмассив
                        testArray,
                        subSize * (threadPoolSize - 1),
                        lastSubArr,
                        0,
                        size - (threadPoolSize - 1) * subSize
                );
                sortingThreads[threadPoolSize - 1] = new ShakeSorter(lastSubArr);
                sortingThreads[threadPoolSize - 1].start();
                // даем каждому потоку время для завершения сортировки
                for (int i = 0; i < threadPoolSize; i++) {
                    sortingThreads[i].join();
                }
                // возвращаем отсортированные массивы в subArr
                for (int i = 0; i < threadPoolSize - 1; i++) {
                    System.arraycopy(
                            sortingThreads[i].getArrayToSort(),
                            0,
                            subArr[i],
                            0,
                            subSize
                    );
                }
                System.arraycopy(
                        sortingThreads[threadPoolSize - 1].getArrayToSort(),
                        0,
                        lastSubArr,
                        0,
                        size - (threadPoolSize - 1) * subSize
                );
                // слияние
                int lastSize = subSize;
                int numberOfArraysForLastStage = threadPoolSize;
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
                // вывод в консоль затраченного на сортировку времени
                elapsedTime = System.currentTimeMillis() - startTime; // конец подсчёта времени
                System.out.println("Время сортировки " + size +
                        " элементов, используя " + threadPoolSize + " потоков: " + (double) elapsedTime / 1000);
            }
        }
    }

}
