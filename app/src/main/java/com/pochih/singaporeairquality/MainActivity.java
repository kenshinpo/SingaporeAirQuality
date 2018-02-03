package com.pochih.singaporeairquality;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.pochih.singaporeairquality.http.response.PsiResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    private Call<PsiResponse> httpCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();

        httpCall = AppApplication.getInstance().getDataGovSgService().getPsi();

        httpCall.enqueue(new Callback<PsiResponse>() {
            @Override
            public void onResponse(Call<PsiResponse> call, Response<PsiResponse> response) {
                PsiResponse psiResponse = response.body();
                PsiResponse.ApiInfo info = psiResponse.getApi_info();
            }

            @Override
            public void onFailure(Call<PsiResponse> call, Throwable t) {
                Timber.e(t);
            }
        });
    }
}
