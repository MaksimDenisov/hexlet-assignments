package exercise.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import exercise.HttpClient;
import exercise.model.City;
import exercise.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class WeatherService {

    @Autowired
    CityRepository cityRepository;

    // Клиент
    HttpClient client;

    // При создании класса сервиса клиент передаётся снаружи
    // В теории это позволит заменить клиент без изменения самого сервиса
    WeatherService(HttpClient client) {
        this.client = client;
    }

    // BEGIN
    public Map<String, String> getCityWeather(Long id) {
        City city = cityRepository.getById(id);
        ObjectMapper mapper = new ObjectMapper();
        String json = client.get("http://weather/api/v2/cities/" + city.getName());
        Map<String, String> map = Collections.emptyMap();
        try {
            map = mapper.readValue(json, Map.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return map;
    }

    public List<Map<String, String>> getCities(String name) {
        return getCities(cityRepository.findAllByNameStartsWithIgnoreCase(name));
    }

    public List<Map<String, String>> getCities() {
        return getCities(cityRepository.getALL());
    }

    private List<Map<String, String>> getCities(    List<City> cities) {
        List<Map<String, String>> result = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        for (City city : cities) {
            String json = client.get("http://weather/api/v2/cities/" + city.getName());
            try {
                Map<String, String> map = mapper.readValue(json, Map.class);
                Map<String, String> pair = new HashMap<>();
                pair.put("name", city.getName());
                pair.put("temperature", map.get("temperature"));
                result.add(pair);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
    // END
}
