package com.here.weather.interfaces;

import com.here.weather.models.CityWeatherInfo;

public interface WeatherClient {

    CityWeatherInfo getWeather(String url);

}
