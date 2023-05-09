package exercise;

import java.util.Arrays;
import java.util.logging.Logger;

// BEGIN
public class MaxThread extends Thread {
    private static final Logger LOGGER = Logger.getLogger("AppLogger");
    private final int[] num;
    private int max = Integer.MIN_VALUE;


    public MaxThread(int[] array) {
        this.num = array;
    }

    @Override
    public void run() {
        max = Arrays.stream(num).max().orElse(Integer.MIN_VALUE);
        LOGGER.info("Max thread finished");
    }

    public int getMax() {
        return max;
    }
}
// END
