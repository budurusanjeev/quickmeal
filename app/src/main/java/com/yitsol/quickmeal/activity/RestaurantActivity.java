package com.yitsol.quickmeal.activity;

import android.content.Context;
import android.os.Bundle;
import android.widget.GridView;

import com.yitsol.quickmeal.R;
import com.yitsol.quickmeal.adapter.RestaurantAdapter;
import com.yitsol.quickmeal.businessLayer.CommonBL;
import com.yitsol.quickmeal.businessLayer.DataListener;
import com.yitsol.quickmeal.domain.RestaurantDomain;
import com.yitsol.quickmeal.utilities.PreferenceUtils;
import com.yitsol.quickmeal.webaccess.Response;

import java.util.ArrayList;

/**
 * Created by Nikita on 7/15/2016.
 */
public class RestaurantActivity extends BaseNew implements DataListener {

    Context context;
    RestaurantAdapter restaurantAdapter;
    GridView gv_restaurents;
    PreferenceUtils preferenceUtils;
    private ArrayList<RestaurantDomain> restaurantDomains;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_summary_oneday);
        mContext = RestaurantActivity.this;
        preferenceUtils = new PreferenceUtils(mContext);
        initViewControl();

        if (new CommonBL(mContext, RestaurantActivity.this).getRestaurentDetails()) {
            showLoaderNew();
        }

    }

    private void initViewControl() {

        gv_restaurents = (GridView) findViewById(R.id.gv_restaurants);
    }

    @Override
    public void dataRetreived(Response data) {

    }
}
