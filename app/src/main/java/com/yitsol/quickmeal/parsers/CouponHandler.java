package com.yitsol.quickmeal.parsers;


import com.yitsol.quickmeal.domain.CouponDO;

import org.json.JSONObject;

import java.util.ArrayList;

public class CouponHandler extends BaseHandler {


    ArrayList<CouponDO> arrCouponDO;
    CouponDO couponDO;
    int opstatus, key;
    String httpStatus = "";
    String errorMessage = "", Message = "", Error = "";


    public CouponHandler(String inputStream) {
        arrCouponDO = new ArrayList<>();
        getInputStream(inputStream);
    }

    private void getInputStream(String inputStream) {
        try {

            JSONObject jsonObject = new JSONObject(inputStream);
            if (jsonObject.has("Coupon")) {
                couponDO = new CouponDO();
                couponDO.TotalAmt = jsonObject.getDouble("baseValue");
                couponDO.CouponAmount = jsonObject.getDouble("discountAmount");
                couponDO.GrandTotalAfterCoupon = jsonObject.getDouble("finalValue");
                errorMessage = "null";
                couponDO.statusMessage = Message;
                arrCouponDO.add(couponDO);
            } else if (jsonObject.has("error")) {
                couponDO = new CouponDO();
                couponDO.statusMessage = jsonObject.getString("message");
                errorMessage = "error";
                couponDO.statusMessage = Message;
                arrCouponDO.add(couponDO);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public Object getData() {
        if (arrCouponDO != null && arrCouponDO.size() > 0)
            return arrCouponDO;
        else
            return 0;
    }

    @Override
    public String getErrorData() {
        return errorMessage;
    }


}
