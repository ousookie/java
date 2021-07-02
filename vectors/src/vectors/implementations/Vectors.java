package vectors.implementations;

import vectors.exceptions.IncompatibleVectorSizesException;
import vectors.interfaces.Vector;
import vectors.exceptions.VectorIndexOutOfBoundsException;

import java.io.*;

public class Vectors implements Vector {

    @Override
    public double[] getValues() {
        return new double[0];
    }

    @Override
    public int getVectorSize() {
        return 0;
    }

    @Override
    public double fastPower(double base, int exp) {
        return 0;
    }

    @Override
    public double euclideanNorm() {
        return 0;
    }

    public static Vector mulByANum(Vector vector, double num) throws VectorIndexOutOfBoundsException {
        if (vector.getVectorSize() != vector.getValues().length) {
            throw new VectorIndexOutOfBoundsException("VectorIndexOutOfBoundsException");
        }
        for (int i = 0; i < vector.getVectorSize(); i++) {
            vector.getValues()[i] *= num;
        }
        return vector;
    }

    public static Vector add(Vector vector1, Vector vector2) throws IncompatibleVectorSizesException {
        if (vector1.getVectorSize() != vector2.getVectorSize()) {
            throw new IncompatibleVectorSizesException("IncompatibleVectorSizesException");
        }
        Vector res = new ArrayVector(vector1.getVectorSize(), new double[vector1.getVectorSize()]);
        for (int i = 0; i < vector1.getVectorSize(); i++) {
            res.getValues()[i] = vector1.getValues()[i] + vector2.getValues()[i];
        }
        return res;
    }

    public static double scalar(Vector vector1, Vector vector2) throws IncompatibleVectorSizesException {
        if (vector1.getVectorSize() != vector2.getVectorSize()) {
            throw new IncompatibleVectorSizesException("IncompatibleVectorSizesException");
        }
        double scalar = 0;
        for (int i = 0; i < vector1.getVectorSize(); i++) {
            scalar += vector1.getValues()[i] * vector2.getValues()[i];
        }
        return scalar;
    }

    public static void outputVector(Vector vector, OutputStream outputStream) throws IOException {
        outputStream.write(vector.getVectorSize());
        for (int i = 0; i < vector.getVectorSize(); i++) {
            outputStream.write((int) vector.getValues()[i]);
        }
        outputStream.flush();
        outputStream.close();
    }

    public static Vector inputVector(InputStream inputStream) throws IOException {
        int vectorSize = inputStream.read();
        Vector vector = new ArrayVector(vectorSize, new double[vectorSize]);
        int i = 0;
        while (inputStream.available() > 0) {
            vector.getValues()[i] = inputStream.read();
            i++;
        }
        inputStream.close();
        return vector;
    }

    public static void writeVector(Vector vector, Writer writer) throws IOException {
        writer.write(vector.getVectorSize());
        for (int i = 0; i < vector.getVectorSize(); i++) {
            writer.write((int) vector.getValues()[i]);
        }
        writer.flush();
        writer.close();
    }

    public static void readVector(Reader reader) throws IOException {
        int chr;
        while ((chr = reader.read()) != -1) {
            System.out.print(chr + " ");
        }
        reader.close();
    }

}

