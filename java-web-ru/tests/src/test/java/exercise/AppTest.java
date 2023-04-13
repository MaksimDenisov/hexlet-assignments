package exercise;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import io.javalin.Javalin;
import io.ebean.DB;

import exercise.domain.User;
import exercise.domain.query.QUser;
import io.ebean.Database;

class AppTest {

    private static Javalin app;
    private static String baseUrl;

    // BEGIN

    @BeforeAll
    static void beforeAll() {
        app = App.getApp();
        app.start();
        baseUrl = "http://localhost:" + app.port();;
    }

    // END

    // Между тестами база данных очищается
    // Благодаря этому тесты не влияют друг на друга
    @BeforeEach
    void beforeEach() {
        Database db = DB.getDefault();
        db.truncate("user");
        User existingUser = new User("Wendell", "Legros", "a@a.com", "123456");
        existingUser.save();
    }

    @Test
    void testUsers() {
        // Выполняем GET запрос на адрес http://localhost:port/users
        HttpResponse<String> response = Unirest
            .get(baseUrl + "/users")
            .asString();
        // Получаем тело ответа
        String content = response.getBody();

        // Проверяем код ответа
        assertThat(response.getStatus()).isEqualTo(200);
        // Проверяем, что страница содержит определенный текст
        assertThat(response.getBody()).contains("Wendell Legros");
    }

    @Test
    void testCreateValidUser() {
        HttpResponse<String> response = Unirest
            .post(baseUrl + "/users")
                .field("firstName", "First")
                .field("lastName", "Lalue")
                .field("email", "a@aasd.ru")
                .field("password", "123456")
            .asString();
        assertThat(response.getStatus()).isEqualTo(302);
        User actualUser = new QUser()
                .firstName.equalTo("First")
                .findOne();
        assertThat(actualUser).isNotNull();
        assertThat(actualUser.getFirstName()).isEqualTo("First");
        assertThat(actualUser.getLastName()).isEqualTo("Lalue");
        assertThat(actualUser.getEmail()).isEqualTo("a@aasd.ru");
        assertThat(actualUser.getPassword()).isEqualTo("123456");
    }

    @Test
    void testCreateInvalidUser() {
        HttpResponse<String> response = Unirest
                .post(baseUrl + "/users")
                .field("firstName", "")
                .field("lastName", "")
                .field("email", "aasd")
                .field("password", "AAA")
                .asString();
        assertThat(response.getStatus()).isEqualTo(422);
        User actualUser = new QUser()
                .firstName.equalTo("First")
                .findOne();
        assertThat(actualUser).isNull();
        String content = response.getBody();
        assertThat(content).contains("Имя не должно быть пустым");
        assertThat(content).contains("Фамилия не должна быть пустой");
        assertThat(content).contains("Должно быть валидным email");
        assertThat(content).contains("Пароль должен содержать не менее 4 символов");
    }

    @Test
    void testNewUser() {

        HttpResponse<String> response = Unirest
                .get(baseUrl + "/users/new")
                .asString();

        assertThat(response.getStatus()).isEqualTo(200);
    }
    // BEGIN

    @AfterAll
    static void afterAll() {
        app.stop();
    }

    // END
}
