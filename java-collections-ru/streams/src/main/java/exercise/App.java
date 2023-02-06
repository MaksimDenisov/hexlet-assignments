package exercise;

import java.util.List;

// BEGIN
class App {
    public static long getCountOfFreeEmails(List<String> emailsList) {
        return emailsList.stream()
                .filter(email -> email.contains("@gmail.com") || email.contains("@yandex.ru") || email.contains("@hotmail.com"))
                .count();
    }

}

// END
