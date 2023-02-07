package exercise;

import java.util.stream.Collectors;
import java.util.Arrays;

// BEGIN
class App {
    private static final String ENV = "environment";
    private static final String PREFIX = "X_FORWARDED_";

    public static String getForwardedVariables(String conf){
        return Arrays.stream(conf.split("\n"))
                .filter(s->s.startsWith(ENV))
                .map(s->s.substring(s.indexOf("\"") + 1,s.lastIndexOf("\"")))
                .flatMap(s->Arrays.stream(s.split(",")))
                .filter(s-> s.startsWith(PREFIX) )
                .map(s->s.replace(PREFIX,""))
                .collect(Collectors.joining(","));
    }
}
//END
