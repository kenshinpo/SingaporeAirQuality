package com.pochih.singaporeairquality;

import com.pochih.singaporeairquality.http.response.PsiResponse;
import com.pochih.singaporeairquality.http.service.IDataGovSgService;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.BeforeClass;
import org.junit.Test;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by PoChih(A-Po) on 2018/02/03.
 */

public class ApiCallTest {
    private static IDataGovSgService dataGovSgService;

    @BeforeClass
    public static void initRetrofit() throws Exception {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Settings.getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        dataGovSgService = retrofit.create(IDataGovSgService.class);
    }

    @Test
    public void getPsi() {
        Call<PsiResponse> httpCall = dataGovSgService.getPsi();
        httpCall.enqueue(new Callback<PsiResponse>() {
            @Override
            public void onResponse(Call<PsiResponse> call, Response<PsiResponse> response) {
                PsiResponse actual = response.body();
                MatcherAssert.assertThat(actual.getRegion_metadata(), Matchers.hasSize(6));
                MatcherAssert.assertThat(actual.getItems(), Matchers.hasSize(1));
            }

            @Override
            public void onFailure(Call<PsiResponse> call, Throwable t) {
            }
        });
    }

}
