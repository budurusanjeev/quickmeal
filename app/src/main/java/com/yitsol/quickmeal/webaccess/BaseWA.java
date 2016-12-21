package com.yitsol.quickmeal.webaccess;

import android.content.Context;

import com.yitsol.quickmeal.parsers.BaseHandler;
import com.yitsol.quickmeal.utilities.LogUtils;
import com.yitsol.quickmeal.utilities.NetworkUtils;


public class BaseWA implements HttpListener {
    private WebAccessListener listener;
    private Context mContext;

    public BaseWA(Context mContext, WebAccessListener webAccessListener) {
        this.mContext = mContext;
        listener = webAccessListener;
    }

    /**
     * Method to start downloading the data.
     *
     * @param method
     * @param parameters
     * @return boolean
     */
    public boolean startDataDownload(ServiceMethods method, String parameters) {
        if (NetworkUtils.isNetworkConnectionAvailable(mContext)) {
            HTTPSimulator downloader = new HTTPSimulator(this, method, parameters);
            downloader.start();


            return true;
        } else {

            return false;
        }
    }


    public boolean startDataDownload(ServiceMethods method, String parameters, String queryparams) {
        if (NetworkUtils.isNetworkConnectionAvailable(mContext)) {
            HTTPSimulator downloader = new HTTPSimulator(this, method, parameters, queryparams);
            downloader.start();


            return true;
        } else {
            return false;
        }
    }

    /**
     * Method to perform operation after receiving the response
     */
    @Override
    public void onResponseReceived(Response response) {
        this.respondWithData(response);
    }

    /**
     * Method to respond for the data
     *
     * @param data
     */
    protected void respondWithData(Response data) {
        listener.dataDownloaded(data);
    }

    class HTTPSimulator extends Thread {
        HttpListener listener;
        ServiceMethods method;
        String parameters;
        int rawSource;
        String queryparams;

        public HTTPSimulator(HttpListener listener, ServiceMethods method, String parameters, String queryparams) {
            this.listener = listener;
            this.method = method;
            this.parameters = parameters;
            this.queryparams = queryparams;
        }

        public HTTPSimulator(HttpListener listener, ServiceMethods method, String parameters) {
            this.listener = listener;
            this.method = method;
            this.parameters = parameters;

        }

        public HTTPSimulator(HttpListener listener, ServiceMethods method, int rawSource, String key) {
            this.listener = listener;
            this.method = method;
            this.rawSource = rawSource;
        }

        @Override
        public void run() {
            Response response = new Response(method, true, "Unable to connect server, please try again.");
            String isResponse = null;

            try {
                LogUtils.info(LogUtils.SERVICE_LOG_TAG, "************************************");
                LogUtils.info(LogUtils.SERVICE_LOG_TAG, "WebService: " + String.valueOf(method));


                isResponse = new RestClient().sendRequest(mContext, method, parameters, queryparams);

                if (isResponse != null) {
                    BaseHandler handler = BaseHandler.getParser(method, isResponse);

                    if (handler != null) {

                        LogUtils.info(LogUtils.SERVICE_LOG_TAG, "Parsing completed");

                        if (handler.getErrorData().equalsIgnoreCase("null") && handler.getData() != null) {
                            response.isError = false;
                            response.data = handler.getData();
                        } else {

                            String errorMsg = handler.getErrorData();
                            if (errorMsg != null && !errorMsg.equalsIgnoreCase(""))
                                response.data = errorMsg;
                            response.isError = true;
                        }
                    } else
                        LogUtils.info(LogUtils.SERVICE_LOG_TAG, "Handler null");

                } else
                    LogUtils.info(LogUtils.SERVICE_LOG_TAG, "InputStream null");
            } catch (Exception e) {
                e.printStackTrace();
            }

            listener.onResponseReceived(response);
        }
    }
}
