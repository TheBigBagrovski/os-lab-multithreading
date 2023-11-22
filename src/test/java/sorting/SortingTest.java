package sorting;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static sorting.SortStarter.createTestArray;

public class SortingTest {

    @Test
    public void testSorting_commonCase() {
        for (int i = 0; i < 100; i++) {
            int[] testArray = createTestArray(10000);
            int[] expected = Arrays.stream(testArray).sorted().toArray();
            Sorter.shakerSort(testArray);
            assertArrayEquals(expected, testArray);
        }
    }

    @Test
    public void testSorting_empty() {
        int[] testArray = createTestArray(0);
        int[] expected = Arrays.stream(testArray).sorted().toArray();
        Sorter.shakerSort(testArray);
        assertArrayEquals(expected, testArray);
    }

    @Test
    public void testSorting_singleElement() {
        int[] testArray = createTestArray(1);
        int[] expected = Arrays.stream(testArray).sorted().toArray();
        Sorter.shakerSort(testArray);
        assertArrayEquals(expected, testArray);
    }

    @Test
    public void testSinglethreadSorting_commonCase() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            int[] testArray = createTestArray(10000);
            int[] expected = Arrays.stream(testArray).sorted().toArray();
            int[] actual = SortStarter.multithreadSort(1, testArray);
            assertArrayEquals(expected, actual);
        }
    }

    @Test
    public void testSinglethreadSorting_empty() throws InterruptedException {
        int[] testArray = createTestArray(0);
        int[] expected = Arrays.stream(testArray).sorted().toArray();
        int[] actual = SortStarter.multithreadSort(1, testArray);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testSinglethreadSorting_singleElement() throws InterruptedException {
        int[] testArray = createTestArray(1);
        int[] expected = Arrays.stream(testArray).sorted().toArray();
        int[] actual = SortStarter.multithreadSort(1, testArray);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testMultithreadSorting_commonCase() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            int[] testArray = createTestArray(10000);
            int[] expected = Arrays.stream(testArray).sorted().toArray();
            int[] actual = SortStarter.multithreadSort(4, testArray);
            assertArrayEquals(expected, actual);
        }
    }

    @Test
    public void testMultithreadSorting_empty() throws InterruptedException {
        int[] testArray = createTestArray(0);
        int[] expected = Arrays.stream(testArray).sorted().toArray();
        int[] actual = SortStarter.multithreadSort(4, testArray);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testMultithreadSorting_singleElement() throws InterruptedException {
        int[] testArray = createTestArray(1);
        int[] expected = Arrays.stream(testArray).sorted().toArray();
        int[] actual = SortStarter.multithreadSort(4, testArray);
        assertArrayEquals(expected, actual);
    }

}
