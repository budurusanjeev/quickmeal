package com.yitsol.quickmeal.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.flyco.pageindicator.indicator.FlycoPageIndicaor;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.yitsol.quickmeal.R;
import com.yitsol.quickmeal.adapter.AreaAdapter;
import com.yitsol.quickmeal.adapter.RestaurantAdapter;
import com.yitsol.quickmeal.businessLayer.CommonBL;
import com.yitsol.quickmeal.businessLayer.DataListener;
import com.yitsol.quickmeal.domain.AreaDO;
import com.yitsol.quickmeal.domain.PrefferenceDomain;
import com.yitsol.quickmeal.domain.RestaurantDomain;
import com.yitsol.quickmeal.domain.TaxDomain;
import com.yitsol.quickmeal.listner.PrefferenceListener;
import com.yitsol.quickmeal.utilities.CartDatabase;
import com.yitsol.quickmeal.utilities.PreferenceUtils;
import com.yitsol.quickmeal.views.SimpleLayoutBanner;
import com.yitsol.quickmeal.webaccess.Response;
import com.yitsol.quickmeal.webaccess.ServiceMethods;
import com.yitsol.quickmeal.webaccess.ServiceURLs;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class Dashboard extends BaseActivity implements DataListener, PrefferenceListener {


    ArrayList<RestaurantDomain> restaurantDomains;
    Context context;
    SimpleLayoutBanner banner;
    GridView gv_restaurants;
    Spinner spin_area;
    String areaName, areaId;
    ImageView iv_search;
    LinearLayout ll_restaurents, ll_mealplans, ll_supriseme, ll_parent;
    ArrayList<RestaurantDomain> arrRestaurantList;
    ArrayList<AreaDO> arrAreaList;
    AreaDO areaDO;
    FlycoPageIndicaor indicator_circle;
    RestaurantAdapter restaurantAdapter;
    AreaAdapter areaAdapter;
    private View decorView;
    private boolean doubleBackToExitPressedOnce = false;
    private PreferenceUtils preferenceUtils;
    private Dialog preffDialog;
    private List<String> prefftittles = new ArrayList<>();
    private int position = 0;
    private String allergiesText;
    private TaxDomain taxDomain;
    private String actionname;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {

            finish();

            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }


    @Override
    public void goto_mealPlan_method() {

    }

    private void setAdaptertoGridView() {
        restaurantAdapter = new RestaurantAdapter(context, restaurantDomains);
        gv_restaurants.setAdapter(restaurantAdapter);

    }


    @Override
    public void initialize() {


        context = Dashboard.this;

        preferenceUtils = new PreferenceUtils(context);

        //getTaxInformation();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .build();
        ImageLoader.getInstance().init(config);
        llContent.addView(inflater.inflate(R.layout.activity_dashboard, null), new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        decorView = getWindow().getDecorView();


        initViewControll();

        if (new CommonBL(context, Dashboard.this).getRestaurentDetails())

        {
            showLoaderNew();

        } else {
            Toast.makeText(Dashboard.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }


        if (new CommonBL(context, Dashboard.this).getArea())

        {
            showLoaderNew();

        } else {
            Toast.makeText(Dashboard.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }

        spin_area.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                AreaDO model = (AreaDO) parent.getItemAtPosition(position);
                areaName = model.areaName;
                areaId = model.areaId;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        iv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getRestaurentByAreaId(areaId);
            }
        });


    }

    public void getRestaurentByAreaId(String id) {

        StringBuilder sb = new StringBuilder();

        String url = ServiceURLs.getRequestedURL(ServiceMethods.WS_GET_REST_BY_AREA_ID);

        sb.append(url);

        JSONObject inputjobj = new JSONObject();

        JSONObject wherejobj = new JSONObject();

        try {

            wherejobj.put("areaId", areaId);
            inputjobj.put("where", wherejobj);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        sb.append(inputjobj.toString());

        String URLTOPOST = sb.toString();

        if (new CommonBL(context, Dashboard.this).getRestaurentsByAreaId(URLTOPOST)) {

            showLoaderNew();
        } else {
            hideloader();
            // showAlertDialog("NO INTERNET","OK");
        }
    }

    @Override
    public void goto_myprofile_method() {

        Intent intent = new Intent(context, MyProfileActivity.class);
        startActivity(intent);

    }

    @Override
    public void goto_contactus_method() {
        Intent intent = new Intent(context, ContactUsActivity.class);
        startActivity(intent);
    }

    @Override
    public void goto_login_method() {
        Intent intent = new Intent(context, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void goto_logout_method() {
        preferenceUtils.logoutUser();
        CartDatabase.clearAddresses();
        CartDatabase.clearDatabase();
        Intent intent = new Intent(context, LoginActivity.class);
        startActivity(intent);

    }

    @Override
    public void goto_myOrders_method() {

    }


    private void initViewControll() {

        ll_parent = (LinearLayout) findViewById(R.id.ll_parent);
        banner = (SimpleLayoutBanner) findViewById(R.id.banner);
        gv_restaurants = (GridView) findViewById(R.id.gv_restaurants);
        spin_area = (Spinner) findViewById(R.id.spin_area);
        iv_search = (ImageView) findViewById(R.id.iv_search);
        indicator_circle = (FlycoPageIndicaor) findViewById(R.id.indicator_circle);
        indicator_circle.bringToFront();


    }

    @Override
    @SuppressWarnings("unchecked")
    public void dataRetreived(Response data) {

        hideloader();
        if (data != null && data.method == ServiceMethods.WS_GET_RESTAURANTS) {
            if (!data.isError) {
                arrRestaurantList = (ArrayList<RestaurantDomain>) data.data;
                restaurantDomains = arrRestaurantList;
                banner.setSource(arrRestaurantList).startScroll();
                indicator_circle.setViewPager(banner.getViewPager(), arrRestaurantList.size());
                setAdaptertoGridView();

            }
        } else if (data != null && data.method == ServiceMethods.WS_GET_AREA) {
            if (!data.isError) {
                arrAreaList = (ArrayList<AreaDO>) data.data;
                for (int i = 0; i < arrAreaList.size(); i++) {
                    areaDO = arrAreaList.get(i);
                    areaDO.areaName = arrAreaList.get(i).areaName;
                    areaDO.areaId = arrAreaList.get(i).areaId;
                }

                areaAdapter = new AreaAdapter(context, arrAreaList);
                spin_area.setAdapter(areaAdapter);

            }
        } else if (data != null && data.method == ServiceMethods.WS_GET_REST_BY_AREA_ID) {
            if (!data.isError) {
                arrRestaurantList = (ArrayList<RestaurantDomain>) data.data;
                restaurantDomains = arrRestaurantList;
               /* banner.setSource(arrRestaurantList).startScroll();
                indicator_circle.setViewPager(banner.getViewPager(),arrRestaurantList.size());*/
                setAdaptertoGridView();

            }
        }
    }
/* if (arrRestaurantList != null && arrRestaurantList.size() > 0)
                {
                    for(int i=0; i<arrRestaurantList.size();i++) {
                        restaurantDomain = arrRestaurantList.get(i);
                        restaurantDomain.restaurantName = arrRestaurantList.get(i).restaurantName;
                        restaurantDomain.restaurantId = arrRestaurantList.get(i).restaurantId;
                        restaurantDomain.restaurantMoto = arrRestaurantList.get(i).restaurantMoto;
                        restaurantDomain.restaurantImageUrl = arrRestaurantList.get(i).restaurantImageUrl;
                        arrRestaurantList.add(restaurantDomain);

                    }

                }*/


    private void setDataToAdapter(ListView lv_prefferences, TextView tv_tittle, int position, EditText edt_allergies, Button btn_next, Button btn_previous) {

        tv_tittle.setText(prefftittles.get(position));
        if (position == 0) {
            btn_previous.setBackgroundColor(ContextCompat.getColor(context, R.color.lightGray));
        } else {
            btn_previous.setBackgroundColor(ContextCompat.getColor(context, R.color.green));

        }
    }

    @Override
    public void prefferenceremove(PrefferenceDomain prefferenceDomain) {

    }

    @Override
    public void prefferenceadded(PrefferenceDomain prefferenceDomain) {

    }
}
