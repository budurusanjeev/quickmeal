package com.yitsol.quickmeal.parsers;


import com.yitsol.quickmeal.utilities.LogUtils;
import com.yitsol.quickmeal.utilities.StringUtils;
import com.yitsol.quickmeal.webaccess.ServiceMethods;

import org.xml.sax.helpers.DefaultHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public abstract class BaseHandler extends DefaultHandler {
    public Boolean currentElement = false;
    public String currentValue = "";

    public static BaseHandler getParser(ServiceMethods wsMethod, String inputStream) {
        switch (wsMethod) {
            case WS_DO_REGISTRATION:
                return new GetAllRegistrationHandler(inputStream);

            case WS_CHECK_LOGIN:
                return new GetLoginHandler(inputStream);

            case WS_SAVE_ADDRESS:
                return new SaveAddressDetailHandler(inputStream);

            case WS_ITEM_LIST:
                return new GetSubItemListDataHandler(inputStream);

            case WS_MY_PROFILE:
                return new GetUserProfileHandler(inputStream);

            case WS_GET_ADDRESS:
                return new GetAddressDetailHandler(inputStream);

            case WS_CONTACT_US:
                return new ContactUsHandler(inputStream);

            case WS_GET_RESTAURANTS:
                return new GetRestaurantsHandler(inputStream);

            case WS_GET_AREA:
                return new GetAreaHandler(inputStream);

            case WS_GET_REST_BY_AREA_ID:
                return new GetRestaurantsByAreaIdHandler(inputStream);
        }
        return null;
    }

    public abstract Object getData();

    public abstract String getErrorData();

    public String getStringFromInputStream(InputStream inputStream) {
        if (inputStream != null) {
            BufferedReader br = null;
            StringBuilder sb = new StringBuilder();
            String line;
            try {
                br = new BufferedReader(new InputStreamReader(inputStream));
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            return sb.toString();
        } else {
            return "";
        }
    }

    //Method to convert StringBuffer to String.
    public String sb2String(StringBuffer sb) {
        if (sb == null)
            return "";
        try {
            return sb.toString();
        } catch (Exception e) {
            LogUtils.error(this.getClass().getName(), "sb2String exception:" + e.getMessage());
        }
        return null;
    }

    //Method to convert StringBuffer to int.
    public int sb2Int(StringBuffer sb) {
        if (sb == null)
            return 0;
        try {
            return StringUtils.getInt(sb.toString());
        } catch (Exception e) {
            LogUtils.error(this.getClass().getName(), "sb2Int exception:" + e.getMessage());
        }
        return 0;
    }

    //Method to convert StringBuffer to Long.
    public long sb2Long(StringBuffer sb) {
        if (sb == null)
            return 0;
        try {
            return Long.parseLong(sb.toString());
        } catch (Exception e) {
            LogUtils.error(this.getClass().getName(), "sb2Long exception:" + e.getMessage());
        }
        return 0;
    }

    //Method to convert StringBuffer to Float.
    public float sb2Float(StringBuffer sb) {
        if (sb == null)
            return 0;
        try {
            return Float.parseFloat(sb.toString());
        } catch (Exception e) {
            LogUtils.error(this.getClass().getName(), "sb2Float exception:" + e.getMessage());
        }
        return 0;
    }

    //Method to convert StringBuffer to double.
    public double sb2Double(StringBuffer sb) {
        if (sb == null)
            return 0;
        try {
            return Double.parseDouble(sb.toString());
        } catch (Exception e) {
            LogUtils.error(this.getClass().getName(), "sb2Long exception:" + e.getMessage());
        }
        return 0;
    }


    //Method to convert StringBuffer to boolean.
    public boolean sb2Boolean(StringBuffer sb) {
        boolean result = false;

        if (sb == null)
            return result;

        if (sb.length() > 0) {
            try {
                result = sb.toString().equalsIgnoreCase("true");
            } catch (Exception e) {
                LogUtils.error(this.getClass().getName(), "sb2Boolean exception:" + e.getMessage());
            }

        }
        return result;
    }


}
