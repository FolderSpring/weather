package com.here.weather;

import com.here.weather.configs.AppConfig;
import com.here.weather.interfaces.WeatherClient;
import com.here.weather.models.CityWeatherInfo;
import com.here.weather.utils.Utils;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;

public class WeatherApplication {

    /**
     * The program returns the temperature of the shortest and longest daylight time city
     *
     * @param args - String cities, separated by comma
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        WeatherClient weatherClient = applicationContext.getBean(WeatherClient.class);
        calculateShortestAndLongest(weatherClient, Utils.loadPropertiesFromFile(), args);
        applicationContext.close();
    }

    private static void calculateShortestAndLongest(WeatherClient weatherClient, Properties appProps, String[] args) {
        Comparator<CityWeatherInfo> comp = (CityWeatherInfo o1, CityWeatherInfo o2) -> (o1.compareTo(o2));
        TreeSet<CityWeatherInfo> treeSet = new TreeSet<>(comp);

        Set<String> cities = Utils.getCitiesFromParams(appProps, args);
        for (String curCity : cities) {
            treeSet.add(weatherClient.getWeather(
                    Utils.buildURL(appProps.getProperty("url.protocol"),
                            appProps.getProperty("url.weather.host"),
                            appProps.getProperty("url.weather.getByCity"),
                            curCity,
                            appProps.getProperty("token")).toString()));
        }

        Utils.printTemperatureAndDaylightTime(treeSet.first());
        Utils.printTemperatureAndDaylightTime(treeSet.last());
        Utils.iterateAndPrint(treeSet);
    }

}
