package exercise;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

// BEGIN

// END
@Getter
@Setter
@Value
@AllArgsConstructor
class User {
    int id;
    String firstName;
    String lastName;
    int age;
}
