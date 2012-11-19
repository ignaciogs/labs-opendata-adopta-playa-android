package com.fortysevendeg.android.adoptaunaplaya.api.impl;

import android.content.Context;
import com.fortysevendeg.android.adoptaunaplaya.R;
import com.fortysevendeg.android.adoptaunaplaya.api.AdoptaUnaPlayaAPI;
import com.fortysevendeg.android.adoptaunaplaya.api.response.BeachListResponse;
import it.restrung.rest.client.APIDelegate;
import it.restrung.rest.client.RestClient;
import it.restrung.rest.client.RestClientFactory;

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
        client.getAsync(delegate, getEndpoint("/list/%s/%s"), firstRecord, lastRecord);
    }
}
