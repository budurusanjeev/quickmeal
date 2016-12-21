package com.yitsol.quickmeal.webaccess;

import android.content.Context;
import android.text.format.DateUtils;

import com.yitsol.quickmeal.utilities.AppConstants;
import com.yitsol.quickmeal.utilities.LogUtils;
import com.yitsol.quickmeal.utilities.PreferenceUtils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class HttpHelper {
    InputStream responseStream;
    PreferenceUtils preferenceUtils;
    String cookieValue = "null";
    private long TIMEOUT_CONNECT_MILLIS = (60 * DateUtils.SECOND_IN_MILLIS);
    private long TIMEOUT_READ_MILLIS = TIMEOUT_CONNECT_MILLIS - 5000;


    public HttpHelper(Context mContext) {

        preferenceUtils = new PreferenceUtils(mContext);

    }

    public String sendPOSTRequest(String strPostURL, String queryparams, String strParamToPost, String contentType) {
        LogUtils.info(HttpHelper.class.getSimpleName(), "on HttpHelper sendPOSTRequest");

        LogUtils.info(HttpHelper.class.getSimpleName(), "Parameters" + strParamToPost);

        DataOutputStream outputstream;

        int statuscode = 0;

        String response = "NA";

        try {
            strPostURL = strPostURL.replaceAll(" ", "%20");

            if (queryparams != null) {
                strPostURL += queryparams;
                strPostURL = strPostURL.replaceAll(" ", "%20");
            }
            URL Url = new URL(strPostURL);
            try {
                LogUtils.debug(HttpHelper.class.getSimpleName(), "URL is" + strPostURL);

                HttpURLConnection urlConnection = (HttpURLConnection) Url.openConnection();

                urlConnection.setRequestMethod("POST");

                urlConnection.setConnectTimeout((int) TIMEOUT_CONNECT_MILLIS);

                urlConnection.setDoInput(true);

                urlConnection.setDoOutput(true);

                urlConnection.setUseCaches(false);

                urlConnection.setRequestProperty("Accept", "application/json");
                urlConnection.setRequestProperty("Content-Type", contentType);
                LogUtils.debug(HttpHelper.class.getSimpleName(), "Login cookie=" + preferenceUtils.getLoginCookie());
                urlConnection.setRequestProperty("Cookie", preferenceUtils.getLoginCookie());
                if (contentType.equalsIgnoreCase("application/x-www-form-urlencoded")) {
                    urlConnection.setRequestProperty("X-Requested-With", "XMLHttpRequest");
                }
                if (contentType.equalsIgnoreCase("application/json")) {
                    urlConnection.setRequestProperty("X-Requested-With", "XMLHttpRequest");
                }

                outputstream = new DataOutputStream(urlConnection.getOutputStream());

                outputstream.writeBytes(strParamToPost);

                outputstream.flush();

                outputstream.close();

                urlConnection.connect();

                statuscode = urlConnection.getResponseCode();

                LogUtils.debug(HttpHelper.class.getSimpleName(), "HTTP STATUS CODE is" + statuscode);

                if (statuscode == HttpURLConnection.HTTP_OK || statuscode == 422) {

                    Map<String, List<String>> headerFields = urlConnection.getHeaderFields();

                    Set<String> headerFieldsSet = headerFields.keySet();

                    Iterator<String> hearerFieldsIter = headerFieldsSet.iterator();

                    while (hearerFieldsIter.hasNext()) {
                        String headerFieldKey = hearerFieldsIter.next();
                        if ("Set-Cookie".equalsIgnoreCase(headerFieldKey)) {

                            List<String> headerFieldValue = headerFields.get(headerFieldKey);

                            for (String headerValue : headerFieldValue) {

                                LogUtils.debug(HttpHelper.class.getSimpleName(), "Cookie Found");


                                String[] fields = headerValue.split(";/s*");
                                cookieValue = fields[0];
                                LogUtils.debug(HttpHelper.class.getSimpleName(), "cookieValue=" + cookieValue);
                                if (!cookieValue.equalsIgnoreCase("null")) {
                                    String[] PerfectCookie = cookieValue.split(";");
                                    preferenceUtils.saveLoginCookie(PerfectCookie[0]);
                                }
                                LogUtils.debug(HttpHelper.class.getSimpleName(), "Cookie=" + preferenceUtils.getLoginCookie());

                            }
                        }
                    }
                    responseStream = new BufferedInputStream(urlConnection.getInputStream());

                    BufferedReader responseStreamReader = new BufferedReader(new InputStreamReader(responseStream));

                    String line = "";
                    StringBuilder stringBuilder = new StringBuilder();
                    while ((line = responseStreamReader.readLine()) != null) {
                        stringBuilder.append(line);
                    }
                    responseStreamReader.close();

                    response = stringBuilder.toString();
                } else {
                    response = AppConstants.NO_RESPONSE;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return response;
    }

    public String sendGETRequest(String strGetURL, String queryparams, String contentType) {
        LogUtils.info(HttpHelper.class.getSimpleName(), "on HttpHelper sendGETRequest");

        DataOutputStream outputstream;
        int statuscode = 0;
        String response = "NA";
        try {
            LogUtils.info(HttpHelper.class.getName(), strGetURL);
            strGetURL = strGetURL.replace(" ", "%20");
            if (queryparams != null) {
                strGetURL += queryparams;
            }
            URL Url = new URL(strGetURL);
            LogUtils.info(HttpHelper.class.getName(), "after url" + Url);
            try {
                HttpURLConnection connection = (HttpURLConnection) Url.openConnection();
                connection.setRequestMethod("GET");
                connection.setConnectTimeout((int) TIMEOUT_CONNECT_MILLIS);
                connection.setDoOutput(false);

                connection.setRequestProperty("Accept", "application/json");

                // connection.setRequestProperty("Content-Type", contentType);

                connection.connect();

                //Get Response

                statuscode = connection.getResponseCode();

                LogUtils.debug(HttpHelper.class.getSimpleName(), "HTTP STATUS CODE is" + statuscode);

                if (statuscode == HttpURLConnection.HTTP_OK) {
                    responseStream = connection.getInputStream();

                    BufferedReader responseStreamReader = new BufferedReader(new InputStreamReader(responseStream));

                    String line = "";
                    StringBuilder stringBuilder = new StringBuilder();
                    while ((line = responseStreamReader.readLine()) != null) {
                        stringBuilder.append(line);
                    }
                    responseStreamReader.close();

                    response = stringBuilder.toString();
                } else {
                    response = AppConstants.NO_RESPONSE;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return response;
    }
}
