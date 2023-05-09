package exercise;

import java.util.Arrays;
import java.util.logging.Logger;

// BEGIN
public class MinThread extends Thread{
    private static final Logger LOGGER = Logger.getLogger("AppLogger");
    private final int[] num;
    private int min = Integer.MAX_VALUE;

    public MinThread(int[] num) {
        this.num = num;
    }

    @Override
    public void run() {
        min = Arrays.stream(num).min().orElse(Integer.MAX_VALUE);
        LOGGER.info("Min thread finished");
    }

    public int getMin() {
        return min;
    }
}
// END
