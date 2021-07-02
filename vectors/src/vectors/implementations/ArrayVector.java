package vectors.implementations;

import vectors.interfaces.Vector;
import vectors.exceptions.VectorIndexOutOfBoundsException;

import java.io.Serializable;

import static java.lang.Math.sqrt;

public class ArrayVector implements Vector, Serializable {

    private final int vectorSize;
    private final double[] values;

    public ArrayVector(int vectorSize, double[] values) throws VectorIndexOutOfBoundsException {
        if (vectorSize != values.length) {
            throw new VectorIndexOutOfBoundsException(" VectorIndexOutOfBoundsException");
        }
        this.vectorSize = vectorSize;
        this.values = values;
    }

    @Override
    public double[] getValues() {
        return values;
    }

    @Override
    public int getVectorSize() {
        return vectorSize;
    }

    @Override
    public double fastPower(double base, int exp) {
        if (exp == 0) return 1;
        if (exp % 2 == 0) return fastPower(base * base, exp / 2);
        return base * fastPower(base, exp - 1);
    }

    @Override
    public double euclideanNorm() {
        double sum = 0;
        for (double element : values) {
            sum += fastPower(element, 2);
        }
        return sqrt(sum);
    }

}



