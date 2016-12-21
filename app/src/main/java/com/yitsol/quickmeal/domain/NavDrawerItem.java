package com.yitsol.quickmeal.domain;

/**
 * Created by Goutam on 8/26/2015.
 */
public class NavDrawerItem extends BaseDomain {

    public String title;
    public int icon;
    public String count;
    // boolean to set visiblity of the counter
    public boolean isCounterVisible = false;

    public NavDrawerItem() {
    }

    public NavDrawerItem(String title) {

        this.title = title;
    }

    public NavDrawerItem(String title, boolean isCounterVisible, String count) {
        this.title = title;
        // this.icon = icon;
        this.isCounterVisible = isCounterVisible;
        this.count = count;
    }

}
