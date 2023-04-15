package exercise.controller;
import exercise.repository.CityRepository;
import exercise.service.WeatherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;


@RestController
public class CityController {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private WeatherService weatherService;

    // BEGIN
    @GetMapping("/cities/{id}")
    public Map<String,String> getCity (@PathVariable Long id){
        return weatherService.getCityWeather(id);
    }

    @GetMapping("/search")
    public List<Map<String,String>> getCities (@RequestParam(required = false) String name){
        if(name==null) {
            return weatherService.getCities();
        }
        return weatherService.getCities(name);
    }
    // END
}

