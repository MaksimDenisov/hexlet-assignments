package exercise;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

// BEGIN
public class App {
    public static void save(Path path, Car car) throws IOException {
        byte[] strToBytes = car.serialize().getBytes();
        Files.write(path, strToBytes);
    }

    public static Car extract(Path path) throws IOException{
        return Car.unserialize(Files.readAllLines(path).get(0));
    }
}
// END
