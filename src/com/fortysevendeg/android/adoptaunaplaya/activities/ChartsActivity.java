package com.fortysevendeg.android.adoptaunaplaya.activities;

import android.graphics.Color;
import android.os.Bundle;
import com.androidplot.series.XYSeries;
import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYPlot;
import com.fortysevendeg.android.adoptaunaplaya.R;

import java.util.Arrays;

public class ChartsActivity  extends BaseActivity {

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

        xyPlot = (XYPlot) findViewById(R.id.charts_xyplot);

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

    }
}
