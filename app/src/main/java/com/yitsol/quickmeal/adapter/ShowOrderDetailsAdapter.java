package com.yitsol.quickmeal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yitsol.quickmeal.R;
import com.yitsol.quickmeal.domain.CustomPackageCategoryDO;
import com.yitsol.quickmeal.listner.CustomCheckedItemListner;

import java.util.ArrayList;

public class ShowOrderDetailsAdapter extends BaseAdapter {

    ArrayList<CustomPackageCategoryDO> arrCustomItemList;
    ViewHolder viewHolder;
    LayoutInflater inflater;
    CustomCheckedItemListner customCheckedItemListner;
    CustomPackageCategoryDO customPackageCategoryDO;
    private Context context;

    public ShowOrderDetailsAdapter(Context context, ArrayList<CustomPackageCategoryDO> arrCustomItemList) {
        this.context = context;
        this.arrCustomItemList = arrCustomItemList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        if (arrCustomItemList != null && arrCustomItemList.size() > 0)
            return arrCustomItemList.size();
        else
            return 0;
    }

    @Override
    public Object getItem(int position) {
        return arrCustomItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void refreshAdapter(ArrayList<CustomPackageCategoryDO> arrListOfSubItems) {
        this.arrCustomItemList = arrListOfSubItems;
        notifyDataSetChanged();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        customPackageCategoryDO = arrCustomItemList.get(position);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.row_order_summary_custom, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tv_itemNameCO = (TextView) convertView.findViewById(R.id.tv_itemNameCO);
            viewHolder.tv_itemPriceCO = (TextView) convertView.findViewById(R.id.tv_itemPriceCO);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tv_itemNameCO.setText(customPackageCategoryDO.ItemName);
        viewHolder.tv_itemPriceCO.setText(String.valueOf(customPackageCategoryDO.ItemPrice));

        return convertView;


    }

    public void registerListner(CustomCheckedItemListner customCheckedItemListner) {
        this.customCheckedItemListner = customCheckedItemListner;
    }

    class ViewHolder {
        TextView tv_itemNameCO;
        TextView tv_itemPriceCO;
    }

}
