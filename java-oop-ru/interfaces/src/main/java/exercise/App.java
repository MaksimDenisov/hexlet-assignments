package exercise;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
class App {
    public static void main(String[] args) {
        List<Home> apartments = new ArrayList<>(List.of(
                new Cottage(100, 1),
                new Flat(190, 10, 2),
                new Flat(180, 30, 5),
                new Cottage(250, 3)
        ));
        buildApartmentsList(apartments,3).forEach(System.out::println);
    }

    public static List<String> buildApartmentsList(List<Home> listHome, int count){
        return listHome.stream()
                .sorted(Home::compareTo)
                .limit(count)
                .map(Object::toString)
                .collect(Collectors.toList());
    }
}
// END
