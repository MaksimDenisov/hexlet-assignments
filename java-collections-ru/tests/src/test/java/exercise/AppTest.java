package exercise;

import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

class AppTest {

    @Test
    void testTake() {
        // BEGIN
        List<Integer> list = List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        List<Integer> expected = List.of(0, 1, 2, 3, 4);
        assertIterableEquals(App.take(list, 0), Collections.emptyList());
        assertIterableEquals(App.take(list, 5), expected);
        assertIterableEquals(App.take(list, 10), list);
        assertIterableEquals(App.take(list, 15), list);
        assertIterableEquals(App.take(Collections.emptyList(), 10), Collections.emptyList());
        // END
    }
}
