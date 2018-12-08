package com.here.weather.utils;

import com.here.weather.models.CityWeatherInfo;
import com.squareup.okhttp.HttpUrl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.*;

import static com.here.weather.constants.AppConstants.APP_PROPERTIES_FILE_NAME;

public class Utils {

    private static final Logger log = LoggerFactory.getLogger(Utils.class);

    /**
     * Function returns Properties
     *
     * @return Properties
     * @throws FileNotFoundException
     */
    public static Properties loadPropertiesFromFile() throws FileNotFoundException {
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String appConfigPath = rootPath + APP_PROPERTIES_FILE_NAME;
        Properties appProps = new Properties();
        try {
            appProps.load(new FileInputStream(appConfigPath));
        } catch (IOException e) {
            log.error("Config file '" + APP_PROPERTIES_FILE_NAME + "' not found!");
            throw new FileNotFoundException("Config file '" + APP_PROPERTIES_FILE_NAME + "' not found!");
        }
        return appProps;
    }

    /**
     * Functionn builds URL
     *
     * @param protocol
     * @param host
     * @param path
     * @param city
     * @param token
     * @return
     */
    public static URL buildURL(String protocol, String host, String path, String city, String token) {
        URL url = new HttpUrl.Builder()
                .scheme(protocol)
                .host(host)
                .encodedPath(path)
                .addQueryParameter("q", city)
                .addQueryParameter("appid", token)
                .build().url();
        log.info(url.toString());
        return url;
    }

    /**
     * Function returns Set of cities from arguments. If arguments is empty returns default set of cities
     *
     * @param appProps
     * @param args
     * @return
     */
    public static Set<String> getCitiesFromParams(Properties appProps, String[] args) {
        if (args.length == 1) {
            return new HashSet<>(Arrays.asList(args[0].split("\\s*,\\s*")));
        } else {
            return new HashSet<>(Arrays.asList(appProps.getProperty("default.cities").split("\\s*,\\s*")));
        }
    }

    public static void printTemperatureAndDaylightTime(CityWeatherInfo cityWeatherInfo) {
        log.info("daylight time: " + cityWeatherInfo.getDayLength() + ", temperature: " + cityWeatherInfo.getTemp());
    }

    /**
     * Function prints to log all elements of Tree
     *
     * @param treeSet
     */
    public static void iterateAndPrint(TreeSet<CityWeatherInfo> treeSet) {
        Iterator<CityWeatherInfo> itr = treeSet.iterator();
        int i = 0;
        while (itr.hasNext()) {
            CityWeatherInfo curElement = itr.next();
            log.info("daylight time " + i + ": " + (curElement.getSys().getSunset() - curElement.getSys().getSunrise()));
            i++;
        }
        log.info(treeSet.toString());
    }

}
