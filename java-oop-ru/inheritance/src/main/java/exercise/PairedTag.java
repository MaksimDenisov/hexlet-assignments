package exercise;

import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
class PairedTag extends Tag {

    private final String text;
    private final List<Tag> children;

    public PairedTag(String name, Map<String, String> attrs, String text, List<Tag> children) {
        super(name, attrs);
        this.text = text;
        this.children = children;
    }

    @Override
    public String toString() {
        if(children.size() == 0) {
            return String.format("%s%s</%s>",super.toString(),text,name);
        }
        else {
            String textChildren = children.stream()
                    .map(Tag::toString)
                    .collect(Collectors.joining(""));
            return String.format("%s%s</%s>",super.toString(),textChildren,name);
        }
    }
}
// END
