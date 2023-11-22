package matrix;

public class SingleThreadedMatrixMultiplier implements MatrixMultiplier{

    @Override
    public Matrix multiplyMatrices(Matrix m1, Matrix m2) {
        if (m1.getWidth() != m2.getHeight()) {
            System.err.println("Illegal matrix parameters");
            return null;
        }
        Matrix result = new Matrix(m1.getHeight(), m2.getWidth());
        for (int i = 0; i < m1.getHeight(); i++) {
            for (int j = 0; j < m2.getWidth(); j++) {
                result.setValues(i, j, VectorMultiplier.multiplyVectors(m1.getRow(i), m2.getColumn(j)));
            }
        }
        return result;
    }
}
