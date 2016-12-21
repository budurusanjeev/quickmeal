package com.yitsol.quickmeal.adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.yitsol.quickmeal.R;
import com.yitsol.quickmeal.domain.ListOfMealPlanDO;
import com.yitsol.quickmeal.domain.OrderNowDO;
import com.yitsol.quickmeal.listner.GetSubItemListner;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


/**
 * Created by Nikita on 12-02-2016.
 */
public class SubscribeCustomOrderAdapter extends BaseAdapter {

    ArrayList<ListOfMealPlanDO> arrListOfMealPlans;
    ViewHolder viewHolder;
    LayoutInflater inflater;
    OrderNowDO orderNowDO;
    ListOfMealPlanDO listOfMealPlanDO;
    int mealtime;
    Dialog pkg_custom_dialog;
    SubscribeCustomOrderAdapter subscribeCustomOrderAdapter;
    ListView lv_addOns;
    Button btn_add_custom;
    String laneDesc;
    int pincodeId;
    String cityName;
    StringBuilder stringBuilder;
    GetSubItemListner getSubItemListner;
    private Context context;
    private ArrayList<OrderNowDO> arrOrderNowDO;


    public SubscribeCustomOrderAdapter(Context context, ArrayList<ListOfMealPlanDO> arrListOfMealPlans) {
        this.context = context;
        this.arrListOfMealPlans = arrListOfMealPlans;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        if (arrListOfMealPlans != null && arrListOfMealPlans.size() > 0)
            return arrListOfMealPlans.size();
        else
            return 0;
    }

    @Override
    public Object getItem(int position) {
        return arrListOfMealPlans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void refreshAdapter(ArrayList<ListOfMealPlanDO> arrListOfMealPlans) {
        this.arrListOfMealPlans = arrListOfMealPlans;
        notifyDataSetChanged();
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        listOfMealPlanDO = arrListOfMealPlans.get(position);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.row_subscribe_custom, null);
            viewHolder = new ViewHolder();
            viewHolder.iv_itemCheck = (ImageView) convertView.findViewById(R.id.iv_itemCheck);
            viewHolder.iv_itemImage = (ImageView) convertView.findViewById(R.id.iv_itemImage);
            viewHolder.tv_itemName = (TextView) convertView.findViewById(R.id.tv_itemName);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_itemName.setText(listOfMealPlanDO.food_ItemName);
        //viewHolder.endDate.setText(mySpiceboxDO.ordEndDt);
        viewHolder.iv_itemCheck.setTag(listOfMealPlanDO);
        viewHolder.iv_itemCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ListOfMealPlanDO listOfMealPlanDO = (ListOfMealPlanDO) v.getTag();

                getSubItemListner.gettherequiresubitemlist(listOfMealPlanDO.food_ItemId);
                // showdialogfororder();

            }
        });


        return convertView;
    }

    public Date stringtodateconverter(String date) {
        String dateStr = "06/27/2007";
        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        Date startDate = null;
        try {
            startDate = formatter.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return startDate;

    }

    public void registerlistner(GetSubItemListner getSubItemListner) {
        this.getSubItemListner = getSubItemListner;
    }

    class ViewHolder {
        ImageView iv_itemCheck;
        ImageView iv_itemImage;
        TextView tv_itemName;

    }

}
