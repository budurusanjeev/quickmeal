package com.yitsol.quickmeal.businessLayer;

import android.content.Context;

import com.yitsol.quickmeal.webaccess.BaseWA;
import com.yitsol.quickmeal.webaccess.BuildXMLRequest;
import com.yitsol.quickmeal.webaccess.ServiceMethods;


public class CommonBL extends BaseBL {

    public CommonBL(Context mContext, DataListener listener) {

        super(mContext, listener);
    }

    public boolean doRegistration(String UserName, String MobileNum, String EmailId, String UserPass) {
        return new BaseWA(mContext, this).startDataDownload(ServiceMethods.WS_DO_REGISTRATION, BuildXMLRequest.doRegistration(UserName, EmailId, MobileNum, UserPass));
    }

    public boolean checkLoginByMail(String LoginId, String UserLoginPwd) {
        return new BaseWA(mContext, this).startDataDownload(ServiceMethods.WS_CHECK_LOGIN, BuildXMLRequest.checkLoginByMail(LoginId, UserLoginPwd));
    }

    public boolean checkLoginByMobile(String LoginId, String UserLoginPwd) {
        return new BaseWA(mContext, this).startDataDownload(ServiceMethods.WS_CHECK_LOGIN, BuildXMLRequest.checkLoginByMobile(LoginId, UserLoginPwd));
    }


    public boolean getRestaurentDetails() {
        return new BaseWA(mContext, this).startDataDownload(ServiceMethods.WS_GET_RESTAURANTS, BuildXMLRequest.getRestaurants());
    }

    public boolean getArea() {
        return new BaseWA(mContext, this).startDataDownload(ServiceMethods.WS_GET_AREA, BuildXMLRequest.getArea());
    }

    public boolean addAddress(String custId, String addressType, String houseNo, String address, String landmark, String city, String zipcode) {
        return new BaseWA(mContext, this).startDataDownload(ServiceMethods.WS_SAVE_ADDRESS, BuildXMLRequest.addAddress(custId, addressType, houseNo, address, landmark, city, zipcode));
    }

    public boolean getSubItems(String queryParams) {
        return new BaseWA(mContext, this).startDataDownload(ServiceMethods.WS_ITEM_LIST, BuildXMLRequest.getItems(), queryParams);

    }

    public boolean getAddress(String queryParams) {
        return new BaseWA(mContext, this).startDataDownload(ServiceMethods.WS_GET_ADDRESS, BuildXMLRequest.getAddress(), queryParams);
    }

    public boolean getUserProfile(String queryParams) {
        return new BaseWA(mContext, this).startDataDownload(ServiceMethods.WS_MY_PROFILE, BuildXMLRequest.getUserProfile(), queryParams);
    }

    public boolean getRestaurentsByAreaId(String queryParams) {
        return new BaseWA(mContext, this).startDataDownload(ServiceMethods.WS_GET_REST_BY_AREA_ID, BuildXMLRequest.getRestaurentsByAReaId(), queryParams);
    }

    public boolean applyCoupon(String customerId, double totalAmt, String couponCode) {
        return new BaseWA(mContext, this).startDataDownload(ServiceMethods.WS_CHECK_LOGIN, BuildXMLRequest.applyCoupon(customerId, totalAmt, couponCode));
    }

    public boolean contactUs(String name, String email, String phone, String message) {
        return new BaseWA(mContext, this).startDataDownload(ServiceMethods.WS_CONTACT_US, BuildXMLRequest.contactUs(name, email, phone, message));
    }
}
