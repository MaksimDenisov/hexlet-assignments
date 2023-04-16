package exercise;

import exercise.daytimes.Daytime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// BEGIN
@RestController
class WelcomeController {

    @Autowired
    Daytime daytime;

    @Autowired
    Meal meal;

    @GetMapping("/daytime")
    public String welcome() {
        return String.format("It is %s now. Enjoy your %s", daytime.getName(), meal.getMealForDaytime(daytime.getName()));
    }
}
// END
