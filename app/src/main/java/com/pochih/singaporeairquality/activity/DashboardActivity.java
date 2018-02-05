package com.pochih.singaporeairquality.activity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.pochih.singaporeairquality.AppApplication;
import com.pochih.singaporeairquality.R;
import com.pochih.singaporeairquality.entity.Region;
import com.pochih.singaporeairquality.helper.DataHelper;
import com.pochih.singaporeairquality.http.response.PsiResponse;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class DashboardActivity extends AppCompatActivity {

    @BindView(R.id.etDate)
    EditText etDate;
    @BindView(R.id.spArea)
    Spinner spArea;
    @BindView(R.id.chart)
    LineChart chart;
    @BindView(R.id.btnFilter)
    Button btnFilter;


    private String selectedArea = "central";
    private Calendar myCalendar = null;
    private Call<PsiResponse> httpCall;
    private ProgressDialog mDialog;
    private List<Region> regions;
    private ArrayAdapter<CharSequence> adapter;
    private SimpleDateFormat sdf;

    private LineData data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_dashboard);
            ButterKnife.bind(this);

            //region Get intent arg
            Intent intent = getIntent();
            if (intent != null && intent.getStringExtra("AREA") != null && !intent.getStringExtra("AREA").equals("")) {
                selectedArea = intent.getStringExtra("AREA").toLowerCase();
            }
            //endregion

            //region Setup ProgressDialog
            mDialog = new ProgressDialog(this);
            mDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
            mDialog.setCancelable(false);
            mDialog.setCancelable(false);
            mDialog.setTitle(getString(R.string.text_Loading));
            mDialog.setMessage(getString(R.string.text_Wait_while_loading));
            //endregion

            //region Setup DatePicker
            sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
            myCalendar = Calendar.getInstance();
            myCalendar.add(Calendar.DATE, -1);
            etDate.setText(sdf.format(myCalendar.getTime()));
            etDate.setInputType(InputType.TYPE_NULL);
            etDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            Calendar selectedCalender = Calendar.getInstance();
                            selectedCalender.set(Calendar.YEAR, year);
                            selectedCalender.set(Calendar.MONTH, monthOfYear);
                            selectedCalender.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                            if (selectedCalender.before(myCalendar)) {
                                etDate.setText(sdf.format(selectedCalender.getTime()));
                            } else {
                                Calendar today = Calendar.getInstance();
                                Toast.makeText(getApplicationContext(), "Please choose a date before " + sdf.format(today.getTime()), Toast.LENGTH_LONG).show();
                            }
                        }
                    };

                    new DatePickerDialog(DashboardActivity.this, date, myCalendar
                            .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                            myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                }
            });
            //endregion

            //region Setup selectedArea spinner
            adapter = ArrayAdapter.createFromResource(DashboardActivity.this,
                    R.array.areas,
                    android.R.layout.simple_spinner_dropdown_item);
            spArea.setAdapter(adapter);
            spArea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    selectedArea = adapter.getItem(position).toString();
                    updateChart();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            //endregion
        } catch (Exception e) {
            Timber.e(e);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        for (int i = 0; i < adapter.getCount(); i++) {
            if (adapter.getItem(i).toString().toLowerCase().equals(selectedArea)) {
                spArea.setSelection(i);
            }
        }

        apiCallGetPsi();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (httpCall.isExecuted()) {
            httpCall.cancel();
        }
    }

    @OnClick(R.id.btnFilter)
    public void btnFilterOnClick(View view) {
        apiCallGetPsi();
    }

    private void apiCallGetPsi() {
        try {
            mDialog.show();

            httpCall = AppApplication.getInstance().getDataGovSgService().getPsi(String.valueOf(etDate.getText()));
            httpCall.enqueue(new Callback<PsiResponse>() {
                @Override
                public void onResponse(Call<PsiResponse> call, Response<PsiResponse> response) {
                    try {
                        if (response.code() == 200) {
                            PsiResponse psiResponse = response.body();
                            regions = DataHelper.convertToRegions(psiResponse);
                            updateChart();
                        } else {
                            Timber.e("response.code: " + response.code());
                        }
                    } catch (Exception e) {
                        Timber.e(e);
                    } finally {
                        mDialog.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<PsiResponse> call, Throwable t) {
                    Timber.e(t);
                    mDialog.dismiss();
                }
            });
        } catch (Exception ex) {
            Timber.e(ex);
        }
    }

    private void updateChart() {
        if (regions != null && regions.size() > 0) {
            // Data
            final ArrayList<String> xVals = new ArrayList<>();

            ArrayList<Entry> yValsO3_SubIndex = new ArrayList<>();
            ArrayList<Entry> yValsPm10_24Hourly = new ArrayList<>();
            ArrayList<Entry> yValsPm10_SubIndex = new ArrayList<>();
            ArrayList<Entry> yValsCo_SubIndex = new ArrayList<>();
            ArrayList<Entry> yValsPm25_24Hourly = new ArrayList<>();
            ArrayList<Entry> yValsSo2_SubIndex = new ArrayList<>();
            ArrayList<Entry> yValsCo_8HrMax = new ArrayList<>();
            ArrayList<Entry> yValsNo2_1hrMax = new ArrayList<>();
            ArrayList<Entry> yValsSo2_24Hourly = new ArrayList<>();
            ArrayList<Entry> yValsPm25_SubIndex = new ArrayList<>();
            ArrayList<Entry> yValsPsi_24Hourly = new ArrayList<>();
            ArrayList<Entry> yValsO3_8hrMax = new ArrayList<>();


            Region displayRegion = null;
            for (int i = 0; i < regions.size(); i++) {
                if (regions.get(i).getName().toLowerCase().equals(selectedArea.toLowerCase())) {
                    displayRegion = regions.get(i);
                    break;
                }
            }

            SimpleDateFormat sdf = new SimpleDateFormat("HH");
            if (displayRegion != null) {
                for (int i = 0; i < displayRegion.getReadings().size(); i++) {
                    xVals.add(sdf.format(displayRegion.getReadings().get(i).getTimestamp()));

                    yValsO3_SubIndex.add(new Entry(i, displayRegion.getReadings().get(i).getO3_SubIndex()));
                    yValsPm10_24Hourly.add(new Entry(i, displayRegion.getReadings().get(i).getPm10_24Hourly()));
                    yValsPm10_SubIndex.add(new Entry(i, displayRegion.getReadings().get(i).getPm10_SubIndex()));
                    yValsCo_SubIndex.add(new Entry(i, displayRegion.getReadings().get(i).getCo_SubIndex()));
                    yValsPm25_24Hourly.add(new Entry(i, displayRegion.getReadings().get(i).getPm25_24hourly()));
                    yValsSo2_SubIndex.add(new Entry(i, displayRegion.getReadings().get(i).getSo2_SubIndex()));
                    yValsCo_8HrMax.add(new Entry(i, displayRegion.getReadings().get(i).getCo_8HrMax()));
                    yValsNo2_1hrMax.add(new Entry(i, displayRegion.getReadings().get(i).getNo2_1HrMax()));
                    yValsSo2_24Hourly.add(new Entry(i, displayRegion.getReadings().get(i).getSo2_24Hourly()));
                    yValsPm25_SubIndex.add(new Entry(i, displayRegion.getReadings().get(i).getPm25_SubIndex()));
                    yValsPsi_24Hourly.add(new Entry(i, displayRegion.getReadings().get(i).getPsi_24Hourly()));
                    yValsO3_8hrMax.add(new Entry(i, displayRegion.getReadings().get(i).getO3_8HrMax()));
                }
            }

            // XAxis
            XAxis xAxis = chart.getXAxis();
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            xAxis.setDrawGridLines(true);
            xAxis.setGridLineWidth(1f);
            xAxis.setValueFormatter(new IAxisValueFormatter() {
                @Override
                public String getFormattedValue(float value, AxisBase axis) {
                    return xVals.get((int) value) + ":00";
                }
            });

            // YAxis
            LineDataSet ldsO3_SubIndex = new LineDataSet(yValsO3_SubIndex, "O3 Sub index");
            ldsO3_SubIndex.setColor(getApplicationContext().getResources().getColor(R.color.color_O3_SubIndex));
            LineDataSet ldsPm10_24Hourly = new LineDataSet(yValsPm10_24Hourly, "PM 10 24 hourly");
            ldsPm10_24Hourly.setColor(getApplicationContext().getResources().getColor(R.color.color_PM10_24Hourly));
            LineDataSet ldsPm10_SubIndex = new LineDataSet(yValsPm10_SubIndex, "PM 10 Sub index");
            ldsPm10_SubIndex.setColor(getApplicationContext().getResources().getColor(R.color.color_PM10_SubIndex));
            LineDataSet ldsCo_SubIndex = new LineDataSet(yValsCo_SubIndex, "CO Sub index");
            ldsCo_SubIndex.setColor(getApplicationContext().getResources().getColor(R.color.color_Co_SubIndex));
            LineDataSet ldsPm25_24Hourly = new LineDataSet(yValsPm25_24Hourly, "PM 2.5 24 Hourly");
            ldsPm25_24Hourly.setColor(getApplicationContext().getResources().getColor(R.color.color_Pm25_24Hourly));
            LineDataSet ldsSo2_SubIndex = new LineDataSet(yValsSo2_SubIndex, "SO2 Sub index");
            ldsSo2_SubIndex.setColor(getApplicationContext().getResources().getColor(R.color.color_So2_SubIndex));
            LineDataSet ldsCo_8HrMax = new LineDataSet(yValsCo_8HrMax, "CO 8Hr max");
            ldsCo_8HrMax.setColor(getApplicationContext().getResources().getColor(R.color.color_Co_8HrMax));
            LineDataSet ldsNo2_1hrMax = new LineDataSet(yValsNo2_1hrMax, "NO2 1Hr max");
            ldsNo2_1hrMax.setColor(getApplicationContext().getResources().getColor(R.color.color_No2_1HrMax));
            LineDataSet ldsSo2_24Hourly = new LineDataSet(yValsSo2_24Hourly, "SO2 24 Hourly");
            ldsSo2_24Hourly.setColor(getApplicationContext().getResources().getColor(R.color.color_So2_24Hourly));
            LineDataSet ldsPm25_SubIndex = new LineDataSet(yValsPm25_SubIndex, "Pm 2.5 Sub index");
            ldsPm25_SubIndex.setColor(getApplicationContext().getResources().getColor(R.color.color_PM25_SubIndex));
            LineDataSet ldsPsi_24Hourly = new LineDataSet(yValsPsi_24Hourly, "PSI 24 Hourly");
            ldsPsi_24Hourly.setColor(getApplicationContext().getResources().getColor(R.color.color_Psi_24Hourly));
            LineDataSet ldsO3_8hrMax = new LineDataSet(yValsO3_8hrMax, "O3 8hr max");
            ldsO3_8hrMax.setColor(getApplicationContext().getResources().getColor(R.color.color_O3_8HrMax));

            //
            data = new LineData(
                    ldsO3_SubIndex
                    , ldsPm10_24Hourly
                    , ldsPm10_SubIndex
                    , ldsCo_SubIndex
                    , ldsPm25_24Hourly
                    , ldsSo2_SubIndex
                    , ldsCo_8HrMax
                    , ldsNo2_1hrMax
                    , ldsSo2_24Hourly
                    , ldsPm25_SubIndex
                    , ldsPsi_24Hourly
                    , ldsO3_8hrMax
            );
            chart.setData(data);
            chart.animateY(1500);
        } else {
            Timber.e("regions is null or length is 0");
        }
    }
}
