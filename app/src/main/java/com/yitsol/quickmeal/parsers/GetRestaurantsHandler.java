package com.yitsol.quickmeal.parsers;


import com.yitsol.quickmeal.domain.RestaurantAreaDO;
import com.yitsol.quickmeal.domain.RestaurantDomain;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class GetRestaurantsHandler extends BaseHandler {


    ArrayList<RestaurantDomain> arrRestaurantList;
    RestaurantDomain restaurantDO;
    ArrayList<RestaurantAreaDO> arrAreaList;
    RestaurantAreaDO areaDO;
    int opstatus, key;
    String httpStatus = "", areaId;
    String errorMessage = "", Message = "", Error = "";

    public GetRestaurantsHandler(String inputStream) {
        arrRestaurantList = new ArrayList<>();
        getInputStream(inputStream);
    }

    private void getInputStream(String inputStream) {
        try {

            JSONObject jsonObject = new JSONObject(inputStream);
            Message = jsonObject.getString("status");
            if (jsonObject.has("error")) {
                JSONObject jObject = jsonObject.getJSONObject("error");
                Message = jsonObject.getString("status");
                restaurantDO = new RestaurantDomain();
                restaurantDO.errorMessage = "Restaurants Details not found";
                errorMessage = "null";
                arrRestaurantList.add(restaurantDO);
            } else if (Message.equalsIgnoreCase("success")) {
                JSONArray jArray = jsonObject.getJSONArray("data");
                for (int i = 0; i < jArray.length(); i++) {
                    JSONObject dataObj = jArray.getJSONObject(i);
                    restaurantDO = new RestaurantDomain();
                    restaurantDO.restaurantName = dataObj.getString("name");
                    restaurantDO.restaurantId = dataObj.getString("id");
                    restaurantDO.restaurantMoto = dataObj.getString("desc");
                    restaurantDO.restaurantImageUrl = dataObj.getString("imageUrl");

                    JSONArray areaArray = dataObj.getJSONArray("areaId");
                    for (int j = 0; j < areaArray.length(); j++) {
                        areaId = (String) areaArray.get(j);
                        restaurantDO.areaList.add(areaId);
                    }

                    arrRestaurantList.add(restaurantDO);

                }
                errorMessage = "null";
                restaurantDO.Message = Message;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public Object getData() {
        if (arrRestaurantList != null && arrRestaurantList.size() > 0)
            return arrRestaurantList;
        else
            return 0;
    }

    @Override
    public String getErrorData() {
        return errorMessage;
    }


}
