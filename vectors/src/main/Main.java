package main;

import vectors.implementations.ArrayVector;
import vectors.implementations.Vectors;
import vectors.interfaces.Vector;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {

        Vector vector1 = new ArrayVector(3, new double[]{1.0, 2.0, 3.0});
        Vector vector2 = new ArrayVector(3, new double[]{4.0, 5.0, 6.0});

        OutputStream file = new FileOutputStream("C:/Users/Nikolay/Desktop/file.txt");
        Vectors.outputVector(vector1, file);
        InputStream file2 = new FileInputStream("C:/Users/Nikolay/Desktop/file.txt");
        Vector res = Vectors.inputVector(file2);

        System.out.print("vector1 from file: " + res.getVectorSize() + " ");
        for (int i = 0; i < res.getVectorSize(); i++) {
            System.out.print(res.getValues()[i] + " ");
        }

        Writer writer = new BufferedWriter(new FileWriter("C:/Users/Nikolay/Desktop/file2.txt"));
        Vectors.writeVector(vector2, writer);
        Reader reader = new BufferedReader(new FileReader("C:/Users/Nikolay/Desktop/file2.txt"));

        System.out.print("\nvector2 from reader: ");
        Vectors.readVector(reader);
    }

}

