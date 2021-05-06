package timer;

import java.util.concurrent.Callable;

/**
 * Class "Timer" allows you to calculate the execution time of the function.
 */
public class Timer {
    /**
     * @param method: different types of sort function
     * @return execution time (except "Quick" sort function)
     * @throws Exception ?
     */
    public static double timer(Callable<Void> method) throws Exception {
        double start = System.currentTimeMillis();
        method.call();
        return System.currentTimeMillis() - start;
    }
}
