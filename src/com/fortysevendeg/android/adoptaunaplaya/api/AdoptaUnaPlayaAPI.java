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

package com.fortysevendeg.android.adoptaunaplaya.api;

import android.content.Context;
import com.fortysevendeg.android.adoptaunaplaya.api.response.BeachListResponse;
import com.fortysevendeg.android.adoptaunaplaya.api.response.TotalBeachListResponse;
import it.restrung.rest.client.APIDelegate;

public interface AdoptaUnaPlayaAPI {

    /**
     * Initializes this service, required before the api is used
     *
     * @param context
     */
    void init(Context context);

    /**
     * Sets the API service endpoint
     *
     * @param endpoint ex. https://api.ride.com/api/v1/ride
     */
    void setEndpoint(String endpoint);

    /**
     * Get the API service endpoint
     *
     * @return API endpoint
     */
    String getEndpoint();

    /**
     * Get beachs list
     *
     * @param firstRecord First record pagination
     * @param lastRecord  Last record pagination
     * @param delegate    listener
     */
    void getBeachList(int firstRecord, int lastRecord, APIDelegate<BeachListResponse> delegate);

    /**
     * Return Top ten users
     *
     * @param delegate listener
     */
    void getTopTenUsers(APIDelegate<TotalBeachListResponse> delegate);

    /**
     * Get beaches by location and radius
     *
     * @param radiusKm  Radius in kms
     * @param latitude  Latitude
     * @param longitude Longitude
     * @param delegate  listener
     */
    void getBeachesByLocation(int radiusKm, double latitude, double longitude, APIDelegate<BeachListResponse> delegate);

    /**
     * Return the bechs with more enterococos
     * @param delegate listener
     */
    void getBeachesWithMoreEnterococos(APIDelegate<BeachListResponse> delegate);

}
