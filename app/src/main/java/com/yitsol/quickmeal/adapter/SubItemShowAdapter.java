package com.yitsol.quickmeal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.yitsol.quickmeal.R;
import com.yitsol.quickmeal.domain.ListOfMealPlanDO;
import com.yitsol.quickmeal.listner.CustomCheckedItemListner;

import java.util.ArrayList;

public class SubItemShowAdapter extends BaseAdapter {

    ViewHolder viewHolder;
    LayoutInflater inflater;
    ListOfMealPlanDO listOfMealPlanDO;
    CustomCheckedItemListner customCheckedItemListner;
    private Context context;
    private ArrayList<ListOfMealPlanDO> arrListOfSubItems;

    public SubItemShowAdapter(Context context, ArrayList<ListOfMealPlanDO> arrListOfSubItems) {
        this.context = context;
        this.arrListOfSubItems = arrListOfSubItems;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        if (arrListOfSubItems != null && arrListOfSubItems.size() > 0)
            return arrListOfSubItems.size();
        else
            return 0;
    }

    @Override
    public Object getItem(int position) {
        return arrListOfSubItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void refreshAdapter(ArrayList<ListOfMealPlanDO> arrListOfSubItems) {
        this.arrListOfSubItems = arrListOfSubItems;
        notifyDataSetChanged();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        listOfMealPlanDO = arrListOfSubItems.get(position);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.row_sub_item_list, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tv_addOn_itemName = (TextView) convertView.findViewById(R.id.tv_addOn_itemName);
            viewHolder.tv_addOn_itemPrice = (TextView) convertView.findViewById(R.id.tv_addOn_itemPrice);
            viewHolder.cb_itemCheckStatus = (CheckBox) convertView.findViewById(R.id.cb_itemCheckStatus);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tv_addOn_itemName.setText(listOfMealPlanDO.sbItemName);
        viewHolder.cb_itemCheckStatus.setChecked(listOfMealPlanDO.sbItemSelectStatus);
        viewHolder.tv_addOn_itemPrice.setText(String.valueOf(listOfMealPlanDO.sbItemPrice));

        viewHolder.cb_itemCheckStatus.setTag(listOfMealPlanDO);

        viewHolder.cb_itemCheckStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ListOfMealPlanDO listOfMealPlanDO = (ListOfMealPlanDO) v.getTag();
                if (listOfMealPlanDO.sbItemSelectStatus) {
                    listOfMealPlanDO.sbItemSelectStatus = false;
                    customCheckedItemListner.removeCustomItemDetails(listOfMealPlanDO.sbItemName, listOfMealPlanDO.sbItemPrice, listOfMealPlanDO.sbItemCategoryId);
                } else {
                    listOfMealPlanDO.sbItemSelectStatus = true;
                    customCheckedItemListner.addCustomItemDetails(listOfMealPlanDO.sbItemName, listOfMealPlanDO.sbItemPrice, listOfMealPlanDO.sbItemCategoryId);
                }

                notifyDataSetChanged();
            }
        });


        return convertView;


    }

    public void registerListner(CustomCheckedItemListner customCheckedItemListner) {
        this.customCheckedItemListner = customCheckedItemListner;
    }

    class ViewHolder {
        TextView tv_addOn_itemName;
        TextView tv_addOn_itemPrice;
        CheckBox cb_itemCheckStatus;
    }

}
