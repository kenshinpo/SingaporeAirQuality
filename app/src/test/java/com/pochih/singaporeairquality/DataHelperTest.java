package com.pochih.singaporeairquality;

import com.google.gson.Gson;
import com.pochih.singaporeairquality.entity.Region;
import com.pochih.singaporeairquality.helper.DataHelper;
import com.pochih.singaporeairquality.http.response.PsiResponse;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;

/**
 * Created by PoChih(A-Po) on 2018/02/05.
 */

public class DataHelperTest {

    private PsiResponse psiResponse;

    @Before
    public void setupData() {
        //region source data(json format)
        String jsonFormat = "{\n" +
                "    \"region_metadata\": [\n" +
                "        {\n" +
                "            \"name\": \"west\",\n" +
                "            \"label_location\": {\n" +
                "                \"latitude\": 1.35735,\n" +
                "                \"longitude\": 103.7\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": \"national\",\n" +
                "            \"label_location\": {\n" +
                "                \"latitude\": 0,\n" +
                "                \"longitude\": 0\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": \"east\",\n" +
                "            \"label_location\": {\n" +
                "                \"latitude\": 1.35735,\n" +
                "                \"longitude\": 103.94\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": \"central\",\n" +
                "            \"label_location\": {\n" +
                "                \"latitude\": 1.35735,\n" +
                "                \"longitude\": 103.82\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": \"south\",\n" +
                "            \"label_location\": {\n" +
                "                \"latitude\": 1.29587,\n" +
                "                \"longitude\": 103.82\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": \"north\",\n" +
                "            \"label_location\": {\n" +
                "                \"latitude\": 1.41803,\n" +
                "                \"longitude\": 103.82\n" +
                "            }\n" +
                "        }\n" +
                "    ],\n" +
                "    \"items\": [\n" +
                "        {\n" +
                "            \"timestamp\": \"2018-02-05T08:00:00+08:00\",\n" +
                "            \"update_timestamp\": \"2018-02-05T08:06:19+08:00\",\n" +
                "            \"readings\": {\n" +
                "                \"o3_sub_index\": {\n" +
                "                    \"west\": 26,\n" +
                "                    \"national\": 26,\n" +
                "                    \"east\": 22,\n" +
                "                    \"central\": 21,\n" +
                "                    \"south\": 20,\n" +
                "                    \"north\": 25\n" +
                "                },\n" +
                "                \"pm10_twenty_four_hourly\": {\n" +
                "                    \"west\": 30,\n" +
                "                    \"national\": 63,\n" +
                "                    \"east\": 32,\n" +
                "                    \"central\": 43,\n" +
                "                    \"south\": 63,\n" +
                "                    \"north\": 35\n" +
                "                },\n" +
                "                \"pm10_sub_index\": {\n" +
                "                    \"west\": 30,\n" +
                "                    \"national\": 57,\n" +
                "                    \"east\": 32,\n" +
                "                    \"central\": 43,\n" +
                "                    \"south\": 57,\n" +
                "                    \"north\": 35\n" +
                "                },\n" +
                "                \"co_sub_index\": {\n" +
                "                    \"west\": 7,\n" +
                "                    \"national\": 7,\n" +
                "                    \"east\": 4,\n" +
                "                    \"central\": 7,\n" +
                "                    \"south\": 6,\n" +
                "                    \"north\": 6\n" +
                "                },\n" +
                "                \"pm25_twenty_four_hourly\": {\n" +
                "                    \"west\": 13,\n" +
                "                    \"national\": 18,\n" +
                "                    \"east\": 15,\n" +
                "                    \"central\": 16,\n" +
                "                    \"south\": 18,\n" +
                "                    \"north\": 15\n" +
                "                },\n" +
                "                \"so2_sub_index\": {\n" +
                "                    \"west\": 1,\n" +
                "                    \"national\": 2,\n" +
                "                    \"east\": 1,\n" +
                "                    \"central\": 2,\n" +
                "                    \"south\": 2,\n" +
                "                    \"north\": 2\n" +
                "                },\n" +
                "                \"co_eight_hour_max\": {\n" +
                "                    \"west\": 0.71,\n" +
                "                    \"national\": 0.71,\n" +
                "                    \"east\": 0.36,\n" +
                "                    \"central\": 0.7,\n" +
                "                    \"south\": 0.58,\n" +
                "                    \"north\": 0.59\n" +
                "                },\n" +
                "                \"no2_one_hour_max\": {\n" +
                "                    \"west\": 9,\n" +
                "                    \"national\": 58,\n" +
                "                    \"east\": 29,\n" +
                "                    \"central\": 58,\n" +
                "                    \"south\": 45,\n" +
                "                    \"north\": 33\n" +
                "                },\n" +
                "                \"so2_twenty_four_hourly\": {\n" +
                "                    \"west\": 2,\n" +
                "                    \"national\": 3,\n" +
                "                    \"east\": 2,\n" +
                "                    \"central\": 3,\n" +
                "                    \"south\": 3,\n" +
                "                    \"north\": 2\n" +
                "                },\n" +
                "                \"pm25_sub_index\": {\n" +
                "                    \"west\": 52,\n" +
                "                    \"national\": 58,\n" +
                "                    \"east\": 54,\n" +
                "                    \"central\": 56,\n" +
                "                    \"south\": 58,\n" +
                "                    \"north\": 54\n" +
                "                },\n" +
                "                \"psi_twenty_four_hourly\": {\n" +
                "                    \"west\": 52,\n" +
                "                    \"national\": 58,\n" +
                "                    \"east\": 54,\n" +
                "                    \"central\": 56,\n" +
                "                    \"south\": 58,\n" +
                "                    \"north\": 54\n" +
                "                },\n" +
                "                \"o3_eight_hour_max\": {\n" +
                "                    \"west\": 62,\n" +
                "                    \"national\": 62,\n" +
                "                    \"east\": 53,\n" +
                "                    \"central\": 49,\n" +
                "                    \"south\": 48,\n" +
                "                    \"north\": 58\n" +
                "                }\n" +
                "            }\n" +
                "        }\n" +
                "    ],\n" +
                "    \"api_info\": {\n" +
                "        \"status\": \"healthy\"\n" +
                "    }\n" +
                "}";
        //endregion

        Gson gson = new Gson();
        psiResponse = gson.fromJson(jsonFormat, PsiResponse.class);
    }


    @Test
    public void convertToRegionsTest() throws Exception {
        List<Region> regions = DataHelper.convertToRegions(psiResponse);

        MatcherAssert.assertThat(regions, hasSize(6));
        MatcherAssert.assertThat(regions.get(0).getName(), Matchers.is("west"));
        MatcherAssert.assertThat(regions.get(1).getName(), Matchers.is("national"));
        MatcherAssert.assertThat(regions.get(2).getName(), Matchers.is("east"));
        MatcherAssert.assertThat(regions.get(3).getName(), Matchers.is("central"));
        MatcherAssert.assertThat(regions.get(4).getName(), Matchers.is("south"));
        MatcherAssert.assertThat(regions.get(5).getName(), Matchers.is("north"));
    }
}
