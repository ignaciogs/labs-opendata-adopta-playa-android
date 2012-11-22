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

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.fortysevendeg.android.adoptaunaplaya.R;
import com.fortysevendeg.android.adoptaunaplaya.api.APIService;

public class SplashScreenActivity extends BaseActivity {

    private static final int SPLASH_FINISH = 0;

    private final Handler splashHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SPLASH_FINISH:
                    nextScreen();
                    finish();
                    break;
            }
            super.handleMessage(msg);
        }
    };

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen_activity);

        APIService.get().init(this);

        Message msg = new Message();
        msg.what = SPLASH_FINISH;
        splashHandler.sendMessageDelayed(msg, getResources().getInteger(R.integer.splash_time));
    }

    private void nextScreen() {
            Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
            startActivity(intent);
    }
}
