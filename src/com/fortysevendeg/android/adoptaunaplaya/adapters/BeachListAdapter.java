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
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        BaseHolder h;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.beach_row, null);
            h = new BaseHolder();

            h.tvBeachName = (TextView) convertView.findViewById(R.id.beach_row_tv_beach_name);

            convertView.setTag(h);
        } else {
            h = (BaseHolder) convertView.getTag();
        }

        h.tvBeachName.setText(data.get(position).getNombre());


        return convertView;
    }
}
