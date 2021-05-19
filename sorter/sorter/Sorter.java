package sorter;

import java.lang.reflect.Method;

/**
 * Class "Sorter" have some different types of sort.
 *
 * @author Nikolay Dikanskiy
 * @version 1.1
 */
public class Sorter {

    private static double[] values;
    private static int sortMethodsCount;

    static {
        Method[] methods = Sorter.class.getDeclaredMethods();
        for (Method method : methods) {
            sortMethodsCount++;
        }
        sortMethodsCount -= 5;
    }

    /**
     * @return count of sort functions
     */
    public static int getSortMethodsCount() {
        return sortMethodsCount;
    }

    /**
     * @param arrValues: desired values of array
     */
    public static void setValues(double[] arrValues) {
        values = arrValues;
    }

    /**
     * @return array of values
     */
    public static double[] getValues() {
        return values;
    }

    /**
     * "Bubble" sort function.
     *
     * @return null
     */
    public static Void checkBubbleSortTimeInMillis() {
        for (int i = 0; i < values.length; i++) {
            for (int j = 0; j < values.length; j++) {
                if (values[j] > values[i]) {
                    double temp = values[j];
                    values[j] = values[i];
                    values[i] = temp;
                }
            }
        }
        return null;
    }

    /**
     * "Brush" sort function.
     *
     * @return null
     */
    public static Void checkBrushSortTimeInMillis() {
        final double fact = 1.247;
        double step = values.length - 1;
        while (step >= 1) {
            for (int i = 0; i + (int) step < values.length; i++) {
                if (values[i + (int) step] < values[i]) {
                    double temp = values[i];
                    values[i] = values[i + (int) step];
                    values[i + (int) step] = temp;
                }
                step /= fact;
            }
        }
        checkBubbleSortTimeInMillis();
        return null;
    }

    /**
     * @param values: source array of values
     * @param start:  start index of array
     * @param stop:   last index of array
     * @return index of min value in array
     */
    private static int minValueIndex(double[] values, int start, int stop) {
        int minIndex = start;
        for (int i = start; i <= stop; i++) {
            if (values[i] < values[minIndex]) minIndex = i;
        }
        return minIndex;
    }

    /**
     * "Selection" sort function.
     *
     * @return null
     */
    public static Void checkSelectionSortTimeInMillis() {
        for (int i = 0; i < values.length - 1; i++) {
            int minValueIndex = minValueIndex(values, i, values.length - 1);
            double temp = values[i];
            values[i] = values[minValueIndex];
            values[minValueIndex] = temp;
        }
        return null;
    }

    /**
     * "Insert" sort function.
     *
     * @return null
     */
    public static Void checkInsertSortTimeInMillis() {
        for (int i = 0; i < values.length; i++) {
            int j = i;
            while (j > 0 && values[j - 1] > values[j]) {
                double temp = values[j - 1];
                values[j - 1] = values[j];
                values[j] = temp;
            }
        }
        return null;
    }

    /**
     * "Quick" sort function.
     *
     * @param values:    source array of values
     * @param lowIndex:  first index of array values
     * @param highIndex: last index of array values
     */
    public static void quickSort(double[] values, int lowIndex, int highIndex) {

        int middleIndex = lowIndex - (lowIndex - highIndex) / 2;
        double pivot = values[middleIndex];

        int i = lowIndex;
        int j = highIndex;

        while (i < j) {
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

        if (highIndex > i) {
            quickSort(values, i, highIndex);
        }

        if (lowIndex < j) {
            quickSort(values, lowIndex, j);
        }
    }

    /**
     * @param arr:       source array of values
     * @param lowIndex:  first index of array values
     * @param highIndex: last index of array values
     * @return execution time ("Quick" sort function)
     */
    public static double checkQuickSortTimeInMillis(double[] arr, int lowIndex, int highIndex) {
        double start = System.currentTimeMillis();
        quickSort(arr, lowIndex, highIndex);
        return System.currentTimeMillis() - start;
    }
}

