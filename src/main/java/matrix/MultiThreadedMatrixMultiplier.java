package matrix;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MultiThreadedMatrixMultiplier implements MatrixMultiplier {

    private final int threadsNum;

    public MultiThreadedMatrixMultiplier(int threadsNum) {
        this.threadsNum = threadsNum;
    }

    @Override
    public Matrix multiplyMatrices(Matrix m1, Matrix m2) {
        if (m1.getWidth() != m2.getHeight()) {
            System.err.println("Illegal matrix parameters");
            return null;
        }
        Matrix result = new Matrix(m1.getHeight(), m2.getWidth());
        ExecutorService executorService = Executors.newFixedThreadPool(threadsNum);
        List<VectorMultiplier> task = new ArrayList<>();
        for (int j = 0; j < m2.getWidth(); j += threadsNum) {
            task.add(new VectorMultiplier(m1, m2, j, j + threadsNum, result));
        }
        try {
            executorService.invokeAll(task);
            executorService.shutdown();
        } catch (InterruptedException exception) {
            System.err.println("Exception with multiply with treads");
        }
        return result;
    }
}
