package com.yitsol.quickmeal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yitsol.quickmeal.R;
import com.yitsol.quickmeal.domain.AreaDO;

import java.util.ArrayList;


public class AreaAdapter extends BaseAdapter {

    ViewHolder viewHolder;
    LayoutInflater inflater;
    AreaDO areaDO;
    private Context context;
    private ArrayList<AreaDO> arrAreaDO;


    public AreaAdapter(Context context, ArrayList<AreaDO> arrAreaDO) {
        this.context = context;
        this.arrAreaDO = arrAreaDO;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        if (arrAreaDO != null && arrAreaDO.size() > 0)
            return arrAreaDO.size();
        else
            return 0;
    }

    @Override
    public Object getItem(int position) {
        return arrAreaDO.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void refreshAdapter(ArrayList<AreaDO> arrCityDO) {
        this.arrAreaDO = arrCityDO;
        notifyDataSetChanged();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        areaDO = arrAreaDO.get(position);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.row_cities, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.city_name = (TextView) convertView.findViewById(R.id.city_name);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.city_name.setText(areaDO.areaName);
        return convertView;
    }

    class ViewHolder {
        TextView city_name;
    }

}
