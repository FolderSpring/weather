package com.here.weather;

import com.here.weather.models.CityWeatherInfo;
import com.here.weather.models.MainValue;
import com.here.weather.models.SysValue;
import com.here.weather.utils.Utils;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.Random;
import java.util.TreeSet;

public class WeatherApplicationTests {

    private static final Logger log = LoggerFactory.getLogger(WeatherApplicationTests.class);

    /**
     * Test checks TreeSet comparator. First elements day length must be smaller than last day length
     */
    @Test
    public void RandomTreeSetTest() {
        int minTreeSetSize = 2;
        TreeSet<CityWeatherInfo> treeSet = getRandomTreeSet(new Random().nextInt(10) + minTreeSetSize);
        Utils.printTemperatureAndDaylightTime(treeSet.first());
        Utils.printTemperatureAndDaylightTime(treeSet.last());
        Utils.iterateAndPrint(treeSet);
        Assert.assertTrue("TreeSet size must be bigger than " + minTreeSetSize, treeSet.size() > minTreeSetSize);
        Assert.assertTrue("Daylight time of first element from TreeSet must be less than daylight time of the last TreeSet element",
                treeSet.first().getDayLength() < treeSet.last().getDayLength());
    }

    private static TreeSet<CityWeatherInfo> getRandomTreeSet(int size) {
        Comparator<CityWeatherInfo> comparator = (CityWeatherInfo o1, CityWeatherInfo o2) -> (o1.compareTo(o2));
        TreeSet<CityWeatherInfo> treeSet = new TreeSet<>(comparator);

        Random random = new Random();
        for (int i = 0; i < size; i++) {
            CityWeatherInfo cityWeatherInfo = new CityWeatherInfo();
            MainValue mainValue = new MainValue();
            mainValue.setTemp((double) random.nextInt(100) - 50);
            cityWeatherInfo.setMain(mainValue);
            SysValue sysValue = new SysValue();
            sysValue.setSunrise(1485762037 + random.nextInt(1000) - 500);
            sysValue.setSunset(1485794875 + random.nextInt(1000) - 500);
            cityWeatherInfo.setSys(sysValue);
            treeSet.add(cityWeatherInfo);
        }
        log.info(treeSet.toString());
        return treeSet;
    }

}
