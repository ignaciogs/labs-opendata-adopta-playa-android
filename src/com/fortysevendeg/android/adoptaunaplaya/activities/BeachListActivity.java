package com.fortysevendeg.android.adoptaunaplaya.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.fortysevendeg.android.adoptaunaplaya.R;
import com.fortysevendeg.android.adoptaunaplaya.adapters.BeachListAdapter;
import com.fortysevendeg.android.adoptaunaplaya.api.APIService;
import com.fortysevendeg.android.adoptaunaplaya.api.response.BeachListResponse;
import com.fortysevendeg.android.adoptaunaplaya.api.response.BeachResponse;
import it.restrung.rest.client.ContextAwareAPIDelegate;

import java.util.ArrayList;

import static it.restrung.rest.cache.RequestCache.LoadPolicy.NEVER;
import static it.restrung.rest.cache.RequestCache.StoragePolicy.DISABLED;

public class BeachListActivity extends BaseActivity {

    private ListView lvData;
    private BeachListAdapter beachListAdapter;
    private final static int SIZE_PAGINATION = 50;
    private ProgressBar pbLoading;
    private Button bGetMoreData;

    /**
     * @see BaseActivity#onCreate(android.os.Bundle)
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.beach_list_activity);

        bGetMoreData = (Button) findViewById(R.id.beach_list_b_get_more_data);
        bGetMoreData.setOnClickListener(clickGetmoreData);

        pbLoading = (ProgressBar) findViewById(R.id.beach_list_pb_loading);
        pbLoading.setVisibility(View.GONE);

        lvData = (ListView) findViewById(R.id.beach_list_lv_data);
        beachListAdapter = new BeachListAdapter(new ArrayList<BeachResponse>(), this);
        lvData.setAdapter(beachListAdapter);
        getData(0, SIZE_PAGINATION);
    }

    /**
     * Get Beach List from Backend
     *
     * @param first Pagination first record
     * @param last  Pagination last record
     */
    private void getData(int first, int last) {
        showLoading();
        APIService.get().getBeachList(first, last, new ContextAwareAPIDelegate<BeachListResponse>(this, BeachListResponse.class, NEVER, DISABLED) {
            @Override
            public void onResults(BeachListResponse beachListResponse) {
                hideLoading();
                beachListAdapter.addData(beachListResponse.getResults());
                beachListAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Throwable throwable) {
                hideLoading();
                Toast.makeText(BeachListActivity.this, String.format("%s: %s", getString(R.string.errorLoadingBeachs), throwable.getMessage()), Toast.LENGTH_LONG).show();
            }
        });

    }

    /**
     * Show loading component
     */
    private void showLoading() {
        pbLoading.setVisibility(View.VISIBLE);
        bGetMoreData.setEnabled(false);
    }

    /**
     * Hide loading component
     */
    private void hideLoading() {
        pbLoading.setVisibility(View.GONE);
        bGetMoreData.setEnabled(true);
    }

    /**
     * Click in button get more data
     */
    private final View.OnClickListener clickGetmoreData = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            getData(beachListAdapter.getCount() -1 , SIZE_PAGINATION);
        }
    };

}
