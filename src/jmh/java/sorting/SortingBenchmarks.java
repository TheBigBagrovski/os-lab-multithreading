package sorting;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

import java.util.concurrent.TimeUnit;

import static sorting.SortStarter.createTestArray;

public class SortingBenchmarks {

    @State(Scope.Benchmark)
    public static class MyState {
        // размеры используемых массивов
        @Param({"100", "1000", "10000", "100000", "1000000"})
        public int size;

        public int[] testArray;

        @Setup(Level.Invocation)
        public void doSetup() {
            testArray = createTestArray(size);
        }

    }

    @Benchmark
    @Fork(1)
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    @Warmup(iterations = 10, time = 1)
    @Measurement(iterations = 10, time = 1)
    public void benchmark_1Thread(MyState state) throws InterruptedException {
        SortStarter.multithreadSort(1, state.testArray);
    }

    @Benchmark
    @Fork(1)
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    @Warmup(iterations = 10, time = 1)
    @Measurement(iterations = 10, time = 1)
    public void benchmark_2Threads(MyState state) throws InterruptedException {
        SortStarter.multithreadSort(2, state.testArray);
    }

    @Benchmark
    @Fork(1)
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    @Warmup(iterations = 10, time = 1)
    @Measurement(iterations = 10, time = 1)
    public void benchmark_4Threads(MyState state) throws InterruptedException {
        SortStarter.multithreadSort(4, state.testArray);
    }

    @Benchmark
    @Fork(1)
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    @Warmup(iterations = 10, time = 1)
    @Measurement(iterations = 10, time = 1)
    public void benchmark_8Threads(MyState state) throws InterruptedException {
        SortStarter.multithreadSort(8, state.testArray);
    }

    @Benchmark
    @Fork(1)
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    @Warmup(iterations = 10, time = 1)
    @Measurement(iterations = 10, time = 1)
    public void benchmark_16Threads(MyState state) throws InterruptedException {
        SortStarter.multithreadSort(16, state.testArray);
    }

}
