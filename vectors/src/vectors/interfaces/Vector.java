package vectors.interfaces;

import vectors.exceptions.IncompatibleVectorSizesException;
import vectors.exceptions.VectorIndexOutOfBoundsException;
import vectors.implementations.ArrayVector;

public interface Vector {

    public double[] getValues();

    public int getVectorSize();

    public double fastPower(double base, int exp);

    public double euclideanNorm();

    public static ArrayVector mulByANum(ArrayVector vector, double num) throws VectorIndexOutOfBoundsException {
        return null;
    }

    public static ArrayVector add(ArrayVector vector1, ArrayVector vector2) throws IncompatibleVectorSizesException {
        return null;
    }

    public static double scalar(ArrayVector vector1, ArrayVector vector2) throws IncompatibleVectorSizesException {
        return 0;
    }

}
