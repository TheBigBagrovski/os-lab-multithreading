package matrix;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

@Getter
@EqualsAndHashCode
public class Matrix {

    private final int height;
    private final int width;
    private final List<List<Integer>> values;

    public Matrix(int height, int width) {
        this.height = height;
        this.width = width;
        values = new ArrayList<>();
        for (int i = 0; i < height; i++) {
            List<Integer> newValues = new ArrayList<>();
            for (int j = 0; j < width; j++) {
                newValues.add(0);
            }
            values.add(newValues);
        }
    }

    public Matrix(int height, int width, List<List<Integer>> values) {
        this.height = height;
        this.width = width;
        this.values = values;
    }

    public Matrix(int height, int width, boolean fillRandomly) {
        this.height = height;
        this.width = width;
        values = new ArrayList<>();
        if (fillRandomly) {
            for (int i = 0; i < height; i++) {
                List<Integer> newValues = new ArrayList<>();
                for (int j = 0; j < width; j++) {
                    newValues.add(ThreadLocalRandom.current().nextInt(10000));
                }
                values.add(newValues);
            }
        }
    }

    public void setValues(int x, int y, int val) {
        values.get(x).set(y, val);
    }

    public List<Integer> getColumn(int column) {
        List<Integer> result = new ArrayList<>();
        values.forEach(row -> result.add(row.get(column)));
        return result;
    }

    public List<Integer> getRow(int row) {
        return new ArrayList<>(values.get(row));
    }

}
