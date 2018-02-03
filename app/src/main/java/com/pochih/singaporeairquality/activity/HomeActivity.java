package com.pochih.singaporeairquality.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.pochih.singaporeairquality.AppApplication;
import com.pochih.singaporeairquality.R;
import com.pochih.singaporeairquality.Settings;
import com.pochih.singaporeairquality.entity.Reading;
import com.pochih.singaporeairquality.entity.Region;
import com.pochih.singaporeairquality.http.response.PsiResponse;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class HomeActivity extends AppCompatActivity implements OnMapReadyCallback {

    @BindView(R.id.tvUpdateDateTime)
    TextView tvUpdateDateTime;
    @BindView(R.id.ibRefresh)
    ImageButton ibRefresh;
    @BindView(R.id.ibChoose)
    ImageButton ibChoose;

    AlertDialog.Builder dialog;
    final String[] airItems = {
            "PSI - 24 hourly"
            , "PM 2.5 - 24 hourly"
            , "PM 2.5 - sub index"
            , "PM 10 - 24 hourly"
            , "PM 10 - sub index"
            , "SO2 - 24 hourly"
            , "SO2 - sub index"
            , "CO - 8hr max"
            , "CO - sub index"
            , "O3 - 8hr max"
            , "O3 - sub index"
            , "NO2 - 1hr max"
    };

    private Call<PsiResponse> httpCall;

    private GoogleMap mMap;
    private List<Region> regions;
    private SupportMapFragment mapFragment;

    private ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_home);
            ButterKnife.bind(this);

            //region Setup ProgressDialog
            mDialog = new ProgressDialog(this);
            mDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
            mDialog.setCancelable(false);
            mDialog.setCancelable(false);
            mDialog.setTitle(getString(R.string.text_Loading));
            mDialog.setMessage(getString(R.string.text_Wait_while_loading));
            //endregion
        } catch (Exception e) {
            Timber.e(e);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        apiCallGetPsi();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (httpCall.isExecuted()) {
            httpCall.cancel();
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        try {
            mMap = googleMap;
            mMap.clear();
            mMap.getUiSettings().setMapToolbarEnabled(false);

            LatLng central = new LatLng(1.35735, 103.82);
            for (int i = 0; i < regions.size(); i++) {
                if (regions.get(i).getLatitude() != 0.0 && regions.get(i).getLongitude() != 0.0) {
                    LatLng latLng = new LatLng(regions.get(i).getLatitude(), regions.get(i).getLongitude());
                    MarkerOptions mo = new MarkerOptions();
                    mo.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                    mo.position(latLng).title(regions.get(i).getName().toUpperCase());
                    if (Settings.getReading().equals(Settings.READING_O3_SUB_INDEX)) {
                        mo.snippet("O3 sub index: " + regions.get(i).getReadings().get(0).getO3_SubIndex());
                    } else if (Settings.getReading().equals(Settings.READING_PM10_24HOURLY)) {
                        mo.snippet("PM10 24 hourly: " + regions.get(i).getReadings().get(0).getPm10_24Hourly());
                    } else if (Settings.getReading().equals(Settings.READING_PM10_SUB_INDEX)) {
                        mo.snippet("PM10 sub index: " + regions.get(i).getReadings().get(0).getPm10_SubIndex());
                    } else if (Settings.getReading().equals(Settings.READING_CO_SUB_INDEX)) {
                        mo.snippet("CO sub index: " + regions.get(i).getReadings().get(0).getCo_SubIndex());
                    } else if (Settings.getReading().equals(Settings.READING_PM25_24HOURLY)) {
                        mo.snippet("PM2.5 24 hourly: " + regions.get(i).getReadings().get(0).getPm25_24hourly());
                        if (regions.get(i).getReadings().get(0).getPm25_24hourly() < 56) {
                            mo.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                        } else if (regions.get(i).getReadings().get(0).getPm25_24hourly() > 55 && regions.get(i).getReadings().get(0).getPm25_24hourly() < 151) {
                            mo.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
                        } else {
                            mo.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                        }
                    } else if (Settings.getReading().equals(Settings.READING_SO2_SUB_INDEX)) {
                        mo.snippet("SO2 sub index: " + regions.get(i).getReadings().get(0).getSo2_SubIndex());
                    } else if (Settings.getReading().equals(Settings.READING_CO_8HR_MAX)) {
                        mo.snippet("CO 8hr max: " + regions.get(i).getReadings().get(0).getCo_8HrMax());
                    } else if (Settings.getReading().equals(Settings.READING_NO2_1HR_MAX)) {
                        mo.snippet("NO2 1hr max: " + regions.get(i).getReadings().get(0).getNo2_1HrMax());
                    } else if (Settings.getReading().equals(Settings.READING_SO2_24HOURLY)) {
                        mo.snippet("SO2 24 hourly: " + regions.get(i).getReadings().get(0).getSo2_24Hourly());
                    } else if (Settings.getReading().equals(Settings.READING_PM25_SUB_INDEX)) {
                        mo.snippet("PM2.5 sub index: " + regions.get(i).getReadings().get(0).getPm25_SubIndex());
                        if (regions.get(i).getReadings().get(0).getPm25_SubIndex() < 56) {
                            mo.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                        } else if (regions.get(i).getReadings().get(0).getPm25_SubIndex() > 55 && regions.get(i).getReadings().get(0).getPm25_SubIndex() < 151) {
                            mo.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
                        } else {
                            mo.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                        }
                    } else if (Settings.getReading().equals(Settings.READING_PSI_24HOURLY)) {
                        mo.snippet("PSI 24 hourly: " + regions.get(i).getReadings().get(0).getPsi_24Hourly());
                        if (regions.get(i).getReadings().get(0).getPsi_24Hourly() < 101) {
                            mo.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                        } else if (regions.get(i).getReadings().get(0).getPsi_24Hourly() > 100 && regions.get(i).getReadings().get(0).getPsi_24Hourly() < 201) {
                            mo.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
                        } else {
                            mo.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                        }
                    } else if (Settings.getReading().equals(Settings.READING_O3_8HR_MAX)) {
                        mo.snippet("O3 8hr max: " + regions.get(i).getReadings().get(0).getO3_8HrMax());
                    } else {
                        // do nothing
                    }

                    mMap.addMarker(mo);

                    if (regions.get(i).getName().toLowerCase().equals("central")) {
                        central = new LatLng(regions.get(i).getLatitude(), regions.get(i).getLongitude());
                    }
                }
            }

            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(central, 10.5f));

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
            tvUpdateDateTime.setText(getString(R.string.text_Update) + ": " + sdf.format(regions.get(0).getReadings().get(0).getUpdatedTimestamp()));
        } catch (Exception e) {
            Timber.e(e);
        }
    }

    @OnClick(R.id.ibRefresh)
    public void ibRefreshOnClick(View view) {
        apiCallGetPsi();
    }

    @OnClick(R.id.ibChoose)
    public void ibChooseOnClick(View view) {


        try {
            //region Show dialog for choosing item
            dialog = new AlertDialog.Builder(HomeActivity.this);
            dialog.setTitle(getString(R.string.text_Choose_item));
            dialog.setItems(airItems, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which) {
                        case 0:
                            Settings.setReading(Settings.READING_PSI_24HOURLY);
                            break;
                        case 1:
                            Settings.setReading(Settings.READING_PM25_24HOURLY);
                            break;
                        case 2:
                            Settings.setReading(Settings.READING_PM25_SUB_INDEX);
                            break;
                        case 3:
                            Settings.setReading(Settings.READING_PM10_24HOURLY);
                            break;
                        case 4:
                            Settings.setReading(Settings.READING_PM10_SUB_INDEX);
                            break;
                        case 5:
                            Settings.setReading(Settings.READING_SO2_24HOURLY);
                            break;
                        case 6:
                            Settings.setReading(Settings.READING_SO2_SUB_INDEX);
                            break;
                        case 7:
                            Settings.setReading(Settings.READING_CO_8HR_MAX);
                            break;
                        case 8:
                            Settings.setReading(Settings.READING_CO_SUB_INDEX);
                            break;
                        case 9:
                            Settings.setReading(Settings.READING_O3_8HR_MAX);
                            break;
                        case 10:
                            Settings.setReading(Settings.READING_O3_SUB_INDEX);
                            break;
                        case 11:
                            Settings.setReading(Settings.READING_NO2_1HR_MAX);
                            break;

                        default:
                            Settings.setReading(Settings.READING_PSI_24HOURLY);
                            break;
                    }

                    mapFragment.getMapAsync(HomeActivity.this);
                    dialog.dismiss();
                }
            });
            dialog.show();
            //endregion
        } catch (Exception e) {
            Timber.e(e);
        }
    }

    private void apiCallGetPsi() {
        try {
            mDialog.show();
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

                    mapFragment = (SupportMapFragment) getSupportFragmentManager()
                            .findFragmentById(R.id.map);
                    mapFragment.getMapAsync(HomeActivity.this);
                    mDialog.dismiss();
                }

                @Override
                public void onFailure(Call<PsiResponse> call, Throwable t) {
                    Timber.e(t);
                    mDialog.dismiss();
                }
            });
        } catch (Exception e) {
            Timber.e(e);
        }
    }
}
