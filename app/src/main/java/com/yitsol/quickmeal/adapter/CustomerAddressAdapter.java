package com.yitsol.quickmeal.adapter;


import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yitsol.quickmeal.R;
import com.yitsol.quickmeal.domain.AddressDO;
import com.yitsol.quickmeal.utilities.FontType;

import java.util.ArrayList;


public class CustomerAddressAdapter extends BaseAdapter {

    AddressDO model;
    ViewHolder viewHolder;
    LayoutInflater inflater;
    FontType fonttype;
    int qty;
    Dialog dialog;
    private Context context;
    private ArrayList<AddressDO> arrAddressDO;


    public CustomerAddressAdapter(Context context, ArrayList<AddressDO> arrAddressDO) {
        this.context = context;
        this.arrAddressDO = arrAddressDO;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {

        return arrAddressDO.size();

    }

    @Override
    public Object getItem(int position) {
        return arrAddressDO.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.row_address, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.txt_addressLabel = (TextView) convertView.findViewById(R.id.txt_addressLabel);
            viewHolder.txt_address = (TextView) convertView.findViewById(R.id.txt_address);
            viewHolder.img_edit = (ImageView) convertView.findViewById(R.id.img_edit);
            viewHolder.ll_rowAddress = (LinearLayout) convertView.findViewById(R.id.ll_rowAddress);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ViewGroup root = (ViewGroup) convertView.findViewById(R.id.ll_rowAddress);
        fonttype = new FontType(context, root);

        model = (AddressDO) getItem(position);

        viewHolder.txt_addressLabel.setText(model.addressType);

        StringBuilder strAddress = new StringBuilder();
        strAddress.append(model.houseNo);
        strAddress.append(",");
        strAddress.append(model.address);
        strAddress.append(",");
        strAddress.append(model.city);
        strAddress.append(",");
        strAddress.append(model.zipcode);
        strAddress.append(",");
        strAddress.append(model.landmark);

        viewHolder.txt_address.setText(strAddress);


        return convertView;
    }


    private class ViewHolder {
        TextView txt_address, txt_addressLabel;
        ImageView img_edit;
        LinearLayout ll_rowAddress;
    }
}