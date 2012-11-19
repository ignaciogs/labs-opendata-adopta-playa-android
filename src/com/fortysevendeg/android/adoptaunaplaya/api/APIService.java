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

package com.fortysevendeg.android.adoptaunaplaya.api;


import com.fortysevendeg.android.adoptaunaplaya.api.impl.AdoptaUnaPlayaAPIImpl;

public class APIService {
    private static AdoptaUnaPlayaAPI instance = null;

    /**
     *
     * @return the singleton instance to access the backend API
     */
    public static AdoptaUnaPlayaAPI get() {
        if (instance == null) {
            instance = new AdoptaUnaPlayaAPIImpl();
        }
        return instance;
    }
}
