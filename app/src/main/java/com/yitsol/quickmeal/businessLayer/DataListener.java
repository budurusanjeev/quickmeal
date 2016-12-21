package com.yitsol.quickmeal.businessLayer;


import com.yitsol.quickmeal.webaccess.Response;

/**
 * interface to recieve the Retreived data
 */
public interface DataListener {
    /**
     * Method to respond when data got received from web-service.
     *
     * @param data
     */
    void dataRetreived(Response data);


}
