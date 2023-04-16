package exercise;

import exercise.daytimes.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

// BEGIN
@Configuration
class MyApplicationConfig {


    /*Если сейчас утро (с 6 часов включительно до 12 часов), должен добавиться бин класса Morning
    Если сейчас день (с 12 часов включительно до 18 часов), должен добавиться бин класса Day
    Если сейчас вечер (с 18 часов включительно до 23 часов), должен добавиться бин класса Evening
    Если сейчас ночь (с 23 часов включительно до 6 часов утра), должен добавиться бин класса Night*/
    @Bean("daytime")
    public Daytime daytime() {
        LocalDateTime dateTime = LocalDateTime.now();
        int hour = dateTime.getHour();
        if (hour >= 6 && hour < 12) {
            return new Morning();
        }
        if (hour >= 12 && hour < 18) {
            return new Day();
        }
        if (hour >= 18 && hour < 23) {
            return new Evening();
        }
        return new Night();
    }
}
// END
