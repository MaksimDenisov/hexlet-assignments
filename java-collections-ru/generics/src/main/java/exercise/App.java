package exercise;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// BEGIN
class App {
    public static List<Map<String, String>> findWhere(List<Map<String, String>> books, Map<String, String> where) {
        return books.stream().filter(book -> isContain(book, where)).collect(Collectors.toList());
    }

    public static boolean isContain(Map<String, String> book, Map<String, String> where) {
        for (Map.Entry<String, String> entry : where.entrySet() ){
            if(book.get(entry.getKey()) == null || !book.get(entry.getKey()).equals(entry.getValue())) {
                return false;
            }
        }
        return true;
    }
}
//END
