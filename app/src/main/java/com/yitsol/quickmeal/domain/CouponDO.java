package com.yitsol.quickmeal.domain;

/**
 * Created by Nikita on 1/13/2016.
 */
public class CouponDO {
    public int couponId, key;
    public String couponCode = "";
    public double TotalAmt, GrandTotalAfterCoupon, CouponAmount;
    public String statusMessage = "";
    public String errorMessage = "";
}
