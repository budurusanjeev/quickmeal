package com.yitsol.quickmeal.businessLayer;

import android.app.Activity;
import android.content.Context;

import com.yitsol.quickmeal.webaccess.Response;
import com.yitsol.quickmeal.webaccess.WebAccessListener;


/**
 * this class contains the control all over the Business Layer Classes
 **/
public class BaseBL implements WebAccessListener {
    public Context mContext;
    DataListener listener;

    public BaseBL(Context mContext, DataListener listener) {
        //Log.d("Sample", "com.winit.example.businesslayer.BaseBL.Constructor");
        this.mContext = mContext;
        this.listener = listener;
    }

    @Override
    public void dataDownloaded(Response data) {
        //Log.d("Sample", "com.winit.example.businesslayer.BaseBL.dataDownloaded");
        if (listener != null) {
            ((Activity) listener).runOnUiThread(new DataRetreivedRunnable(listener, data));
        }
    }

    /**
     * Class to respond when data has received successfully.
     */
    class DataRetreivedRunnable implements Runnable {
        DataListener listener;
        Response data;

        DataRetreivedRunnable(DataListener listener, Response data) {

            this.listener = listener;
            this.data = data;
        }

        @Override
        public void run() {
            listener.dataRetreived(data);
        }
    }
}
