package com.pochih.singaporeairquality.entity;

import java.util.Date;

/**
 * Created by PoChih(A-Po) on 2018/02/03.
 */

public class Reading {
    private Date timestamp;
    private Date updatedTimestamp;
    private double o3_SubIndex;
    private double pm10_24Hourly;
    private double pm10_SubIndex;
    private double co_SubIndex;
    private double pm25_24hourly;
    private double so2_SubIndex;
    private double co_8HrMax;
    private double no2_1HrMax;
    private double so2_24Hourly;
    private double pm25_SubIndex;
    private double psi_24Hourly;
    private double o3_8HrMax;
    private double psi_3Hourly;

    public Reading() {
    }

    public Reading(Date timestamp, Date updatedTimestamp) {
        this.timestamp = timestamp;
        this.updatedTimestamp = updatedTimestamp;
    }

    public Reading(Date timestamp, Date updatedTimestamp, double o3_SubIndex, double pm10_24Hourly, double pm10_SubIndex, double co_SubIndex, double pm25_24hourly, double so2_SubIndex, double co_8HrMax, double no2_1HrMax, double so2_24Hourly, double pm25_SubIndex, double psi_24Hourly, double o3_8HrMax) {
        this.timestamp = timestamp;
        this.updatedTimestamp = updatedTimestamp;
        this.o3_SubIndex = o3_SubIndex;
        this.pm10_24Hourly = pm10_24Hourly;
        this.pm10_SubIndex = pm10_SubIndex;
        this.co_SubIndex = co_SubIndex;
        this.pm25_24hourly = pm25_24hourly;
        this.so2_SubIndex = so2_SubIndex;
        this.co_8HrMax = co_8HrMax;
        this.no2_1HrMax = no2_1HrMax;
        this.so2_24Hourly = so2_24Hourly;
        this.pm25_SubIndex = pm25_SubIndex;
        this.psi_24Hourly = psi_24Hourly;
        this.o3_8HrMax = o3_8HrMax;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Date getUpdatedTimestamp() {
        return updatedTimestamp;
    }

    public void setUpdatedTimestamp(Date updatedTimestamp) {
        this.updatedTimestamp = updatedTimestamp;
    }

    public double getO3_SubIndex() {
        return o3_SubIndex;
    }

    public void setO3_SubIndex(double o3_SubIndex) {
        this.o3_SubIndex = o3_SubIndex;
    }

    public double getPm10_24Hourly() {
        return pm10_24Hourly;
    }

    public void setPm10_24Hourly(double pm10_24Hourly) {
        this.pm10_24Hourly = pm10_24Hourly;
    }

    public double getPm10_SubIndex() {
        return pm10_SubIndex;
    }

    public void setPm10_SubIndex(double pm10_SubIndex) {
        this.pm10_SubIndex = pm10_SubIndex;
    }

    public double getCo_SubIndex() {
        return co_SubIndex;
    }

    public void setCo_SubIndex(double co_SubIndex) {
        this.co_SubIndex = co_SubIndex;
    }

    public double getPm25_24hourly() {
        return pm25_24hourly;
    }

    public void setPm25_24hourly(double pm25_24hourly) {
        this.pm25_24hourly = pm25_24hourly;
    }

    public double getSo2_SubIndex() {
        return so2_SubIndex;
    }

    public void setSo2_SubIndex(double so2_SubIndex) {
        this.so2_SubIndex = so2_SubIndex;
    }

    public double getCo_8HrMax() {
        return co_8HrMax;
    }

    public void setCo_8HrMax(double co_8HrMax) {
        this.co_8HrMax = co_8HrMax;
    }

    public double getNo2_1HrMax() {
        return no2_1HrMax;
    }

    public void setNo2_1HrMax(double no2_1HrMax) {
        this.no2_1HrMax = no2_1HrMax;
    }

    public double getSo2_24Hourly() {
        return so2_24Hourly;
    }

    public void setSo2_24Hourly(double so2_24Hourly) {
        this.so2_24Hourly = so2_24Hourly;
    }

    public double getPm25_SubIndex() {
        return pm25_SubIndex;
    }

    public void setPm25_SubIndex(double pm25_SubIndex) {
        this.pm25_SubIndex = pm25_SubIndex;
    }

    public double getPsi_24Hourly() {
        return psi_24Hourly;
    }

    public void setPsi_24Hourly(double psi_24Hourly) {
        this.psi_24Hourly = psi_24Hourly;
    }

    public double getO3_8HrMax() {
        return o3_8HrMax;
    }

    public void setO3_8HrMax(double o3_8HrMax) {
        this.o3_8HrMax = o3_8HrMax;
    }

    public double getPsi_3Hourly() {
        return psi_3Hourly;
    }

    public void setPsi_3Hourly(double psi_3Hourly) {
        this.psi_3Hourly = psi_3Hourly;
    }
}
