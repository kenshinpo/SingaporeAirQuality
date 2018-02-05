package com.pochih.singaporeairquality.entity;

import java.util.Date;

/**
 * Created by PoChih(A-Po) on 2018/02/03.
 */

public class Reading {
    private Date timestamp;
    private Date updatedTimestamp;
    private float o3_SubIndex;
    private float pm10_24Hourly;
    private float pm10_SubIndex;
    private float co_SubIndex;
    private float pm25_24hourly;
    private float so2_SubIndex;
    private float co_8HrMax;
    private float no2_1HrMax;
    private float so2_24Hourly;
    private float pm25_SubIndex;
    private float psi_24Hourly;
    private float o3_8HrMax;
    private float psi_3Hourly;

    public Reading() {
    }

    public Reading(Date timestamp, Date updatedTimestamp) {
        this.timestamp = timestamp;
        this.updatedTimestamp = updatedTimestamp;
    }

    public Reading(Date timestamp, Date updatedTimestamp, float o3_SubIndex, float pm10_24Hourly, float pm10_SubIndex, float co_SubIndex, float pm25_24hourly, float so2_SubIndex, float co_8HrMax, float no2_1HrMax, float so2_24Hourly, float pm25_SubIndex, float psi_24Hourly, float o3_8HrMax) {
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

    public float getO3_SubIndex() {
        return o3_SubIndex;
    }

    public void setO3_SubIndex(float o3_SubIndex) {
        this.o3_SubIndex = o3_SubIndex;
    }

    public float getPm10_24Hourly() {
        return pm10_24Hourly;
    }

    public void setPm10_24Hourly(float pm10_24Hourly) {
        this.pm10_24Hourly = pm10_24Hourly;
    }

    public float getPm10_SubIndex() {
        return pm10_SubIndex;
    }

    public void setPm10_SubIndex(float pm10_SubIndex) {
        this.pm10_SubIndex = pm10_SubIndex;
    }

    public float getCo_SubIndex() {
        return co_SubIndex;
    }

    public void setCo_SubIndex(float co_SubIndex) {
        this.co_SubIndex = co_SubIndex;
    }

    public float getPm25_24hourly() {
        return pm25_24hourly;
    }

    public void setPm25_24hourly(float pm25_24hourly) {
        this.pm25_24hourly = pm25_24hourly;
    }

    public float getSo2_SubIndex() {
        return so2_SubIndex;
    }

    public void setSo2_SubIndex(float so2_SubIndex) {
        this.so2_SubIndex = so2_SubIndex;
    }

    public float getCo_8HrMax() {
        return co_8HrMax;
    }

    public void setCo_8HrMax(float co_8HrMax) {
        this.co_8HrMax = co_8HrMax;
    }

    public float getNo2_1HrMax() {
        return no2_1HrMax;
    }

    public void setNo2_1HrMax(float no2_1HrMax) {
        this.no2_1HrMax = no2_1HrMax;
    }

    public float getSo2_24Hourly() {
        return so2_24Hourly;
    }

    public void setSo2_24Hourly(float so2_24Hourly) {
        this.so2_24Hourly = so2_24Hourly;
    }

    public float getPm25_SubIndex() {
        return pm25_SubIndex;
    }

    public void setPm25_SubIndex(float pm25_SubIndex) {
        this.pm25_SubIndex = pm25_SubIndex;
    }

    public float getPsi_24Hourly() {
        return psi_24Hourly;
    }

    public void setPsi_24Hourly(float psi_24Hourly) {
        this.psi_24Hourly = psi_24Hourly;
    }

    public float getO3_8HrMax() {
        return o3_8HrMax;
    }

    public void setO3_8HrMax(float o3_8HrMax) {
        this.o3_8HrMax = o3_8HrMax;
    }

    public float getPsi_3Hourly() {
        return psi_3Hourly;
    }

    public void setPsi_3Hourly(float psi_3Hourly) {
        this.psi_3Hourly = psi_3Hourly;
    }
}
