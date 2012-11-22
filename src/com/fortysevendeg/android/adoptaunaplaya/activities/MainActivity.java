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

import android.app.AlertDialog;
import android.content.DialogInterface;
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

        ImageButton bMap = (ImageButton)findViewById(R.id.main_b_map);
        bMap.setOnClickListener(clickMap);

    }

    /**
     * Click on charts button
     */
    private final View.OnClickListener clickCharts = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
            dialog.setTitle(getString(R.string.kindCharts));
            dialog.setItems(new CharSequence[]{getString(R.string.topTenUsers)},
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int itemIndex) {
                                    switch (itemIndex) {
                                        case 0: //Top ten users
                                            Intent intent = new Intent(MainActivity.this, ChartsActivity.class);
                                            intent.putExtra(ChartsActivity.KEY_KIND_CHARTS, ChartsActivity.KindCharts.TOP_TEN_USERS);
                                            startActivity(intent);
                                            break;
                                    }
                                }
                            }
            );
            dialog.show();
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

    /**
     * Click on Map button
     */
    private final View.OnClickListener clickMap = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, MapBrowseActivity.class);
            startActivity(intent);
        }
    };

}
