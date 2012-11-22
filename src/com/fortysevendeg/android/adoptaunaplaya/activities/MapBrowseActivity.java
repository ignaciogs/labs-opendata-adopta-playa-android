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


import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.fortysevendeg.android.adoptaunaplaya.R;
import com.fortysevendeg.android.adoptaunaplaya.api.APIService;
import com.fortysevendeg.android.adoptaunaplaya.api.response.BeachListResponse;
import com.fortysevendeg.android.adoptaunaplaya.api.response.BeachResponse;
import com.fortysevendeg.android.adoptaunaplaya.map.OnMoveOverlay;
import com.fortysevendeg.android.adoptaunaplaya.map.PoiItemizedOverlay;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;
import it.restrung.rest.client.ContextAwareAPIDelegate;

import java.util.ArrayList;
import java.util.List;

import static it.restrung.rest.cache.RequestCache.LoadPolicy.NEVER;
import static it.restrung.rest.cache.RequestCache.StoragePolicy.DISABLED;

public class MapBrowseActivity extends MapActivity {

    private MapView mapView;
    private GeoPoint lastPointToConsult, consultingPoint;
    ProgressBar pbLoading;
    boolean isConsultingStore = false;
    private List<BeachResponse> data = new ArrayList<BeachResponse>();

    /**
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.map_browse_activity);
        pbLoading = (ProgressBar) findViewById(R.id.map_browse_pb_loading);
        pbLoading.setVisibility(View.GONE);
        initMap();
    }

    /**
     * Init object map and draw on the map the beaches
     */
    private void initMap() {
        if (mapView == null) {
            LinearLayout llMapView = (LinearLayout) findViewById(R.id.map_browse_ll_map_container);
            mapView = new MapView(this, getString(R.string.google_maps_api));
            mapView.setBuiltInZoomControls(false);
            mapView.getController().setZoom(6);
            mapView.setClickable(true);
            llMapView.addView(mapView);

            AdoptaUnaPlayaApplication application = (AdoptaUnaPlayaApplication)getApplication();
            Location userLocation = application.getCurrentLocationUser();
            if (userLocation != null) {
                mapView.getController().animateTo(new GeoPoint((int)(userLocation.getLatitude() * 1E6), (int)(userLocation.getLongitude() * 1E6)));
                mapView.getController().setZoom(12);
            }

            OnMoveOverlay.OnMapMoveListener mapListener = new OnMoveOverlay.OnMapMoveListener() {
                public void mapMovingFinishedEvent() {
                    lastPointToConsult = mapView.getMapCenter();
                    loadBeachesByLocation(false);
                }
            };
            OnMoveOverlay mOnMoveOverlay = new OnMoveOverlay(mapListener);
            mapView.getOverlays().add(mOnMoveOverlay);

            loadBeachesByLocation(true);
        }
    }

    /**
     * Load beaches on the map
     * @param useUserLocation Use user location
     */
    private void loadBeachesByLocation(boolean useUserLocation) {
        if (!isConsultingStore) {
            pbLoading.setVisibility(View.VISIBLE);
            isConsultingStore = true;
            AdoptaUnaPlayaApplication application = (AdoptaUnaPlayaApplication)getApplication();
            Location userLocation = application.getCurrentLocationUser();
            if (useUserLocation && userLocation != null) {
                consultingPoint = new GeoPoint((int)(userLocation.getLatitude() * 1E6), (int)(userLocation.getLongitude() * 1E6));
            } else {
                consultingPoint = new GeoPoint(mapView.getMapCenter().getLatitudeE6(), mapView.getMapCenter().getLongitudeE6());
            }
            APIService.get().getBeachesByLocation((int)Math.round(getRadioMap()), consultingPoint.getLatitudeE6(), consultingPoint.getLongitudeE6(), new ContextAwareAPIDelegate<BeachListResponse>(this, BeachListResponse.class, NEVER, DISABLED) {
                @Override
                public void onResults(BeachListResponse beachListResponse) {
                    for (BeachResponse beachResponse : beachListResponse.getResults()) {
                        if (!data.contains(beachResponse)) {
                            data.add(beachResponse);
                            addBeachToMap(beachResponse, data.size() - 1);
                        }
                    }

                    mapView.invalidate(); //Repaint mapView

                    pbLoading.setVisibility(View.GONE);
                    isConsultingStore = false;
                    if (lastPointToConsult!= null && (consultingPoint.getLatitudeE6() != lastPointToConsult.getLatitudeE6() || consultingPoint.getLongitudeE6() != lastPointToConsult.getLongitudeE6()) ) {
                        loadBeachesByLocation(false);
                    }
                }

                @Override
                public void onError(Throwable e) {
                    pbLoading.setVisibility(View.GONE);
                    isConsultingStore = false;
                    Toast.makeText(MapBrowseActivity.this, String.format("Error: %s", e.getMessage()), Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    /**
     * Add a Beach to MapView
     *
     * @param beachResponse beach to add
     */
    private void addBeachToMap(BeachResponse beachResponse, int index) {
        GeoPoint geoPointStore = new GeoPoint((int) (beachResponse.getLatitude() * 1E6), (int) (beachResponse.getLongitude() * 1E6));
        OverlayItem overlayItem = new OverlayItem(geoPointStore, beachResponse.getNombre(), String.format("%s-%s", beachResponse.getMunicipio(), beachResponse.getProvincia()));
        Drawable drawablePushpin;
        drawablePushpin = getResources().getDrawable(R.drawable.pushpin_map);
        PoiItemizedOverlay poiItemizedOverlay = new PoiItemizedOverlay(drawablePushpin, mapView, beachResponse.getLatitude(), beachResponse.getLongitude(), index);
        poiItemizedOverlay.setOnTapListener(new PoiItemizedOverlay.OnTapListener() {
            @Override
            public void onTap(double lat, double lng, int index) {
                //nothing
            }
        });
        poiItemizedOverlay.addOverlay(overlayItem);
        mapView.getOverlays().add(poiItemizedOverlay);
    }

    /**
     * Get the map display radio
     * @return radio in miles
     */
    public double getRadioMap() {
        double radio = 100;
        Location currentLoc = geopoint2Location(mapView.getMapCenter());
        if (currentLoc!=null) {

            Point p = mapView.getProjection().toPixels(location2Geopoint(currentLoc), null);
            GeoPoint maxDistance = null;

            if (p.x>p.y) {
                maxDistance = mapView.getProjection().fromPixels(0, p.y);
            } else {
                maxDistance = mapView.getProjection().fromPixels(p.x, 0);
            }

            Location maxLocation = geopoint2Location(maxDistance);
            radio = currentLoc.distanceTo(maxLocation)/1000;
        }
        return radio;
    }

    private Location geopoint2Location(GeoPoint geo) {
        Location loc = new Location(LocationManager.GPS_PROVIDER);
        loc.setLatitude((double)geo.getLatitudeE6()/1E6);
        loc.setLongitude((double)geo.getLongitudeE6()/1E6);
        return loc;
    }

    private GeoPoint location2Geopoint(Location loc) {
        return new GeoPoint( (int)(loc.getLatitude()*1E6), (int)(loc.getLongitude()*1E6) );
    }

    @Override
    protected boolean isRouteDisplayed() {
        return false;
    }
}
