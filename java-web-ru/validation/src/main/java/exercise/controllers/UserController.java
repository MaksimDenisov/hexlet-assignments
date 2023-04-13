package exercise.controllers;

import io.javalin.http.Handler;
import java.util.List;
import java.util.Map;
import io.javalin.core.validation.Validator;
import io.javalin.core.validation.ValidationError;
import io.javalin.core.validation.JavalinValidation;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.lang3.StringUtils;

import exercise.domain.User;
import exercise.domain.query.QUser;

public final class UserController {

    public static Handler listUsers = ctx -> {

        List<User> users = new QUser()
            .orderBy()
                .id.asc()
            .findList();

        ctx.attribute("users", users);
        ctx.render("users/index.html");
    };

    public static Handler showUser = ctx -> {
        long id = ctx.pathParamAsClass("id", Long.class).getOrDefault(null);

        User user = new QUser()
            .id.equalTo(id)
            .findOne();

        ctx.attribute("user", user);
        ctx.render("users/show.html");
    };

    public static Handler newUser = ctx -> {

        ctx.attribute("errors", Map.of());
        ctx.attribute("user", Map.of());
        ctx.render("users/new.html");
    };

    public static Handler createUser = ctx -> {

        Validator<String> firstNameValidator = ctx.formParamAsClass("firstName", String.class)
            .check(it -> !it.isEmpty(), "Имя не должно быть пустым");
        Validator<String> lastNameValidator = ctx.formParamAsClass("lastName", String.class)
                .check(it -> !it.isEmpty(), "Не должно быть пустым");
        Validator<String> emailValidator = ctx.formParamAsClass("email", String.class)
                .check(it -> EmailValidator.getInstance().isValid(it), "Должен быть валидным Email");
        Validator<String> passwordValidator = ctx.formParamAsClass("password", String.class)
                .check(it -> !(it.length() < 4), "Yе короче 4 символов")
                .check(it -> it.matches("[0-9]+"),"Только цифры");

        Map<String, List<ValidationError<? extends Object>>> errors = JavalinValidation.collectErrors(
                 firstNameValidator,
                 lastNameValidator,
                 emailValidator,
                 passwordValidator);
        User user = new User(ctx.formParam("firstName"),ctx.formParam("lastName"),ctx.formParam("email"),ctx.formParam("password"));
        if (!errors.isEmpty()) {
            System.out.println("Has errors");
            ctx.status(422);
            System.out.println("Set status");
            ctx.attribute("errors", errors);
            System.out.println("Add errors");
            ctx.attribute("user", user);
            System.out.println("Add user");
            ctx.render("users/new.html");
            return;
        }
        user.save();
        ctx.sessionAttribute("flash", "Пользователь успешно добавлен");
        ctx.redirect("/users");
     // END
    };
}
