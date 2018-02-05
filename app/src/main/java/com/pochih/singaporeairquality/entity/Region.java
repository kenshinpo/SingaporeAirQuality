package com.pochih.singaporeairquality.entity;

import java.util.List;

/**
 * Created by PoChih(A-Po) on 2018/02/03.
 */

public class Region {
    private String name;
    private float latitude;
    private float longitude;
    private List<Reading> readings;

    public Region() {
    }

    public Region(String name, float latitude, float longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public List<Reading> getReadings() {
        return readings;
    }

    public void setReadings(List<Reading> readings) {
        this.readings = readings;
    }
}

