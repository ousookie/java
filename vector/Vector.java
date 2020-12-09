package vector;

import static java.lang.Math.*;

public class Vector {
    private int vectorSize;
    private double[] values;

    public Vector(int vectorSize, double[] values) {
        this.vectorSize = vectorSize;
        this.values = values;
    }

    public double[] getValues() {
        return values;
    }

    public void setValues(double[] values) {
        this.values = values;
    }

    public int getVectorSize() {
        return vectorSize;
    }

    public void setVectorSize(int vectorSize) {
        this.vectorSize = vectorSize;
    }

    public double fastPower(double base, int exp) {
        if (exp == 0) return 1;
        if (exp % 2 == 0) return fastPower(base * base, exp / 2);
        return base * fastPower(base, exp - 1);
    }

    public double euclideanNorm() {
        double sum = 0;
        for (double element : values) {
            sum += fastPower((int) element, 2);
        }
        return sqrt(sum);
    }

    public double maxValue() {
        double max = getValues()[0];
        for (double elem : values) {
            if (elem > max) max = elem;
        }
        return max;
    }

    public double minValue() {
        double min = getValues()[0];
        for (double elem : values) {
            if (elem < min) min = elem;
        }
        return min;
    }

    public void mulByANum(double num) {
        double[] newValues = new double[vectorSize];
        for (int i = 0; i < vectorSize; i++) {
            newValues[i] = values[i] * num;
        }
        setValues(newValues);
    }

    public void quickSort(int lowIndex, int highIndex) {
        int i = lowIndex;
        int j = highIndex;
        int middleIndex = lowIndex + (highIndex - lowIndex) / 2;
        double pivot = values[middleIndex];
        while (i <= j) {
            while (values[i] < pivot) {
                i++;
            }
            while (values[j] > pivot) {
                j--;
            }
            if (i <= j) {
                double temp = values[i];
                values[i] = values[j];
                values[j] = temp;
            }
            i++;
            j--;
        }
        if (lowIndex < j) {
            quickSort(lowIndex, j);
        }

        if (highIndex > i) {
            quickSort(i, highIndex);
        }
    }

    public void add(Vector vector) {
        if (vectorSize != vector.vectorSize) return;
        Vector res = new Vector(vector.vectorSize, new double[vectorSize]);
        for (int i = 0; i < vectorSize; i++) {
            res.values[i] = values[i] + vector.values[i];
        }
        setValues(res.values);
    }

    public double scalar(Vector vector) {
        double scalar = 0;
        for (int i = 0; i < vectorSize; i++) {
            scalar += values[i] * vector.values[i];
        }
        return scalar;
    }
}

