package matrix;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

import java.util.concurrent.TimeUnit;

public class MatrixBenchmarks {

    @State(Scope.Benchmark)
    public static class MyState {
        @Param({"10", "100", "1000"})
        public int size;
    }

    @Benchmark
    @Fork(1)
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    @Warmup(iterations = 10, time = 1)
    @Measurement(iterations = 10, time = 1)
    public void benchmark_1Thread(MyState state) {
        Matrix m1 = new Matrix(state.size, state.size, true);
        Matrix m2 = new Matrix(state.size, state.size, true);
        MatrixMultiplier mm = new SingleThreadedMatrixMultiplier();
        mm.multiplyMatrices(m1, m2);
    }

    @Benchmark
    @Fork(1)
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    @Warmup(iterations = 10, time = 1)
    @Measurement(iterations = 10, time = 1)
    public void benchmark_2Threads(MyState state) {
        Matrix m1 = new Matrix(state.size, state.size, true);
        Matrix m2 = new Matrix(state.size, state.size, true);
        MatrixMultiplier mm = new MultiThreadedMatrixMultiplier(2);
        mm.multiplyMatrices(m1, m2);
    }

    @Benchmark
    @Fork(1)
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    @Warmup(iterations = 10, time = 1)
    @Measurement(iterations = 10, time = 1)
    public void benchmark_4Threads(MyState state) {
        Matrix m1 = new Matrix(state.size, state.size, true);
        Matrix m2 = new Matrix(state.size, state.size, true);
        MatrixMultiplier mm = new MultiThreadedMatrixMultiplier(4);
        mm.multiplyMatrices(m1, m2);
    }

    @Benchmark
    @Fork(1)
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    @Warmup(iterations = 10, time = 1)
    @Measurement(iterations = 10, time = 1)
    public void benchmark_8Threads(MyState state) {
        Matrix m1 = new Matrix(state.size, state.size, true);
        Matrix m2 = new Matrix(state.size, state.size, true);
        MatrixMultiplier mm = new MultiThreadedMatrixMultiplier(8);
        mm.multiplyMatrices(m1, m2);
    }

    @Benchmark
    @Fork(1)
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    @Warmup(iterations = 10, time = 1)
    @Measurement(iterations = 10, time = 1)
    public void benchmark_12Threads(MyState state) {
        Matrix m1 = new Matrix(state.size, state.size, true);
        Matrix m2 = new Matrix(state.size, state.size, true);
        MatrixMultiplier mm = new MultiThreadedMatrixMultiplier(12);
        mm.multiplyMatrices(m1, m2);
    }

}