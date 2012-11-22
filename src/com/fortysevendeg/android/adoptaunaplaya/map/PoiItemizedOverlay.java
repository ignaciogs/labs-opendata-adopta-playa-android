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

package com.fortysevendeg.android.adoptaunaplaya.map;

import android.graphics.drawable.Drawable;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;

import java.util.ArrayList;
import java.util.List;

public class PoiItemizedOverlay extends BalloonItemizedOverlay<OverlayItem> {

    private double latitude;
    private double longitude;
    private int indexData;
    private OnTapListener onTapListener;
    private List<OverlayItem> listOverlays = new ArrayList<OverlayItem>();

    /**
     * When the user click on the balloon is trigger this listener
     *
     * @param onTapListener Listener to trigger
     */
    public void setOnTapListener(OnTapListener onTapListener) {
        this.onTapListener = onTapListener;
    }

    /**
     * Interface to implemented
     */
    public interface OnTapListener {
        public void onTap(double lat, double lng, int index);
    }

    /**
     * Constructor of PoiItemizedOverlay
     *
     * @param defaultMarker drawable for the pushpin
     * @param mapView       MapView Object
     * @param latitude      Pushpin's latitude
     * @param longitude     Pushpin's longitude
     * @param indexData     Object's index inside list
     */
    public PoiItemizedOverlay(Drawable defaultMarker, MapView mapView, double latitude, double longitude, int indexData) {
        super(boundCenter(defaultMarker), mapView);
        this.latitude = latitude;
        this.longitude = longitude;
        this.indexData = indexData;
    }

    /**
     * Add a overlay to list of overlays
     *
     * @param overlay
     */
    public void addOverlay(OverlayItem overlay) {
        listOverlays.add(overlay);
        populate();
    }

    /**
     * Get an overlay item
     *
     * @param i index of item
     * @return An OverlayItem
     */
    @Override
    protected OverlayItem createItem(int i) {
        return listOverlays.get(i);
    }

    /**
     * Get size of list of overlays
     *
     * @return Size of the overlays list
     */
    @Override
    public int size() {
        return listOverlays.size();
    }

    /**
     * Override this method to handle a "tap" on a balloon. By default, does nothing
     * and returns false.
     *
     * @param index - The index of the item whose balloon is tapped.
     * @return true if you handled the tap, otherwise false.
     */
    @Override
    protected boolean onBalloonTap(int index) {
        if (onTapListener != null) {
            onTapListener.onTap(latitude, longitude, indexData);
        }
        return true;
    }

}
