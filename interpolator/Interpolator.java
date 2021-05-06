package interpolator;

/**
 * "Interpolator" class allows you to interpolate a function at given points.
 */
public class Interpolator {

    /**
     * @param xPoints:     array of point values on the abscissa (x)
     * @param pointsCount: number of desired points
     * @return array which contains values of desired points
     */
    private static double[] calculateXPoints(double[] xPoints, int pointsCount) {
        double[] calculatedXPoints = new double[pointsCount];
        double xn = xPoints[0];
        double xk = xPoints[xPoints.length - 1];
        double dx = (xk - xn) / (pointsCount - 1);
        double x = xn;
        for (int i = 0; i < pointsCount; i++) {
            calculatedXPoints[i] = x;
            x += dx;
        }
        return calculatedXPoints;
    }

    /**
     * @param xPoints:     source array of point values on the abscissa (x)
     * @param yPoints:     source array of point values on the ordinate (y)
     * @param xRange:      number of desired points on the abscissa (x)
     * @param pointsCount: number of desired points on the ordinate (y)
     * @return two-dimensional array which contains values of point (x,y)
     */
    public static double[][] lagrangeInterpolation(double[] xPoints, double[] yPoints,
                                                   int xRange, int pointsCount) {

        double[] calculatedXPoints = Interpolator.calculateXPoints(xPoints, xRange);
        double[] calculatedYPoints = new double[calculatedXPoints.length];

        for (int x = 0; x < xRange; x++) {
            double sum = 0;
            for (int i = 0; i < pointsCount; i++) {
                double mul = 1;
                for (int j = 0; j < pointsCount; j++) {
                    if (i != j) {
                        double temp = (calculatedXPoints[x] - xPoints[j]) / (xPoints[i] - xPoints[j]);
                        mul *= temp;
                    }
                }
                sum += mul * yPoints[i];
            }
            calculatedYPoints[x] = sum;

        }

        double[][] lagrangeArray = new double[calculatedXPoints.length][2];
        for (int i = 0; i < calculatedXPoints.length; i++) {
            for (int j = 0; j < 2; j++) {
                lagrangeArray[i][j] = calculatedXPoints[i];
                if (j == 1) {
                    lagrangeArray[i][j] = calculatedYPoints[i];
                }
            }
        }
        return lagrangeArray;
    }
}




