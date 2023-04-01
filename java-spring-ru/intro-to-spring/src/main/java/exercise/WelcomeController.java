package exercise;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// BEGIN
@RestController
@RequestMapping(value = "")
public class WelcomeController {
    {
        System.out.println("Asdasssssssfa");
    }
    @GetMapping("/")
    public String welcome() {
        System.out.println("welcome");
        return "Welcome to Spring";
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name",defaultValue = "World") String name) {
        return String.format("Hello, %s!", name);
    }
}
// END
