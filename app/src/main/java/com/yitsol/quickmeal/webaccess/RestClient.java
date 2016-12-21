package com.yitsol.quickmeal.webaccess;

import android.content.Context;

import com.yitsol.quickmeal.utilities.AppConstants;
import com.yitsol.quickmeal.utilities.LogUtils;

import java.io.IOException;


public class RestClient {

    public String sendRequest(Context mContext, ServiceMethods method, String parameters, String queryparams) throws IOException {
        int reqType = ServiceURLs.getRequestType(method);

        String contentType = ServiceURLs.getContentType(method);

        if (reqType == AppConstants.GET) {
            LogUtils.debug(RestClient.class.getSimpleName(), "Url request is" + queryparams);
            return new HttpHelper(mContext).sendGETRequest(ServiceURLs.getRequestedURL(method), queryparams, contentType);
        } else if (reqType == AppConstants.POST)
            return new HttpHelper(mContext).sendPOSTRequest(ServiceURLs.getRequestedURL(method), queryparams, parameters, contentType);

        return null;
    }
}
