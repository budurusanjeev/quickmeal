package com.yitsol.quickmeal.webaccess;

import com.yitsol.quickmeal.utilities.AppConstants;

public class ServiceURLs {
    public static String HOST_URL_2 = "http://139.162.56.235:9999/api/";

    //Method Names

    public static String DO_REGISTRATION = "Customers";
    public static String CHECK_LOGIN = "Customers/login";
    public static String GET_RESTAURANTS = "Restaurants";
    public static String SAVE_ADDRESS = "Addresses";
    public static String ITEM_LIST = "Items?filter=";
    public static String MY_PROFILE = "Customers/";
    public static String APPLY_COUPON = "Coupons/applyCoupon";
    public static String GET_ADDRESS = "Addresses?filter=";
    public static String GET_AREA = "Areas";
    public static String GET_REST_BY_AREA_ID = "Restaurants?filter=";


    public static int getRequestType(ServiceMethods wsMethod) {
        switch (wsMethod) {
            case WS_DO_REGISTRATION:
                return AppConstants.POST;//(or) AppConstants.GET based on request method

            case WS_CHECK_LOGIN:
                return AppConstants.POST;

            case WS_SAVE_ADDRESS:
                return AppConstants.POST;

            case WS_ITEM_LIST:
                return AppConstants.GET;

            case WS_MY_PROFILE:
                return AppConstants.GET;

            case WS_APPLY_COUPON:
                return AppConstants.POST;

            case WS_GET_ADDRESS:
                return AppConstants.GET;

            case WS_ORDER_PLACE:
                return AppConstants.POST;

            case WS_CONTACT_US:
                return AppConstants.POST;

            case WS_GET_RESTAURANTS:
                return AppConstants.GET;

            case WS_GET_AREA:
                return AppConstants.GET;

            case WS_GET_REST_BY_AREA_ID:
                return AppConstants.GET;

        }
        return 0;

    }


    public static String getContentType(ServiceMethods wsMethod) {
        switch (wsMethod) {

            case WS_DO_REGISTRATION:
                return AppConstants.ContentTypeJson;

            case WS_CHECK_LOGIN:
                return AppConstants.ContentTypeJson;

            case WS_SAVE_ADDRESS:
                return AppConstants.ContentTypeJson;

            case WS_ITEM_LIST:
                return AppConstants.ContentTypeJson;

            case WS_MY_PROFILE:
                return AppConstants.ContentTypeJson;

            case WS_APPLY_COUPON:
                return AppConstants.ContentTypeJson;

            case WS_GET_ADDRESS:
                return AppConstants.ContentTypeJson;

            case WS_ORDER_PLACE:
                return AppConstants.ContentTypeJson;

            case WS_CONTACT_US:
                return AppConstants.ContentTypeJson;

            case WS_GET_RESTAURANTS:
                return AppConstants.ContentTypeJson;

            case WS_GET_AREA:
                return AppConstants.ContentTypeJson;

            case WS_GET_REST_BY_AREA_ID:
                return AppConstants.ContentTypeJson;
        }
        return "NA";
    }

    public static String getRequestedURL(ServiceMethods wsMethod) {
        switch (wsMethod) {

            case WS_DO_REGISTRATION:
                return HOST_URL_2 + DO_REGISTRATION;

            case WS_CHECK_LOGIN:
                return HOST_URL_2 + CHECK_LOGIN;

            case WS_SAVE_ADDRESS:
                return HOST_URL_2 + SAVE_ADDRESS;

            case WS_ITEM_LIST:
                return HOST_URL_2 + ITEM_LIST;

            case WS_MY_PROFILE:
                return HOST_URL_2 + MY_PROFILE;

            case WS_APPLY_COUPON:
                return HOST_URL_2 + APPLY_COUPON;

            case WS_GET_ADDRESS:
                return HOST_URL_2 + GET_ADDRESS;

            case WS_GET_RESTAURANTS:
                return HOST_URL_2 + GET_RESTAURANTS;

            case WS_GET_AREA:
                return HOST_URL_2 + GET_AREA;

            case WS_GET_REST_BY_AREA_ID:
                return HOST_URL_2 + GET_REST_BY_AREA_ID;

        }
        return getRequestedURL(wsMethod);
    }


    public static String getRequestedURL(ServiceMethods wsMethod, String parameters) {
        switch (wsMethod) {

            case WS_DO_REGISTRATION:
                return String.format(HOST_URL_2 + DO_REGISTRATION, parameters);

            case WS_CHECK_LOGIN:
                return String.format(HOST_URL_2 + CHECK_LOGIN, parameters);

            case WS_SAVE_ADDRESS:
                return String.format(HOST_URL_2 + SAVE_ADDRESS, parameters);

            case WS_ITEM_LIST:
                return String.format(HOST_URL_2 + ITEM_LIST, parameters);

            case WS_MY_PROFILE:
                return String.format(HOST_URL_2 + MY_PROFILE, parameters);

            case WS_APPLY_COUPON:
                return String.format(HOST_URL_2 + APPLY_COUPON, parameters);

            case WS_GET_ADDRESS:
                return String.format(HOST_URL_2 + GET_ADDRESS, parameters);

            case WS_GET_RESTAURANTS:
                return String.format(HOST_URL_2 + GET_RESTAURANTS, parameters);

            case WS_GET_AREA:
                return String.format(HOST_URL_2 + GET_AREA, parameters);

            case WS_GET_REST_BY_AREA_ID:
                return String.format(HOST_URL_2 + GET_REST_BY_AREA_ID, parameters);

            default:
                return getRequestedURL(wsMethod);
        }
    }

}
