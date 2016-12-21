package com.yitsol.quickmeal.domain;

public class MySpiceboxDO {
    public String orderType = "", status = "";
    public String OrderId = "", CancelOrderId = "";
    public String id = "", mealTimeCode = "", mealTimeDesc = "", mealTypeDesc = "", weekTypDesc = "";
    public String foodTypeDesc = "", packageDurDesc = "", pkgSizeTypeDesc = "", pkgSizeTypF = "";
    public int mealTimeId;
    public int mealTypeId;
    public int weekDurId;
    public int foodTypeId;
    public int packageDurId;
    public int addrTypeId, custDetId, cityId, pincodeId;
    public String cityDesc, houseNo, laneDesc;
    public int paidOrdersKey, unpaidOrdersKey, completedOrdersKey, key;
    public int custBillId, ordrStId, pkgSizTypId, pkgSizTypQty, payModId, ordOrgnId, addressId, cancelledeOrdersKey, activeOrdersKey;
    public String orderMessage = "", errorMessage = "", unpaidOrdersStatus = "", paidOrdersStatus = "";
    public String completedOrdersStatus, cancelledeOrdersStatus;
    public String ordDt = "", ordStDt = "", ordEndDt = "", ordTmstp = "", ordStDesc = "";
    public double ordBillAmnt, discAmnt, ordBillSubAmnt, redeemCancelAmnt;
}
