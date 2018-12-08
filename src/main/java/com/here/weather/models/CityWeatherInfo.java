package com.here.weather.models;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CityWeatherInfo implements Comparable {

    private int id;
    private String Name;
    private MainValue main;
    private SysValue sys;

    public CityWeatherInfo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public MainValue getMain() {
        return main;
    }

    public void setMain(MainValue main) {
        this.main = main;
    }

    public SysValue getSys() {
        return sys;
    }

    public void setSys(SysValue sys) {
        this.sys = sys;
    }

    public int getDayLength() {
        return sys.getSunset() - sys.getSunrise();
    }

    public double getTemp() {
        return main.getTemp();
    }

    @Override
    public String toString() {
        return "CityWeatherInfo{" +
                "id=" + id +
                ", Name='" + Name + '\'' +
                ", main=" + main +
                ", sys=" + sys +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        CityWeatherInfo otherCityWeatherInfo = (CityWeatherInfo) o;
        if (getDayLength() > otherCityWeatherInfo.getDayLength()) {
            return 1;
        } else if (getDayLength() < otherCityWeatherInfo.getDayLength()) {
            return -1;
        } else {
            return 0;
        }
    }

}
