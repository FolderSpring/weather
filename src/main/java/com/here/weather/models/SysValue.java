package com.here.weather.models;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SysValue {

    private int sunrise;
    private int sunset;

    public SysValue() {
    }

    public int getSunrise() {
        return sunrise;
    }

    public void setSunrise(int sunrise) {
        this.sunrise = sunrise;
    }

    public int getSunset() {
        return sunset;
    }

    public void setSunset(int sunset) {
        this.sunset = sunset;
    }

    @Override
    public String toString() {
        return "SysValue{" +
                "sunrise=" + sunrise +
                ", sunset=" + sunset +
                '}';
    }

}
