/*
 * Copyright (C) 2012 47 Degrees, LLC
 *   http://47deg.com
 *   hello@47deg.com
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package com.fortysevendeg.android.adoptaunaplaya.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;
import com.androidplot.series.XYSeries;
import com.androidplot.xy.*;
import com.fortysevendeg.android.adoptaunaplaya.R;
import com.fortysevendeg.android.adoptaunaplaya.api.APIService;
import com.fortysevendeg.android.adoptaunaplaya.api.response.TotalBeachListResponse;
import com.fortysevendeg.android.adoptaunaplaya.api.response.TotalBeachResponse;
import it.restrung.rest.client.ContextAwareAPIDelegate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static it.restrung.rest.cache.RequestCache.LoadPolicy.NEVER;
import static it.restrung.rest.cache.RequestCache.StoragePolicy.DISABLED;

public class ChartsActivity extends BaseActivity {

    public enum KindCharts {
        TOP_TEN_USERS
    }

    public static final String KEY_KIND_CHARTS = "KEY_KIND_CHARTS";
    private KindCharts kindCharts;

    /**
     * Charts component
     */
    private XYPlot xyPlot;

    /**
     * @see BaseActivity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.charts_activity);

        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.containsKey(KEY_KIND_CHARTS)) {
            kindCharts = (KindCharts)extras.getSerializable(KEY_KIND_CHARTS);
            xyPlot = (XYPlot) findViewById(R.id.charts_xyplot);
            xyPlot.setDomainStep(XYStepMode.SUBDIVIDE, 3);
            switch (kindCharts) {
                case TOP_TEN_USERS:
                    APIService.get().getTopTenUsers(new ContextAwareAPIDelegate<TotalBeachListResponse>(ChartsActivity.this, TotalBeachListResponse.class, NEVER, DISABLED) {
                        @Override
                        public void onResults(TotalBeachListResponse totalBeachListResponse) {
                            //TODO Hack for test
                            //Number[] series1Numbers = {1, 8, 5, 2, 7, 4};

                            List<Number> numbers = new ArrayList<Number>();
                            for (TotalBeachResponse totalBeachResponse : totalBeachListResponse.getResults()) {
                                numbers.add(totalBeachResponse.getTotalBeach());
                            }

                            //XYSeries series1 = new SimpleXYSeries(Arrays.asList(series1Numbers), SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Series1");
                            XYSeries series1 = new SimpleXYSeries(numbers, SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, getString(R.string.topTenUsers));

                            LineAndPointFormatter series1Format = new LineAndPointFormatter(
                                    0xFFFF0000,
                                    0xFFFF0000,
                                    null);

                            //BarFormatter seriesFormat = new BarFormatter(0xFFFF0000, 0xFFFF0000);

                            xyPlot.addSeries(series1, series1Format);

                            xyPlot.setTicksPerRangeLabel(3);
                            xyPlot.disableAllMarkup();


                            Toast.makeText(ChartsActivity.this, "ok", Toast.LENGTH_LONG).show();

                            xyPlot.redraw();


                        }

                        @Override
                        public void onError(Throwable e) {
                            Toast.makeText(ChartsActivity.this, String.format("Error: %s", e.getMessage()), Toast.LENGTH_LONG).show();

                            //TODO Hack for test
                            Number[] series1Numbers = {1, 8, 5, 2, 7, 4};
                            Number[] series2Numbers = {4, 6, 3, 8, 2, 10};

                            XYSeries series1 = new SimpleXYSeries(Arrays.asList(series1Numbers), SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Series1");
                            XYSeries series2 = new SimpleXYSeries(Arrays.asList(series2Numbers), SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Series2");

                            LineAndPointFormatter series1Format = new LineAndPointFormatter(
                                    0xFFFF0000,
                                    0xFFFF0000,
                                    null);
                            xyPlot.addSeries(series1, series1Format);

                            xyPlot.addSeries(series2, new LineAndPointFormatter(Color.rgb(0, 0, 200), Color.rgb(0, 0, 100), null));

                            xyPlot.setTicksPerRangeLabel(3);
                            xyPlot.disableAllMarkup();
                            xyPlot.redraw();
                        }
                    });
                    break;
            }
        } else {
            Toast.makeText(this, "Error", Toast.LENGTH_LONG).show();
            finish();
        }

    }
}
