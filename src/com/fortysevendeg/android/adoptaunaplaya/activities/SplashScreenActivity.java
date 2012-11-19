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
