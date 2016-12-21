package com.yitsol.quickmeal.webaccess;

import com.yitsol.quickmeal.domain.OrderNowDO;
import com.yitsol.quickmeal.domain.UserProfileDO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class BuildXMLRequest {

    public static String checkLoginByMail(String LoginId, String UserLoginPwd) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("email", LoginId);
            jsonObject.put("password", UserLoginPwd);

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    public static String checkLoginByMobile(String LoginId, String UserLoginPwd) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("phone", LoginId);
            jsonObject.put("password", UserLoginPwd);

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return jsonObject.toString();
    }


    public static String applyCoupon(String customerId, double totalAmt, String couponCode) {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("customerId", customerId);
            jsonObject.put("cartValue", totalAmt);
            jsonObject.put("couponCode", couponCode);

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    public static String forgetPassword(String userId_forget) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("mobile", userId_forget);

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    public static String doRegistration(String UserName, String MobileNum, String EmailId, String UserPass) {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("name", UserName);
            jsonObject.put("phone", MobileNum);
            jsonObject.put("email", EmailId);
            jsonObject.put("password", UserPass);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    public static String PlaceOrderOneDay(ArrayList<OrderNowDO> arrOrderNowDO, ArrayList<UserProfileDO> arrUserProfile) {

        JSONObject jsonObject = new JSONObject();

        OrderNowDO orderNowDO = arrOrderNowDO.get(0);
        try {
            jsonObject.put("customerId", orderNowDO.customerId);
            jsonObject.put("cartValue", orderNowDO.cartValue);
            jsonObject.put("subTotal", orderNowDO.subTotal);
            jsonObject.put("orderStatus", orderNowDO.orderStatus);
            jsonObject.put("mealTime", orderNowDO.mealTime);
            jsonObject.put("orderType", orderNowDO.orderType);
            jsonObject.put("quantity", orderNowDO.quantity);
            jsonObject.put("address", orderNowDO.custDelAddress);
            jsonObject.put("totalAmount", orderNowDO.totalAmount);
            jsonObject.put("paymentStatus", orderNowDO.paymentStatus);
            jsonObject.put("paymentMode", orderNowDO.paymentMode);

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        UserProfileDO userProfileDO = arrUserProfile.get(0);
        try {
            jsonObject.put("email", userProfileDO.email);
            jsonObject.put("phone", userProfileDO.phone);
            jsonObject.put("name", userProfileDO.name);

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        JSONObject selectedPackage = new JSONObject();

        OrderNowDO orderNowDO1 = arrOrderNowDO.get(0);
        try {

            JSONArray category = new JSONArray();
            for (int i = 0; i < orderNowDO1.categories.size(); i++) {
                category.put(orderNowDO1.categories.get(i));
            }

            JSONArray categoryList = new JSONArray();
            for (int i = 0; i < orderNowDO1.listOfMealPlanDOs.size(); i++) {
                JSONObject categorylistitem = new JSONObject();

                categorylistitem.put("name", orderNowDO1.listOfMealPlanDOs.get(i).food_ItemName);
                categorylistitem.put("id", orderNowDO1.listOfMealPlanDOs.get(i).food_ItemId);
            }
            selectedPackage.put("name", orderNowDO1.pkg_Name);
            selectedPackage.put("desc", orderNowDO1.pkg_Desc);
            selectedPackage.put("oneWeekPrice", orderNowDO1.oneWeekPrice);
            selectedPackage.put("oneMonthPrice", orderNowDO1.oneMonthPrice);
            selectedPackage.put("threeMonthPrice", orderNowDO1.threeMonthPrice);
            selectedPackage.put("sixMonthPrice", orderNowDO1.sixMonthPrice);
            selectedPackage.put("id", orderNowDO1.pkgId);
            selectedPackage.put("categories", category);
            selectedPackage.put("categoriesList", categoryList);
            selectedPackage.put("imgURL", orderNowDO1.pkg_Image);
            selectedPackage.put("mealTime", orderNowDO1.mealTime);
            selectedPackage.put("mealType", orderNowDO1.mealType);
            selectedPackage.put("price", orderNowDO1.pkg_Price);

            jsonObject.put("selectedPackage", selectedPackage);

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        JSONArray selectedDatesArray = new JSONArray();
        try {
            for (int i = 0; i < orderNowDO1.selectedDates.size(); i++) {
                JSONObject dateObj = new JSONObject();
                dateObj.put("dates", orderNowDO1.selectedDates.get(i).dates);
                selectedDatesArray.put(dateObj);
                jsonObject.put("selectedDatesArray", selectedDatesArray);
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return jsonObject.toString();
    }

    public static String changePassword(String mobile, String password, String newPassword) {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("mobile", mobile);
            jsonObject.put("password", password);
            jsonObject.put("newPassword", newPassword);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    public static String contactUs(String name, String email, String phone, String message) {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("name", name);
            jsonObject.put("email", email);
            jsonObject.put("phone", phone);
            jsonObject.put("message", message);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    public static String getOneDayOrderDetails() {

        StringBuilder stringBuilder = new StringBuilder();
        return stringBuilder.toString();
    }

    public static String viewAddress(int id) {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id", id);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    public static String getAddress() {
        StringBuilder stringBuilder = new StringBuilder();
        return stringBuilder.toString();
    }

    public static String addAddress(String customerId, String addressType, String houseNo, String address, String landmark, String city, String zipcode) {
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("customerId", customerId);
            jsonObject.put("addressType", addressType);
            jsonObject.put("houseNumber", houseNo);
            jsonObject.put("address", address);
            jsonObject.put("landMark", landmark);
            jsonObject.put("city", city);
            jsonObject.put("zipcode", zipcode);

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    public static String getItems() {
        StringBuilder stringBuilder = new StringBuilder();
        return stringBuilder.toString();
    }

    public static String getUserProfile() {
        StringBuilder stringBuilder = new StringBuilder();
        return stringBuilder.toString();
    }

    public static String getRestaurentsByAReaId() {
        StringBuilder stringBuilder = new StringBuilder();
        return stringBuilder.toString();
    }

    public static String getRestaurants() {
        StringBuilder stringBuilder = new StringBuilder();
        return stringBuilder.toString();
    }

    public static String getArea() {
        StringBuilder stringBuilder = new StringBuilder();
        return stringBuilder.toString();
    }
}
