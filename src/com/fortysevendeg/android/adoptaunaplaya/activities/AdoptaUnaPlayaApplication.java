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

import android.app.Application;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import com.fortysevendeg.android.adoptaunaplaya.api.APIService;

public class AdoptaUnaPlayaApplication extends Application {

    private Context context;
    private LocationManager locationManager;

    /**
     * Current user location
     */
    private Location currentLocationUser;

    /**
     * @see android.app.Application#onCreate()
     */
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        APIService.get().init(this);
        if (locationManager == null) {
            // TODO When phone not have GPS and card, launch force close
            try {
                locationManager = (LocationManager) this.getSystemService(getApplicationContext().LOCATION_SERVICE);
                Criteria locationCriteria = new Criteria();
                locationManager.requestLocationUpdates(locationManager.getBestProvider(locationCriteria, true), 0, 20, locationListener);
            } catch (IllegalArgumentException e) {
            }
        }
    }

    /**
     * @see android.location.LocationListener
     */
    LocationListener locationListener = new LocationListener() {
        public void onLocationChanged(Location location) {
            currentLocationUser = location;
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {
        }

        public void onProviderEnabled(String provider) {
        }

        public void onProviderDisabled(String provider) {
        }
    };

    public Location getCurrentLocationUser() {
        return currentLocationUser;
    }

}
