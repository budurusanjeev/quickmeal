package com.yitsol.quickmeal.domain;

/**
 * Created by Nikita on 1/13/2016.
 */
public class CancelOrderDO {
    public int orderId, key, quantity, custId;
    public String cancelDate = "";
    public double Customer_Wallet_Amount;
    public String statusMessage = "";
    public String errorMessage = "", status = "";
}
