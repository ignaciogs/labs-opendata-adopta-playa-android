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

package com.fortysevendeg.android.adoptaunaplaya.api.impl;

import android.content.Context;
import com.fortysevendeg.android.adoptaunaplaya.R;
import com.fortysevendeg.android.adoptaunaplaya.api.AdoptaUnaPlayaAPI;
import com.fortysevendeg.android.adoptaunaplaya.api.request.LatLngRequest;
import com.fortysevendeg.android.adoptaunaplaya.api.request.QueryRequest;
import com.fortysevendeg.android.adoptaunaplaya.api.response.BeachListResponse;
import com.fortysevendeg.android.adoptaunaplaya.api.response.TotalBeachListResponse;
import it.restrung.rest.client.APIDelegate;
import it.restrung.rest.client.RestClient;
import it.restrung.rest.client.RestClientFactory;

import java.util.ArrayList;
import java.util.List;

public class AdoptaUnaPlayaAPIImpl implements AdoptaUnaPlayaAPI {

    private RestClient client = RestClientFactory.getClient();

    /**
     * API Endpoint
     */
    private String endpoint;

    @Override
    public void init(Context context) {
        setEndpoint(context.getString(R.string.end_point));
    }

    @Override
    public synchronized void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    @Override
    public String getEndpoint() {
        return this.endpoint;
    }

    private String getEndpoint(String path) {
        return endpoint + path;
    }

    @Override
    public void getBeachList(int firstRecord, int lastRecord, APIDelegate<BeachListResponse> delegate) {
        QueryRequest queryRequest = new QueryRequest();
        queryRequest.setOffset(firstRecord);
        queryRequest.setLimit(lastRecord);
        queryRequest.setQuery("select p from Playa p");

        List<String> prop = new ArrayList<String>();
        prop.add("hack");

        queryRequest.setProperties(prop);
        client.postAsync(delegate, getEndpoint("/query"), queryRequest);
    }

    @Override
    public void getTopTenUsers(APIDelegate<TotalBeachListResponse> delegate) {
        QueryRequest queryRequest = new QueryRequest();
        queryRequest.setOffset(0);
        queryRequest.setLimit(10);
        queryRequest.setQuery("select p.adoptadaPor, count(p) from Playa p group by p.adoptadaPor order by count(p) desc");

        List<String> prop = new ArrayList<String>();
        prop.add("user");
        prop.add("totalBeach");

        queryRequest.setProperties(prop);
        client.postAsync(delegate, getEndpoint("/query"), queryRequest);
    }

    @Override
    public void getBeachesByLocation(int radiusKm, double latitude, double longitude, APIDelegate<BeachListResponse> delegate) {
        LatLngRequest latLngRequest = new LatLngRequest();
        latLngRequest.setOffset(0);
        latLngRequest.setLimit(100);
        latLngRequest.setCenterLat(latitude / 1E6);
        latLngRequest.setCenterLng(longitude/1E6);
        latLngRequest.setRadiusKm(radiusKm);
        client.postAsync(delegate, getEndpoint("/query"), latLngRequest);
    }


}
