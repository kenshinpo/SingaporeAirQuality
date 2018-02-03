package com.pochih.singaporeairquality;

import timber.log.Timber;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by PoChih(A-Po) on 2018/02/03.
 */

public class Settings {
    public static final String SETTINGS = "settings";
    public static final String SETTINGS_BASE_URL = "baseUrl";
    public static final String SETTINGS_READING = "reading";
    public static final String READING_O3_SUB_INDEX = "o3_sub_index";
    public static final String READING_PM10_24HOURLY = "pm10_twenty_four_hourly";
    public static final String READING_PM10_SUB_INDEX = "pm10_sub_index";
    public static final String READING_CO_SUB_INDEX = "co_sub_index";
    public static final String READING_PM25_24HOURLY = "pm25_twenty_four_hourly";
    public static final String READING_SO2_SUB_INDEX = "so2_sub_index";
    public static final String READING_CO_8HR_MAX = "co_eight_hour_max";
    public static final String READING_NO2_1HR_MAX = "no2_one_hour_max";
    public static final String READING_SO2_24HOURLY = "so2_twenty_four_hourly";
    public static final String READING_PM25_SUB_INDEX = "pm25_sub_index";
    public static final String READING_PSI_24HOURLY = "psi_twenty_four_hourly";
    public static final String READING_O3_8HR_MAX = "o3_eight_hour_max";
    public static final String READING_PSI_3Hourly = "psi_three_hourly";


    public static final String DEFAULT_BASE_URL = "https://api.data.gov.sg/v1/environment/";

    public static String getBaseUrl() {
        try {
            return AppApplication.getInstance().getSharedPreferences(SETTINGS, MODE_PRIVATE)
                    .getString(SETTINGS_BASE_URL, DEFAULT_BASE_URL);
        } catch (Exception e) {
            Timber.e(e);
            return DEFAULT_BASE_URL;
        }
    }

    public static void setBaseUrl(String baseUrl) {
        try {
            AppApplication.getInstance().getSharedPreferences(SETTINGS, MODE_PRIVATE)
                    .edit()
                    .putString(SETTINGS_BASE_URL, baseUrl)
                    .commit();
        } catch (Exception e) {
            Timber.e(e);
        }
    }

    public static String getReading() {
        try {
            return AppApplication.getInstance().getSharedPreferences(SETTINGS, MODE_PRIVATE)
                    .getString(SETTINGS_READING, READING_PSI_24HOURLY);
        } catch (Exception e) {
            Timber.e(e);
            return READING_PSI_24HOURLY;
        }
    }

    public static void setReading(String reading) {
        try {
            AppApplication.getInstance().getSharedPreferences(SETTINGS, MODE_PRIVATE)
                    .edit()
                    .putString(SETTINGS_READING, reading)
                    .commit();
        } catch (Exception e) {
            Timber.e(e);
        }
    }
}
