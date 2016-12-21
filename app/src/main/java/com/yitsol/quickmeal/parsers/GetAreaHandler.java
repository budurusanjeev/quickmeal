package com.yitsol.quickmeal.parsers;

import com.yitsol.quickmeal.domain.AreaDO;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by User on 26-05-2016.
 */
public class GetAreaHandler extends BaseHandler {

    ArrayList<AreaDO> arrAreaDO;
    AreaDO areaDO;
    String errorMessage = "", Message = "", Error = "";

    public GetAreaHandler(String inputStream) {
        super();
        arrAreaDO = new ArrayList<AreaDO>();
        getInputStream(inputStream);
    }

    private void getInputStream(String inputStream) {
        try {
            JSONObject jObject = new JSONObject(inputStream);
            Message = jObject.getString("status");

            if (Message.equalsIgnoreCase("success")) {
                JSONArray jsonArray = jObject.getJSONArray("data");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject dataObject = jsonArray.getJSONObject(i);
                    areaDO = new AreaDO();
                    areaDO.areaName = dataObject.getString("areaName");
                    areaDO.areaId = dataObject.getString("id");
                    areaDO.areaPincode = dataObject.getString("pincode");
                    arrAreaDO.add(areaDO);
                    errorMessage = "null";
                    areaDO.message = Message;
                }

            } else {
                JSONObject jsonObject = jObject.getJSONObject("error");
                Message = jsonObject.getString("name");
                areaDO = new AreaDO();
                areaDO.message = "Data not found";
                errorMessage = "";
                arrAreaDO.add(areaDO);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object getData() {

        if (arrAreaDO != null && arrAreaDO.size() > 0)
            return arrAreaDO;
        else
            return 0;
    }

    @Override
    public String getErrorData() {

        return errorMessage;
    }
}
