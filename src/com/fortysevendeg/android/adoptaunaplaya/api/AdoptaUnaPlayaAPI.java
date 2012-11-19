package com.fortysevendeg.android.adoptaunaplaya.api;

import android.content.Context;
import com.fortysevendeg.android.adoptaunaplaya.api.response.BeachListResponse;
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

}
