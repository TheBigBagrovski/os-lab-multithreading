package matrix;

import java.util.List;
import java.util.concurrent.Callable;

public class VectorMultiplier implements Callable<Void> {

    private final Matrix a;
    private final Matrix b;
    private final int start;
    private final int end;
    private final Matrix ans;

    public VectorMultiplier(Matrix a, Matrix b, int start, int end, Matrix ans) {
        this.a = a;
        this.b = b;
        this.start = start;
        this.end = end;
        this.ans = ans;
    }

    public static int multiplyVectors(List<Integer> v1, List<Integer> v2) {
        int result = 0;
        for (int k = 0; k < v1.size(); k++) {
            result += v1.get(k) * v2.get(k);
        }
        return result;
    }

    @Override
    public Void call() {
        for (int i = 0; i < a.getHeight(); i++) {
            for (int j = start; j < end && j < a.getWidth(); j++) {
                int res = multiplyVectors(a.getRow(i), b.getColumn(j));
                synchronized (ans) {
                    ans.setValues(i, j, res);
                }
            }
        }
        return null;
    }
}
