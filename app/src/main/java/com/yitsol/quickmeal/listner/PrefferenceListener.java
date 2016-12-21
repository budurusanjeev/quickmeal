package com.yitsol.quickmeal.listner;


import com.yitsol.quickmeal.domain.PrefferenceDomain;

/**
 * Created by User on 4/19/2016.
 */
public interface PrefferenceListener {

    void prefferenceadded(PrefferenceDomain prefferenceDomain);

    void prefferenceremove(PrefferenceDomain prefferenceDomain);

}
