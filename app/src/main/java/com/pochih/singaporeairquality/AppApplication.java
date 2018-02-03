package com.pochih.singaporeairquality;

import android.app.Application;

import com.pochih.singaporeairquality.http.service.IDataGovSgService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

/**
 * Created by PoChih(A-Po) on 2018/02/03.
 */

public class AppApplication extends Application {

    private static AppApplication instance;

    public static synchronized AppApplication getInstance() {
        if (instance == null) {
            instance = new AppApplication();
        }
        return instance;
    }

    private IDataGovSgService dataGovSgService;


    @Override
    public void onCreate() {
        super.onCreate();

        //region Step 1. Initial settings
        Timber.plant(new Timber.DebugTree());
        instance = this;
        //endregion

        //region Step 2. Initial Http call setting
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.data.gov.sg/v1/environment/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        dataGovSgService = retrofit.create(IDataGovSgService.class);
        //endregion
    }

    public IDataGovSgService getDataGovSgService() {
        return dataGovSgService;
    }
}
