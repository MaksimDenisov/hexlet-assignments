package exercise;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// BEGIN
public class Validator {
    public static List<String> validate(Address address) {
        List<String> notValid = new ArrayList<>();
        Field[] fields = address.getClass().getDeclaredFields();
        for (Field field : fields) {
            NotNull annotation = field.getAnnotation(NotNull.class);
            field.setAccessible(true);
            try {
                if(annotation != null && field.get(address) == null) {
                    notValid.add(field.getName());
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return notValid;
    }

    public static void main(String[] args)  {
        validate(new Address("country", "city", "street", "houseNumber", "flatNumber"));
        validate(new Address(null, null, null, null, null));
    }
}
// END
