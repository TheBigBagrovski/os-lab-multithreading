package series;

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

public class SeriesBenchmarks {

    @State(Scope.Benchmark)
    public static class MyState {
        @Param({"100", "1000", "10000"})
        public int precision;

        @Param({"80", "500", "2000"})
        public int terms;

        @Setup(Level.Trial)
        public void setup() {
            if ((precision == 100 && terms != 80) ||
                    (precision == 1000 && terms != 500) ||
                    (precision == 10000 && terms != 2000)) {
                throw new IllegalArgumentException("Непарные значения параметров");
            }
        }
    }

    @Benchmark
    @Fork(1)
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    @Warmup(iterations = 10, time = 1)
    @Measurement(iterations = 10, time = 1)
    public void benchmark_1Thread(MyState state) {
        EulerNumberComputer stc = new SingleThreadedEulerComputer();
        stc.calculateEulerNumber(state.terms, state.precision);
    }

    @Benchmark
    @Fork(1)
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    @Warmup(iterations = 10, time = 1)
    @Measurement(iterations = 10, time = 1)
    public void benchmark_2Threads(MyState state) {
        EulerNumberComputer stc = new MultiThreadedEulerComputer(2);
        stc.calculateEulerNumber(state.terms, state.precision);
    }

    @Benchmark
    @Fork(1)
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    @Warmup(iterations = 10, time = 1)
    @Measurement(iterations = 10, time = 1)
    public void benchmark_4Threads(MyState state) {
        EulerNumberComputer stc = new MultiThreadedEulerComputer(4);
        stc.calculateEulerNumber(state.terms, state.precision);
    }

    @Benchmark
    @Fork(1)
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    @Warmup(iterations = 10, time = 1)
    @Measurement(iterations = 10, time = 1)
    public void benchmark_8Threads(MyState state) {
        EulerNumberComputer stc = new MultiThreadedEulerComputer(8);
        stc.calculateEulerNumber(state.terms, state.precision);
    }

    @Benchmark
    @Fork(1)
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    @Warmup(iterations = 10, time = 1)
    @Measurement(iterations = 10, time = 1)
    public void benchmark_12Threads(MyState state) {
        EulerNumberComputer stc = new MultiThreadedEulerComputer(12);
        stc.calculateEulerNumber(state.terms, state.precision);
    }

}
