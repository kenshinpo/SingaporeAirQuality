package com.pochih.singaporeairquality.entity;

import java.util.List;

/**
 * Created by PoChih(A-Po) on 2018/02/03.
 */

public class Region {
    private String name;
    private double latitude;
    private double longitude;
    private List<Reading> readings;

    public Region() {
    }

    public Region(String name, double latitude, double longitude) {
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

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public List<Reading> getReadings() {
        return readings;
    }

    public void setReadings(List<Reading> readings) {
        this.readings = readings;
    }
}

