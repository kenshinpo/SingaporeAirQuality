package com.pochih.singaporeairquality.http.service;

import com.pochih.singaporeairquality.http.response.PsiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by PoChih(A-Po) on 2018/02/03.
 */

public interface IDataGovSgService {
    @Headers({
            "api-key:0AlG3IAgp7Xs2jXJWfo2ZnfCCGsPAWuH"
    })
    @GET("psi")
    Call<PsiResponse> getPsi();

    @Headers({
            "api-key:0AlG3IAgp7Xs2jXJWfo2ZnfCCGsPAWuH"
    })
    @GET("psi")
    Call<PsiResponse> getPsi(@Query("date") String date);
}
