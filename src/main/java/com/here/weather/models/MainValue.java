package com.here.weather.models;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MainValue {

    private Double temp;

    public MainValue() {
    }

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    @Override
    public String toString() {
        return "MainValue{" +
                "temp=" + temp +
                '}';
    }

}
