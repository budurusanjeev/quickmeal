package com.yitsol.quickmeal.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yitsol.quickmeal.R;
import com.yitsol.quickmeal.businessLayer.CommonBL;
import com.yitsol.quickmeal.businessLayer.DataListener;
import com.yitsol.quickmeal.domain.UserProfileDO;
import com.yitsol.quickmeal.utilities.PreferenceUtils;
import com.yitsol.quickmeal.webaccess.Response;
import com.yitsol.quickmeal.webaccess.ServiceMethods;
import com.yitsol.quickmeal.webaccess.ServiceURLs;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MyProfileActivity extends BaseNew implements DataListener {

    public Toolbar mtoolbar;
    Context mContext;
    PreferenceUtils preferenceUtils;
    LinearLayout ll_myprofilelayout;
    FrameLayout ll_myprofile;
    ImageView img_back, img_resetFilter;
    TextView tv_usernameText, tv_username, tv_mobileText, tv_mobileNumber, tv_emailText, tv_emailId, tv_changePassword;
    TextView tv_dots1, tv_dots2, tv_dots3, tv_myAddresses, tv_home, txt_homeAddress, tv_office, txt_officeAddress, tv_others, txt_othersAddress;
    CheckBox chk_addHomeAddress, chk_addofficeAddress, chk_addothersAddress;
    ArrayList<UserProfileDO> arrListUserDetails;
    UserProfileDO userProfileDO;
    String Message = "";
    String customerId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        mContext = MyProfileActivity.this;
        preferenceUtils = new PreferenceUtils(mContext);
        //customerId = AppConstants.KEY_CUSTOMER_ID;

        customerId = preferenceUtils.getCustId();

        if (mtoolbar != null) {
            setSupportActionBar(mtoolbar);
        }
        Initialize();

        getUserInfo(customerId);


    }

    public void Initialize() {
        ll_myprofile = (FrameLayout) findViewById(R.id.ll_myprofile);
        ll_myprofilelayout = (LinearLayout) findViewById(R.id.ll_myprofilelayout);
        //img_normal_actionbar = (ImageView) findViewById(R.id.img_normal_actionbar);
        //img_back = (ImageView) findViewById(R.id.img_back);
        tv_usernameText = (TextView) findViewById(R.id.tv_usernameText);
        tv_username = (TextView) findViewById(R.id.tv_username);
        tv_mobileText = (TextView) findViewById(R.id.tv_mobileText);
        tv_mobileNumber = (TextView) findViewById(R.id.tv_mobileNumber);
        tv_emailText = (TextView) findViewById(R.id.tv_emailText);
        tv_emailId = (TextView) findViewById(R.id.tv_emailId);
        tv_dots1 = (TextView) findViewById(R.id.tv_dots1);
        tv_dots2 = (TextView) findViewById(R.id.tv_dots2);
        tv_dots3 = (TextView) findViewById(R.id.tv_dots3);
        tv_myAddresses = (TextView) findViewById(R.id.tv_myAddresses);
        tv_home = (TextView) findViewById(R.id.tv_home);
        txt_homeAddress = (TextView) findViewById(R.id.txt_homeAddress);
        tv_office = (TextView) findViewById(R.id.tv_office);
        txt_officeAddress = (TextView) findViewById(R.id.txt_officeAddress);
        // tv_others = (TextView) findViewById(R.id.tv_others);
        // txt_othersAddress = (TextView) findViewById(R.id.txt_othersAddress);
        tv_changePassword = (TextView) findViewById(R.id.tv_changePassword);
        chk_addHomeAddress = (CheckBox) findViewById(R.id.chk_addHomeAddress);
        chk_addofficeAddress = (CheckBox) findViewById(R.id.chk_addofficeAddress);
        //chk_addothersAddress = (CheckBox) findViewById(R.id.chk_addothersAddress);

    }

    public void getUserInfo(String id) {

        StringBuilder sb = new StringBuilder();

        String url = ServiceURLs.getRequestedURL(ServiceMethods.WS_MY_PROFILE);

        sb.append(url);

        JSONObject inputjobj = new JSONObject();

        JSONObject wherejobj = new JSONObject();

        try {
            wherejobj.put("id", customerId);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            inputjobj.put("where", wherejobj);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        sb.append(inputjobj.toString());

        String URLTOPOST = sb.toString();

        if (new CommonBL(mContext, MyProfileActivity.this).getUserProfile(customerId)) {
            showLoaderNew();
        } else {
            hideloader();
            // showAlertDialog("NO INTERNET","OK");
        }
    }

    @Override
    public void dataRetreived(Response data) {
        if (data != null && data.method == ServiceMethods.WS_MY_PROFILE) {
            hideloader();

            if (!data.isError) {

                arrListUserDetails = (ArrayList<UserProfileDO>) data.data;

                userProfileDO = arrListUserDetails.get(0);

                for (int i = 0; i < arrListUserDetails.size(); i++) {

                    Message = arrListUserDetails.get(i).statusMessage;
                }
                tv_username.setText(userProfileDO.name);
                tv_emailId.setText(userProfileDO.email);
                tv_mobileNumber.setText(userProfileDO.phone);

                if (Message.equalsIgnoreCase("422")) {
                    showAlertDialog("Error", "Ok");
                } else {
                    //showAlertDialog("No Internet Connection", "Ok");
                    Toast.makeText(mContext, "NO INTERNET CONNECTION", Toast.LENGTH_SHORT).show();
                }

            }

        } else {
            //showAlertDialog("No Internet Connection", "Ok");
            Toast.makeText(mContext, "Error", Toast.LENGTH_SHORT).show();

        }
    }
}
