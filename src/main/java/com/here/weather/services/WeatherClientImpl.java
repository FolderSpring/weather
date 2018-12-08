package com.here.weather.services;

import com.here.weather.interfaces.WeatherClient;
import com.here.weather.models.CityWeatherInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherClientImpl implements WeatherClient {

    @Autowired
    RestTemplate restTemplate;

    public CityWeatherInfo getWeather(String url) {
        ResponseEntity<CityWeatherInfo> response = restTemplate.getForEntity(url, CityWeatherInfo.class);
        return response.getBody();
    }
}
