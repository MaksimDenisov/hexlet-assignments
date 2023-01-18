package exercise;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.summingInt;


// BEGIN
public class App {
clear
    public static Map<String, Integer> getWordCount(String sentence) {
        return ("".equals(sentence)) ? Collections.emptyMap() : Arrays.stream(sentence.split(" "))
                .collect(Collectors.groupingBy(word -> (word), summingInt(word -> 1)));
    }

    public static String toString(Map<String, Integer> wordCount) {
        return wordCount.entrySet().stream()
                .map(e -> String.format("  %s: %d\n", e.getKey(), e.getValue()))
                .reduce(String::concat).map(s -> "{\n" + s + "}")
                .orElse("{}");
    }
}
//END
