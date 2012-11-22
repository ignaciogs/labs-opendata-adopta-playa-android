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

package com.fortysevendeg.android.adoptaunaplaya.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.fortysevendeg.android.adoptaunaplaya.R;
import com.fortysevendeg.android.adoptaunaplaya.api.response.BeachResponse;

import java.util.List;

public class BeachListAdapter extends BaseAdapter {

    private List<BeachResponse> data;
    private Context context;

    public BeachListAdapter(List<BeachResponse> data, Context context) {
        this.data = data;
        this.context = context;
    }

    public void addData(List<BeachResponse> newData) {
        for (BeachResponse beachResponse : newData) {
            data.add(beachResponse);
        }
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public BeachResponse getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    private static class BaseHolder {
        private TextView tvBeachName;
        private TextView tvBeachAddress;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        BeachResponse item = data.get(position);

        BaseHolder h;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.beach_row, null);
            h = new BaseHolder();

            h.tvBeachName = (TextView) convertView.findViewById(R.id.beach_row_tv_beach_name);
            h.tvBeachAddress = (TextView) convertView.findViewById(R.id.beach_row_tv_beach_address);

            convertView.setTag(h);
        } else {
            h = (BaseHolder) convertView.getTag();
        }

        h.tvBeachName.setText(item.getNombre());
        if (item.getMunicipio().equals(item.getProvincia())) {
            h.tvBeachAddress.setText(String.format("%s - %s", item.getMunicipio(), item.getComunidad()));
        } else {
            h.tvBeachAddress.setText(String.format("%s - %s - %s", item.getMunicipio(), item.getProvincia(), item.getComunidad()));
        }


        return convertView;
    }
}
