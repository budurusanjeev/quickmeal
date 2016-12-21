package com.yitsol.quickmeal.utilities;

import android.graphics.Typeface;
import android.os.Environment;

import com.yitsol.quickmeal.activity.Dashboard;

import java.io.File;
import java.net.HttpURLConnection;

public class AppConstants {

    public static final int GET = 1;
    public static final int POST = 2;
    //Device height & width
    public static int DEVICE_DISPLAY_WIDTH;
    public static int DEVICE_DISPLAY_HEIGHT;
    public static Typeface typeFace;

    //public static String IMAGE_URL="http://fitros-ws.elasticbeanstalk.com/";

    public static String SDCARD_ROOT = Environment.getExternalStorageDirectory().toString() + File.separator;

    public static String appCacheDir = "";

    public static String ContentTypeJson = "application/json";

    public static String ContentTypeXML = "application/xml";

    public static String ContentTypeForm = "application/x-www-form-urlencoded";

    public static String CHECK_NETWORK_CONN = "Please check internet connection!!";

    public static String NO_RESPONSE = "No Response from Server";
    public static int PAYMODEONLINE = 5;
    public static int PAYMODECOD = 7;
    public static String DASHBOARD = Dashboard.class.getSimpleName();
    public static String RESTAURANTID = "restaurantid";
    public static String RESTAURANTNAME = "restaurant name";
    public static String CLASSFROM = "classfrom";
    public static String KEY_USER_ID = "";
    public static String KEY_USER_NAME = "custName";
    public static String KEY_CUSTOMER_ID = "customerId";
    public static String KEY_USER_EMAIL = "email";
    public static String KEY_IS_LOGIN = "IsLoggedIn";
    public static String KEY_IS_COUPON_APPLIED = "IsCouponApplied";
    public static String KEY_TOTALAMTAFTERCOUPON = "totalamtaftercoupon";
    public static String KEY_SOCIAL_TYPE = "socialtype";
    public static String KEY_USER_CONTACT = "MobileNo";
    public static String KEY_WALLET_AMOUNT = "walletAmount";
    public static String ActionProceedToBillSummry = "proceedToBillSummry";
    public static String KEY_ORDER_SUCCESS = "orderDatails";
    public static String KEY_ORDERPACKEAGE = "orderpackagelist";
    public static String LOGINCOOKIE = "loginCookie";
    public static String FILTERQUIERY = "?filter=";
    public int UNAUTHORISED = HttpURLConnection.HTTP_UNAUTHORIZED;


}
