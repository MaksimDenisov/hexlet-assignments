package exercise;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import java.util.logging.Level;

class App {
    private static final Logger LOGGER = Logger.getLogger("AppLogger");

    // BEGIN
    public static void main(String[] args) {
        int[] numbers = {10, -4, 67, 100, -100, 8};

        System.out.println(App.getMinMax(numbers));
    }

    private static Map<String, Integer> getMinMax(int[] numbers) {
        MinThread minThread = new MinThread(numbers);
        MaxThread maxThread = new MaxThread(numbers);
        minThread.start();
        LOGGER.info("Min thread started");
        maxThread.start();
        LOGGER.info("Max thread started");
        try {
            minThread.join();
            maxThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Map<String, Integer> map = new HashMap<>();
        map.put("min", minThread.getMin());
        LOGGER.info("Min thread joined");
        map.put("max", maxThread.getMax());
        LOGGER.info("Max thread joined");
        return map;
    }
    // END
}
