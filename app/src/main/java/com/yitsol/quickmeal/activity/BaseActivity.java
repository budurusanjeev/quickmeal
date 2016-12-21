package com.yitsol.quickmeal.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.yitsol.quickmeal.R;
import com.yitsol.quickmeal.adapter.NavDrawerListAdapter;
import com.yitsol.quickmeal.domain.NavDrawerItem;
import com.yitsol.quickmeal.utilities.AppConstants;
import com.yitsol.quickmeal.utilities.PreferenceUtils;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class BaseActivity extends AppCompatActivity {

    public TextView txt_usrname, txt_usrcontact;

    public LinearLayout llContent, ll_userDetail;

    public LayoutInflater inflater;

    public Context basecontext;

    public Toolbar mtoolbar;

    public String[] nav_item_titlenames;

    public Bundle savedInstanceState;

    public ListView mdrawerlistview;

    public ImageView iv_filter;

    public DrawerLayout mdrawerlayout;

    public ActionBarDrawerToggle mdrawertoogle;

    public View header_view, footer_view;
    public ArrayList<NavDrawerItem> navDrawerItems;
    public NavDrawerListAdapter adapter;
    public View customtoast;
    public AlertDialog alertDialog;
    public AlertDialog.Builder alertBuilder;
    public Dialog dialog;
    public HashMap<String, String> Userdata;
    PreferenceUtils preferenceUtils;
    String UserName, UserEmail, UserMobile, WalletAmt;
    private AnimationDrawable animationDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_base);

        basecontext = BaseActivity.this;

        preferenceUtils = new PreferenceUtils(basecontext);

        basecontext = BaseActivity.this;

        this.savedInstanceState = savedInstanceState;

        inflater = this.getLayoutInflater();

        navDrawerItems = new ArrayList<>();

        baseInitializeControls();

        generateiconandstringfordrawer();

        initialize();

        //updatesidepanelwalletamt();

        if (mtoolbar != null) {
            setSupportActionBar(mtoolbar);

        }

        try {
            getSupportActionBar().setHomeButtonEnabled(false);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        initdrawer();

        mdrawertoogle.syncState();

        mdrawerlistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                NavDrawerItem drawerobj = (NavDrawerItem) parent.getItemAtPosition(position);

                //Toast.makeText(BaseActivity.this,"base list item click"+drawerobj.title,Toast.LENGTH_SHORT).show();

                slidelist_item_click(drawerobj.title);

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    public void slidelist_item_click(String title) {
        switch (title) {
            
            /* <item>My Profile</item>
        <item>My Orders</item>
        <item>My Wallet</item>
        <item>Contact Us</item>
        <item>Logout</item>*/
            case "My Profile":
                goto_myprofile_method();
                mdrawerlayout.closeDrawers();
                break;
            case "My Orders":
                goto_myOrders_method();
                mdrawerlayout.closeDrawers();
                break;
           /* case "My Wallet":
                goto_takeatour_method();
                mdrawerlayout.closeDrawers();
                break;*/
            case "Contact Us":
                goto_contactus_method();
                mdrawerlayout.closeDrawers();
                break;
            case "Login":
                goto_login_method();
                mdrawerlayout.closeDrawers();
                break;
            case "Logout":
                goto_logout_method();
                adapter.notifyDataSetChanged();
                mdrawerlayout.closeDrawers();

        }
    }

    public void showAlertDialog(String strMessage, String firstBtnName) {
        runOnUiThread(new RunshowCustomDialogs(strMessage, firstBtnName));
    }

    public void closeAlertDialog() {
        if (alertDialog != null && alertDialog.isShowing())
            alertDialog.dismiss();
    }

    public abstract void goto_mealPlan_method();

    public abstract void initialize();

    public abstract void goto_myprofile_method();

    /*   public abstract void goto_changepassword_method();

       public abstract void goto_addressBook_method();

       public abstract void goto_sharing_method();

       public abstract void goto_feedback_method();

       public abstract void goto_ordernow_method();

       public abstract void goto_takeatour_method();
   */
    public abstract void goto_contactus_method();

    public abstract void goto_login_method();

    public abstract void goto_logout_method();

    public abstract void goto_myOrders_method();

    private void initdrawer() {

        mdrawertoogle = new ActionBarDrawerToggle(this, mdrawerlayout, mtoolbar, R.string.opendrawer, R.string.closedrawer) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
            }

            @Override
            public void onDrawerStateChanged(int newState) {
                super.onDrawerStateChanged(newState);
            }


        };

        mdrawerlayout.setDrawerListener(mdrawertoogle);
    }

    private void baseInitializeControls() {


        llContent = (LinearLayout) findViewById(R.id.llContent);

        header_view = getLayoutInflater().inflate(R.layout.header_navi, null);

        footer_view = getLayoutInflater().inflate(R.layout.footer_navi, null);

        mdrawerlistview = (ListView) findViewById(R.id.left_drawer);

        mtoolbar = (Toolbar) findViewById(R.id.toolbar);

        mdrawerlayout = (DrawerLayout) findViewById(R.id.drawerLayout);

        iv_filter = (ImageView) findViewById(R.id.iv_filter);

        adapter = new NavDrawerListAdapter(basecontext, navDrawerItems);

        ll_userDetail = (LinearLayout) header_view.findViewById(R.id.ll_userDetail);

        txt_usrname = (TextView) header_view.findViewById(R.id.tv_userNameNav);

        txt_usrcontact = (TextView) header_view.findViewById(R.id.tv_userMobileNav);

        mdrawerlistview.addHeaderView(header_view, null, false);

        mdrawerlistview.addFooterView(footer_view, null, false);

        mdrawerlistview.setAdapter(adapter);

    }

    private void generateiconandstringfordrawer() {

        //TypedArray navMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons);

        if (preferenceUtils.isLoggedIn()) {
            nav_item_titlenames = getResources().getStringArray(R.array.navdraweritemsafterlogin);
            Userdata = preferenceUtils.getUserDetails();

            UserName = Userdata.get(AppConstants.KEY_USER_NAME);
            UserMobile = Userdata.get(AppConstants.KEY_USER_CONTACT);
            UserEmail = Userdata.get(AppConstants.KEY_USER_EMAIL);
            WalletAmt = Userdata.get(AppConstants.KEY_WALLET_AMOUNT);

            txt_usrname.setText(UserName);
            txt_usrcontact.setText(UserMobile);

        } else {
            nav_item_titlenames = getResources().getStringArray(R.array.navdraweritemsbeforelogin);

            ll_userDetail.setVisibility(View.GONE);

        }

        //nav_item_titlenames = getResources().getStringArray(R.array.navdraweritemsbeforelogin);

        // adding nav drawer items to list
        // My Profile
        navDrawerItems.add(new NavDrawerItem(nav_item_titlenames[0]));
        // My Orders
        navDrawerItems.add(new NavDrawerItem(nav_item_titlenames[1]));
        //My Wallet
        navDrawerItems.add(new NavDrawerItem(nav_item_titlenames[2]));
        // Contact Us
        navDrawerItems.add(new NavDrawerItem(nav_item_titlenames[3]));
        // Logout/Login
        navDrawerItems.add(new NavDrawerItem(nav_item_titlenames[4]));

        // navDrawerItems.add(new NavDrawerItem(nav_item_titlenames[5]));
        //Login/logout
        // navDrawerItems.add(new NavDrawerItem(nav_item_titlenames[6]));
/*
* navDrawerItems.add(new NavDrawerItem(nav_item_titlenames[4], navMenuIcons.getResourceId(4, -1),true,TempLoyaltyPoints));*/
        // Recycle the typed array
        // navMenuIcons.recycle();

    }

    @Override
    public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);

        mdrawertoogle.syncState();

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        mdrawertoogle.syncState();
    }

    public void updatecustomerwalletamount(double custwalletamount) {

        if (navDrawerItems != null && navDrawerItems.size() != 0) {
            for (int i = 0; i < navDrawerItems.size(); i++) {
                NavDrawerItem navDrawerItem = navDrawerItems.get(i);

                if (navDrawerItem.isCounterVisible) {
                    navDrawerItem.count = String.valueOf(custwalletamount);
                }
            }

            adapter.notifyDataSetChanged();
        }
    }

    public void showLoaderNew() {
        runOnUiThread(new Runloader(getResources().getString(R.string.loading)));
    }

    public void hideloader() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (dialog != null && dialog.isShowing())
                        dialog.dismiss();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    class RunshowCustomDialogs implements Runnable {
        private String strMessage;// Message to be shown in dialog
        private String firstBtnName;
        private int titleGravity;
        private boolean isShowNestedDialog;
        private String dialogFrom;

        public RunshowCustomDialogs(String strMessage, String firstBtnName) {
            this.strMessage = strMessage;
            this.firstBtnName = firstBtnName;
        }

        @Override
        public void run() {
            closeAlertDialog();
            alertBuilder = new AlertDialog.Builder(basecontext);
            alertBuilder.setCancelable(true);

            final LinearLayout linearLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.notification_dailog, null);

            TextView dialogtvTitle = (TextView) linearLayout.findViewById(R.id.tvTitle);
            TextView btnYes = (TextView) linearLayout.findViewById(R.id.btnYes);


            if (titleGravity != 0) {
                // Only in the case of Crash Report Dialog, i am customizing it with custom padding.
                dialogtvTitle.setGravity(titleGravity);
                dialogtvTitle.setPadding(35, 35, 0, 35);

            }
            dialogtvTitle.setText(strMessage);
            btnYes.setText(firstBtnName);
            btnYes.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    alertDialog.cancel();

                }
            });

            try {
                alertDialog = alertBuilder.create();
                alertDialog.setView(linearLayout, 0, 0, 0, 0);
                alertDialog.setInverseBackgroundForced(true);
                alertDialog.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    class Runloader implements Runnable {
        private String strrMsg;

        public Runloader(String strMsg) {
            this.strrMsg = strMsg;
        }

        @SuppressWarnings("ResourceType")
        @Override
        public void run() {
            try {
                if (dialog == null) {
                    dialog = new Dialog(basecontext, R.style.Theme_Dialog_Translucent);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.getWindow().setBackgroundDrawable(
                            new ColorDrawable(android.graphics.Color.TRANSPARENT));
                }
                dialog.setContentView(R.layout.loading);
                dialog.setCancelable(false);

                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                    dialog = null;
                }
                dialog.show();

                ImageView imgeView = (ImageView) dialog
                        .findViewById(R.id.imgeView);
                TextView tvLoading = (TextView) dialog
                        .findViewById(R.id.tvLoading);
                if (!strrMsg.equalsIgnoreCase(""))
                    tvLoading.setText(strrMsg);

                imgeView.setBackgroundResource(R.anim.frame);

                animationDrawable = (AnimationDrawable) imgeView
                        .getBackground();
                imgeView.post(new Runnable() {
                    @Override
                    public void run() {
                        if (animationDrawable != null)
                            animationDrawable.start();
                    }
                });
            } catch (Exception e) {

            }
        }
    }


}
