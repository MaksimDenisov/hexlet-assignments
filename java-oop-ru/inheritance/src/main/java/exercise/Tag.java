package exercise;

import java.util.Map;
import java.util.stream.Collectors;

// BEGIN
class Tag {
    protected String name;
    protected Map<String, String> attrs;

    public Tag(String name, Map<String, String> attrs) {
        this.name = name;
        this.attrs = attrs;
    }

    public String getName() {
        return name;
    }

    public Map<String, String> getAttrs() {
        return attrs;
    }

    @Override
    public String toString() {
        if (attrs.size() == 0) {
            return String.format("<%s>", name);
        }
        String strAttrs = attrs.entrySet().stream()
                .map(entry -> String.format("%s=\"%s\"", entry.getKey(), entry.getValue()))
                .collect(Collectors.joining(" "));
        return String.format("<%s %s>", name, strAttrs);
    }
}
// END
