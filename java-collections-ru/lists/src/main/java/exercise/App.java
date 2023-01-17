package exercise;

import java.util.Arrays;

// BEGIN
public class App {

    public static boolean scrabble(String symbols, String word) {
        char[] symbolsItems = getCharsInLowerCaseSorted(symbols);
        char[] wordsItems = getCharsInLowerCaseSorted(word);
        int c = 0;
        for (char symbolsItem : symbolsItems) {
            if (wordsItems[c] == symbolsItem) {
                if (++c == word.length()) {
                    return true;
                }
            }
        }
        return false;
    }

    public static char[] getCharsInLowerCaseSorted(String text) {
        char[] result = new char[text.length()];
        text.toLowerCase().getChars(0, text.length(), result, 0);
        Arrays.sort(result);
        return result;
    }
}
//END
