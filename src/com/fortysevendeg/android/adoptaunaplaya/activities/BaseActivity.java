/*
 * Copyright (C) 2012 QThru.com
 * http://www.qthru.com/
 * All rights reserved
 * Developed for QThru.com by:
 *
 * 47 Degrees, LLC
 * http://47deg.com
 * hello@47deg.com
 */

package com.fortysevendeg.android.adoptaunaplaya.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;

public class BaseActivity extends FragmentActivity {

    /**
     * @see Activity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
    }

}
