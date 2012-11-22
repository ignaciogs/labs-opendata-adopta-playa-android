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

import android.graphics.Canvas;
import android.view.MotionEvent;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

public class OnMoveOverlay extends Overlay
{

    private static GeoPoint lastLatLon = new GeoPoint(0, 0);
    private static GeoPoint currLatLon;

    // Event listener to listen for map finished moving events
    private OnMapMoveListener eventListener = null;

    protected boolean isMapMoving = false;

    public OnMoveOverlay(OnMapMoveListener eventLis){
        //Set event listener
        eventListener = eventLis;
    }

    @Override
    public boolean onTouchEvent(android.view.MotionEvent ev, MapView mapView)
    {
        super.onTouchEvent(ev, mapView);
        if (ev.getAction() == MotionEvent.ACTION_UP)
        {
            // Added to example to make more complete
            isMapMoving = true;
        }
        //Fix: changed to false as it would handle the touch event and not pass back.
        return false;
    }

    @Override
    public void draw(Canvas canvas, MapView mapView, boolean shadow)
    {
        if (!shadow)
        {
            if (isMapMoving)
            {
                currLatLon = mapView.getProjection().fromPixels(0, 0);
                if (currLatLon.equals(lastLatLon))
                {
                    isMapMoving = false;
                    eventListener.mapMovingFinishedEvent();
                }
                else
                {
                    lastLatLon = currLatLon;
                }
            }
        }
    }

    public interface OnMapMoveListener{
        public void mapMovingFinishedEvent();
    }
}
