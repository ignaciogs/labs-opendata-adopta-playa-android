package com.fortysevendeg.android.adoptaunaplaya.api.response;

import it.restrung.rest.marshalling.response.AbstractJSONResponse;

import java.io.Serializable;
import java.util.List;

/**
 * BeachListResponse class
 */
public class BeachListResponse extends AbstractJSONResponse implements Serializable {

    private List<BeachResponse> results;

    public List<BeachResponse> getResults() {
        return results;
    }

    public void setResults(List<BeachResponse> results) {
        this.results = results;
    }
}
