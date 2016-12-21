package com.yitsol.quickmeal.activity;

import android.os.Bundle;

import com.yitsol.quickmeal.R;

/**
 * Created by samba on 5/18/2016.
 */
public class OrderCompleteActivity extends BaseNew

{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_complete);
        mContext = OrderCompleteActivity.this;
    }
}
