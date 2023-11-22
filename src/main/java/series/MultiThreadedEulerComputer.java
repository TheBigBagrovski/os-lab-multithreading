package series;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MultiThreadedEulerComputer implements EulerNumberComputer {

    private final int threadsNumber;
    private BigDecimal E = zero;
    private final Object lock = new Object();
    private final Map<Integer, BigDecimal> factorials = new ConcurrentHashMap<>();
    private int max = 1;

    public MultiThreadedEulerComputer(int threadsNumber) {
        this.threadsNumber = threadsNumber;
    }

    @Override
    public BigDecimal calculateEulerNumber(int terms, int precision) {
        MathContext mc = new MathContext(precision);
        ArrayList<PartialSumComputer> threads = new ArrayList<>();
        for (int i = 0; i < threadsNumber; i++) {
            PartialSumComputer thread = new PartialSumComputer(i, threadsNumber, terms, mc);
            threads.add(thread);
            thread.start();
        }
        for (PartialSumComputer thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e1) {
                Thread.currentThread().interrupt();
            }
        }
        return E;
    }

    private class PartialSumComputer extends Thread {

        private final int step;
        private final int start;
        private final int end;
        private final MathContext mc;

        public PartialSumComputer(int start, int step, int end, MathContext mc) {
            this.step = step;
            this.start = start;
            this.end = end;
            this.mc = mc;
        }

        @Override
        public void run() {
            BigDecimal partialSum = zero;
            BigDecimal fact;
            int m;
            for (int i = start; i < end; i += step) {
                m = i * 3;
                fact = factorials.get(m);
                if (fact == null) {
                    fact = factorial(m);
                }
                partialSum = partialSum.add(
                        BigDecimal.valueOf(Math.pow(m, 2) + 1).divide(fact, mc),
                        mc
                );
            }
            synchronized (lock) {
                E = E.add(partialSum, mc);
            }
        }

        private BigDecimal factorial(int n) {
            BigDecimal result = one;
            for (int i = max; i <= n; ++i) {
                if (factorials.containsKey(i)) {
                    result = factorials.get(i);
                } else {
                    result = result.multiply(BigDecimal.valueOf(i), mc);
                    factorials.put(i, result);
                    if (i > max) {
                        max = i;
                    }
                }
            }
            return result;
        }

    }

}
