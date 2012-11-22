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

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.androidplot.series.XYSeries;
import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYPlot;
import com.fortysevendeg.android.adoptaunaplaya.R;
import com.fortysevendeg.android.adoptaunaplaya.api.APIService;
import com.fortysevendeg.android.adoptaunaplaya.api.response.BeachListResponse;
import com.fortysevendeg.android.adoptaunaplaya.api.response.BeachResponse;
import com.fortysevendeg.android.adoptaunaplaya.api.response.TotalBeachListResponse;
import com.fortysevendeg.android.adoptaunaplaya.api.response.TotalBeachResponse;
import it.restrung.rest.client.ContextAwareAPIDelegate;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.List;

import static it.restrung.rest.cache.RequestCache.LoadPolicy.NEVER;
import static it.restrung.rest.cache.RequestCache.StoragePolicy.DISABLED;

public class ChartsActivity extends BaseActivity {

    public enum KindCharts {
        TOP_TEN_USERS,
        TOP_ENTEROCOCOS
    }

    public static final String KEY_KIND_CHARTS = "KEY_KIND_CHARTS";
    private KindCharts kindCharts;
    private ProgressBar pbLoading;

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
            kindCharts = (KindCharts) extras.getSerializable(KEY_KIND_CHARTS);
            xyPlot = (XYPlot) findViewById(R.id.charts_xyplot);
            pbLoading = (ProgressBar) findViewById(R.id.charts_pb_loading);
            pbLoading.setVisibility(View.VISIBLE);
            final MyIndexFormat myIndexFormat = new MyIndexFormat();
            switch (kindCharts) {
                case TOP_TEN_USERS:
                    xyPlot.setTitle(getString(R.string.topTenUsers));
                    APIService.get().getTopTenUsers(new ContextAwareAPIDelegate<TotalBeachListResponse>(ChartsActivity.this, TotalBeachListResponse.class, NEVER, DISABLED) {
                        @Override
                        public void onResults(TotalBeachListResponse totalBeachListResponse) {
                            List<Number> numbers = new ArrayList<Number>();
                            List<String> names = new ArrayList<String>();
                            for (TotalBeachResponse totalBeachResponse : totalBeachListResponse.getResults()) {
                                if (!TextUtils.isEmpty(totalBeachResponse.getUser())) {
                                    numbers.add(totalBeachResponse.getTotalBeach());
                                    names.add(totalBeachResponse.getUser());
                                }
                            }

                            String[] stringsNames = names.toArray(new String[0]);
                            myIndexFormat.labels = stringsNames;

                            XYSeries series1 = new SimpleXYSeries(numbers, SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, getString(R.string.topTenUsers));
                            LineAndPointFormatter series1Format = new LineAndPointFormatter(
                                    0xFFFF0000,
                                    0xFFFF0000,
                                    null);
                            xyPlot.addSeries(series1, series1Format);
                            xyPlot.setTicksPerRangeLabel(3);
                            xyPlot.disableAllMarkup();
                            xyPlot.getGraphWidget().setDomainValueFormat(myIndexFormat);
                            xyPlot.redraw();
                            pbLoading.setVisibility(View.GONE);
                        }

                        @Override
                        public void onError(Throwable e) {
                            pbLoading.setVisibility(View.GONE);
                            Toast.makeText(ChartsActivity.this, String.format("Error: %s", e.getMessage()), Toast.LENGTH_LONG).show();
                        }
                    });
                    break;
                case TOP_ENTEROCOCOS:
                    xyPlot.setTitle(getString(R.string.beachesWithEnterococos));
                    APIService.get().getBeachesWithMoreEnterococos(new ContextAwareAPIDelegate<BeachListResponse>(ChartsActivity.this, BeachListResponse.class, NEVER, DISABLED) {
                        @Override
                        public void onResults(BeachListResponse beachListResponse) {
                            List<Number> numbers = new ArrayList<Number>();
                            List<String> names = new ArrayList<String>();
                            for (BeachResponse beachResponse : beachListResponse.getResults()) {
                                numbers.add(Integer.valueOf(beachResponse.getEnterococo()));
                                if (beachResponse.getNombre().length() > 12) {
                                    names.add(String.format("%s...", beachResponse.getNombre().substring(0, 9)));
                                } else {
                                    names.add(beachResponse.getNombre());
                                }
                            }

                            String[] stringsNames = names.toArray(new String[0]);
                            myIndexFormat.labels = stringsNames;

                            XYSeries series1 = new SimpleXYSeries(numbers, SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, getString(R.string.topTenUsers));
                            LineAndPointFormatter series1Format = new LineAndPointFormatter(
                                    0xFFFF0000,
                                    0xFFFF0000,
                                    null);
                            xyPlot.addSeries(series1, series1Format);
                            xyPlot.setTicksPerRangeLabel(3);
                            xyPlot.disableAllMarkup();
                            xyPlot.getGraphWidget().setDomainValueFormat(myIndexFormat);
                            xyPlot.redraw();
                            pbLoading.setVisibility(View.GONE);
                        }

                        @Override
                        public void onError(Throwable e) {
                            pbLoading.setVisibility(View.GONE);
                            Toast.makeText(ChartsActivity.this, String.format("Error: %s", e.getMessage()), Toast.LENGTH_LONG).show();
                        }
                    });
                    break;
            }
        } else {
            Toast.makeText(this, "Error", Toast.LENGTH_LONG).show();
            finish();
        }

    }

    public class MyIndexFormat extends Format {

        public String[] labels = null;

        @Override
        public StringBuffer format(Object obj,
                                   StringBuffer toAppendTo,
                                   FieldPosition pos) {

            float fl = ((Number) obj).floatValue();
            int index = Math.round(fl);
            if (index >= 0 && index < labels.length) {
                return new StringBuffer(labels[index]);
            } else {
                return new StringBuffer("");
            }
        }

        @Override
        public String parseObject(String s, ParsePosition parsePosition) {
            return null;
        }
    }

    ;
}