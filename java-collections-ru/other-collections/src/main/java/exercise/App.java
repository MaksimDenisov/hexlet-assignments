package exercise;

import java.util.*;

// BEGIN
class App {

    public static HashMap<String, String> genDiff(Map<String, Object> data1, Map<String, Object> data2) {
        HashMap<String, String> result = new LinkedHashMap<>();
        Set<String> keySet = new TreeSet<>(data1.keySet());
        keySet.addAll(data2.keySet());
        for (String key : keySet) {
            Object value1 = data1.get(key);
            Object value2 = data2.get(key);
            String diff;
            if (value1 == null) {
                diff = "added";
            } else if (value2 == null) {
                diff = "deleted";
            } else if (value1.equals(value2)) {
                diff = "unchanged";
            } else {
                diff = "changed";
            }
            result.put(key, diff);
        }
        return result;
    }
}
//END
