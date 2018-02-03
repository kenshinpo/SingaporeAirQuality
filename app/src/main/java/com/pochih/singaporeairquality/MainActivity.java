package com.pochih.singaporeairquality;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.pochih.singaporeairquality.entity.Reading;
import com.pochih.singaporeairquality.entity.Region;
import com.pochih.singaporeairquality.http.response.PsiResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    private Call<PsiResponse> httpCall;
    private List<Region> regions;

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
                regions = new ArrayList<>();

                List<Reading> readingsWest = new ArrayList<>();
                List<Reading> readingsEast = new ArrayList<>();
                List<Reading> readingsSouth = new ArrayList<>();
                List<Reading> readingsNorth = new ArrayList<>();
                List<Reading> readingsCentral = new ArrayList<>();
                List<Reading> readingsNational = new ArrayList<>();

                for (int i = 0; i < psiResponse.getItems().size(); i++) {
                    PsiResponse.Item item = psiResponse.getItems().get(i);
                    readingsWest.add(new Reading(
                            item.getTimestamp(),
                            item.getUpdate_timestamp(),
                            item.getReadings().getO3_sub_index().getWest(),
                            item.getReadings().getPm10_twenty_four_hourly().getWest(),
                            item.getReadings().getPm10_sub_index().getWest(),
                            item.getReadings().getCo_sub_index().getWest(),
                            item.getReadings().getPm25_twenty_four_hourly().getWest(),
                            item.getReadings().getSo2_sub_index().getWest(),
                            item.getReadings().getCo_eight_hour_max().getWest(),
                            item.getReadings().getNo2_one_hour_max().getWest(),
                            item.getReadings().getSo2_twenty_four_hourly().getWest(),
                            item.getReadings().getPm25_sub_index().getWest(),
                            item.getReadings().getPsi_twenty_four_hourly().getWest(),
                            item.getReadings().getO3_eight_hour_max().getWest()
                    ));

                    readingsEast.add(new Reading(
                            item.getTimestamp(),
                            item.getUpdate_timestamp(),
                            item.getReadings().getO3_sub_index().getEast(),
                            item.getReadings().getPm10_twenty_four_hourly().getEast(),
                            item.getReadings().getPm10_sub_index().getEast(),
                            item.getReadings().getCo_sub_index().getEast(),
                            item.getReadings().getPm25_twenty_four_hourly().getEast(),
                            item.getReadings().getSo2_sub_index().getEast(),
                            item.getReadings().getCo_eight_hour_max().getEast(),
                            item.getReadings().getNo2_one_hour_max().getEast(),
                            item.getReadings().getSo2_twenty_four_hourly().getEast(),
                            item.getReadings().getPm25_sub_index().getEast(),
                            item.getReadings().getPsi_twenty_four_hourly().getEast(),
                            item.getReadings().getO3_eight_hour_max().getEast()
                    ));

                    readingsSouth.add(new Reading(
                            item.getTimestamp(),
                            item.getUpdate_timestamp(),
                            item.getReadings().getO3_sub_index().getSouth(),
                            item.getReadings().getPm10_twenty_four_hourly().getSouth(),
                            item.getReadings().getPm10_sub_index().getSouth(),
                            item.getReadings().getCo_sub_index().getSouth(),
                            item.getReadings().getPm25_twenty_four_hourly().getSouth(),
                            item.getReadings().getSo2_sub_index().getSouth(),
                            item.getReadings().getCo_eight_hour_max().getSouth(),
                            item.getReadings().getNo2_one_hour_max().getSouth(),
                            item.getReadings().getSo2_twenty_four_hourly().getSouth(),
                            item.getReadings().getPm25_sub_index().getSouth(),
                            item.getReadings().getPsi_twenty_four_hourly().getSouth(),
                            item.getReadings().getO3_eight_hour_max().getSouth()
                    ));

                    readingsNorth.add(new Reading(
                            item.getTimestamp(),
                            item.getUpdate_timestamp(),
                            item.getReadings().getO3_sub_index().getNorth(),
                            item.getReadings().getPm10_twenty_four_hourly().getNorth(),
                            item.getReadings().getPm10_sub_index().getNorth(),
                            item.getReadings().getCo_sub_index().getNorth(),
                            item.getReadings().getPm25_twenty_four_hourly().getNorth(),
                            item.getReadings().getSo2_sub_index().getNorth(),
                            item.getReadings().getCo_eight_hour_max().getNorth(),
                            item.getReadings().getNo2_one_hour_max().getNorth(),
                            item.getReadings().getSo2_twenty_four_hourly().getNorth(),
                            item.getReadings().getPm25_sub_index().getNorth(),
                            item.getReadings().getPsi_twenty_four_hourly().getNorth(),
                            item.getReadings().getO3_eight_hour_max().getNorth()
                    ));

                    readingsCentral.add(new Reading(
                            item.getTimestamp(),
                            item.getUpdate_timestamp(),
                            item.getReadings().getO3_sub_index().getCentral(),
                            item.getReadings().getPm10_twenty_four_hourly().getCentral(),
                            item.getReadings().getPm10_sub_index().getCentral(),
                            item.getReadings().getCo_sub_index().getCentral(),
                            item.getReadings().getPm25_twenty_four_hourly().getCentral(),
                            item.getReadings().getSo2_sub_index().getCentral(),
                            item.getReadings().getCo_eight_hour_max().getCentral(),
                            item.getReadings().getNo2_one_hour_max().getCentral(),
                            item.getReadings().getSo2_twenty_four_hourly().getCentral(),
                            item.getReadings().getPm25_sub_index().getCentral(),
                            item.getReadings().getPsi_twenty_four_hourly().getCentral(),
                            item.getReadings().getO3_eight_hour_max().getCentral()
                    ));

                    readingsNational.add(new Reading(
                            item.getTimestamp(),
                            item.getUpdate_timestamp(),
                            item.getReadings().getO3_sub_index().getNational(),
                            item.getReadings().getPm10_twenty_four_hourly().getNational(),
                            item.getReadings().getPm10_sub_index().getNational(),
                            item.getReadings().getCo_sub_index().getNational(),
                            item.getReadings().getPm25_twenty_four_hourly().getNational(),
                            item.getReadings().getSo2_sub_index().getNational(),
                            item.getReadings().getCo_eight_hour_max().getNational(),
                            item.getReadings().getNo2_one_hour_max().getNational(),
                            item.getReadings().getSo2_twenty_four_hourly().getNational(),
                            item.getReadings().getPm25_sub_index().getNational(),
                            item.getReadings().getPsi_twenty_four_hourly().getNational(),
                            item.getReadings().getO3_eight_hour_max().getNational()
                    ));
                }


                for (int i = 0; i < psiResponse.getRegion_metadata().size(); i++) {
                    Region region = new Region(psiResponse.getRegion_metadata().get(i).getName(), psiResponse.getRegion_metadata().get(i).getLabel_location().getLatitude(), psiResponse.getRegion_metadata().get(i).getLabel_location().getLongitude());
                    if (psiResponse.getRegion_metadata().get(i).getName().equals("west")) {
                        region.setReadings(readingsWest);
                    } else if (psiResponse.getRegion_metadata().get(i).getName().equals("east")) {
                        region.setReadings(readingsEast);
                    } else if (psiResponse.getRegion_metadata().get(i).getName().equals("south")) {
                        region.setReadings(readingsSouth);
                    } else if (psiResponse.getRegion_metadata().get(i).getName().equals("north")) {
                        region.setReadings(readingsNorth);
                    } else if (psiResponse.getRegion_metadata().get(i).getName().equals("central")) {
                        region.setReadings(readingsCentral);
                    } else if (psiResponse.getRegion_metadata().get(i).getName().equals("national")) {
                        region.setReadings(readingsNational);
                    } else {
                        // do nothing
                    }
                    regions.add(region);
                }
            }

            @Override
            public void onFailure(Call<PsiResponse> call, Throwable t) {
                Timber.e(t);
            }
        });
    }
}
