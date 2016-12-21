package com.yitsol.quickmeal.listner;

/**
 * Created by User on 29-05-2016.
 */
public interface CustomCheckedItemListner {

    void addCustomItemDetails(String ItemName, double ItemPrice, String SubItemId);

    void removeCustomItemDetails(String ItemName, double ItemPrice, String SubItemId);

}
