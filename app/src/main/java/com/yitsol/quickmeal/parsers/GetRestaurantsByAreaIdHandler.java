package com.yitsol.quickmeal.parsers;


import com.yitsol.quickmeal.domain.RestaurantAreaDO;
import com.yitsol.quickmeal.domain.RestaurantDomain;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class GetRestaurantsByAreaIdHandler extends BaseHandler {


    ArrayList<RestaurantDomain> arrRestaurantById;
    RestaurantDomain restaurantByAreaDO;
    ArrayList<RestaurantAreaDO> arrAreaList;
    RestaurantAreaDO areaDO;
    int opstatus, key;
    String httpStatus = "", areaId;
    String errorMessage = "", Message = "", Error = "";

    public GetRestaurantsByAreaIdHandler(String inputStream) {
        arrRestaurantById = new ArrayList<>();
        getInputStream(inputStream);
    }

    private void getInputStream(String inputStream) {
        try {

            JSONObject jsonObject = new JSONObject(inputStream);
            Message = jsonObject.getString("status");
            if (jsonObject.has("error")) {
                JSONObject jObject = jsonObject.getJSONObject("error");
                Message = jsonObject.getString("status");
                restaurantByAreaDO = new RestaurantDomain();
                restaurantByAreaDO.errorMessage = "Restaurants Details not found";
                errorMessage = "null";
                arrRestaurantById.add(restaurantByAreaDO);
            } else if (Message.equalsIgnoreCase("success")) {
                JSONArray jArray = jsonObject.getJSONArray("data");
                for (int i = 0; i < jArray.length(); i++) {
                    JSONObject dataObj = jArray.getJSONObject(i);
                    restaurantByAreaDO = new RestaurantDomain();
                    restaurantByAreaDO.restaurantName = dataObj.getString("name");
                    restaurantByAreaDO.restaurantId = dataObj.getString("id");
                    restaurantByAreaDO.restaurantMoto = dataObj.getString("desc");
                    restaurantByAreaDO.restaurantImageUrl = dataObj.getString("imageUrl");

                    JSONArray areaArray = dataObj.getJSONArray("areaId");
                    for (int j = 0; j < areaArray.length(); j++) {
                        areaId = (String) areaArray.get(j);
                        restaurantByAreaDO.areaList.add(areaId);
                    }

                    arrRestaurantById.add(restaurantByAreaDO);

                }
                errorMessage = "null";
                restaurantByAreaDO.Message = Message;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public Object getData() {
        if (arrRestaurantById != null && arrRestaurantById.size() > 0)
            return arrRestaurantById;
        else
            return 0;
    }

    @Override
    public String getErrorData() {
        return errorMessage;
    }


}
