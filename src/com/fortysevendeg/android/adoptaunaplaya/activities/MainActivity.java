package com.fortysevendeg.android.adoptaunaplaya.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import com.fortysevendeg.android.adoptaunaplaya.R;

public class MainActivity extends BaseActivity {

    /**
     * Called when the activity is first created.
     */
    @Override
        public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        ImageButton bCharts = (ImageButton)findViewById(R.id.main_b_charts);
        bCharts.setOnClickListener(clickCharts);

        ImageButton bList = (ImageButton)findViewById(R.id.main_b_list);
        bList.setOnClickListener(clickList);

    }

    /**
     * Click on charts button
     */
    private final View.OnClickListener clickCharts = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, ChartsActivity.class);
            startActivity(intent);
        }
    };

    /**
     * Click on charts button
     */
    private final View.OnClickListener clickList = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, BeachListActivity.class);
            startActivity(intent);
        }
    };

}
