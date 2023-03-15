package exercise;

import java.util.Map;

// BEGIN
public class App {
    public static void swapKeyValue(KeyValueStorage storage) {
        Map<String, String> map = storage.toMap();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            storage.unset(entry.getKey());
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            storage.set(entry.getValue(), entry.getKey());
        }
    }
}
// END
